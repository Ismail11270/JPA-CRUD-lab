/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.ismoil.atajanov.jpalab.controller;

import pl.polsl.ismoil.atajanov.jpalab.service.EntityManagerHolder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import pl.polsl.ismoil.atajanov.jpalab.view.View;
import pl.polsl.ismoil.atajanov.jpalab.model.Employee;
import pl.polsl.ismoil.atajanov.jpalab.exceptions.NoEntityException;
import pl.polsl.ismoil.atajanov.jpalab.model.Department;
import pl.polsl.ismoil.atajanov.jpalab.service.DepartmentService;
import pl.polsl.ismoil.atajanov.jpalab.service.EmployeeService;

/**
 * An extension of BaseController class Containing addition fields and methods
 * specific to Employee entity
 *
 * @see BaseController
 * @author Ismoil Atajanov
 * @version 1.1
 */
public class EmployeeController extends BaseController<Employee> {

    private DepartmentService departmentService;

    /**
     * Constructor
     *
     * @see BaseController
     * @param view instance of view class
     * @param emf instance of EntityManagerFactory class responsible for
     * creation of EntityManager
     */
    public EmployeeController(View view, EntityManagerFactory emf) {
        super(view);
        service = new EmployeeService(emf);
        departmentService = new DepartmentService(emf);
    }

    /**
     * @see BaseController#getEntityName()
     * @return String name of the entity the current controller refers to
     */
    /**
     * @see BaseController#update(int id)
     * @param id id of an entity to find
     * @return true if changes were successful or false when there was no
     * changes
     * @throws NoEntityException if the entity with a given id does not exist
     */
    @Override
    public boolean update(int id)
            throws NoEntityException {
        Employee emp = new Employee();
        emp = (Employee) service.getById(id);
        if (emp != null) {
            view.print("Current name - " + emp.getFullName() + "\nEnter a new name or leave blank to make no changes: \n");
            String newName = view.getStringInput();
            String empBd;
            view.print("Current date of birth - " + emp.getBirthDate().toString() + "\nEnter a new date of birth or leave blank to make no changes (DD/MM/YYYY): ");
            int year, month, day;
            while (true) {
                empBd = view.getStringInput();
                if (empBd.length() == 0) {
                    year = 0;
                    month = 0;
                    day = 0;
                    break;
                }
                String date[] = empBd.split("/");
                try {
                    day = Integer.parseInt(date[0]);
                    month = Integer.parseInt(date[1]) - 1;
                    year = Integer.parseInt(date[2]) - 1900;
                    break;
                } catch (NumberFormatException nfe) {
                    view.printRed(nfe.getMessage() + "\n Invalid date format please try again DD/MM/YYYY: ");
                }
            }
            view.endl();
            view.print("Current department id - " + emp.getDepartment() + "\nEnter an ID of the department or leave blank to make no changes: ");
            int depId;
            while (true) {
                depId = view.getIntInput();
                if (depId == 0) {
                    break;
                }
                try {
                    Department dpmnt = (Department) departmentService.getById(depId);
                    emp.setDepartment(dpmnt);
                    break;
                } catch (NoEntityException ex) {
                    view.printRed("Department with such id doesn't exist!");
                    view.sleep(300);
                    view.print("\nEnter 1 to print all the departments available\n"
                            + "\nOr enter anything to cancel the operation\n");
                    int choice = view.getIntInput();
                    if (choice == 1) {
                        List<Department> list = departmentService.getAll();
                        for (Department dep : list) {
                            view.print("/n===========================");
                            view.print(dep.toString());
                        }
                        view.endl();
                        view.endl();
                        view.print("Enter id of the department: ");
                    } else {
                        return false;
                    }
                }
            }
            if (newName.length() == 0 && empBd.length() == 0 && depId == 0) {
                return false;
            }
            if (newName.length() > 0) {
                emp.setFullName(newName);
            }
            if (empBd.length() > 0) {
                emp.setBirthDate(new Date(year, month, day));
            }
            service.update(emp);
            return true;
        } else {
            throw new NoEntityException("No employee entry with id = " + id + " was found in the database");
        }
    }

    public List<Employee> findByParameters(String employeeName, Department department, Date bDate) {
        return ((EmployeeService) service).findByParemeters(employeeName, department, bDate);
    }
}

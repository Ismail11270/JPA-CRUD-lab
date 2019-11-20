/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.ismoil.atajanov.jpalab.controller;


import java.util.List;
import javax.persistence.EntityManagerFactory;
import pl.polsl.ismoil.atajanov.jpalab.model.Department;
import pl.polsl.ismoil.atajanov.jpalab.exceptions.NoEntityException;
import pl.polsl.ismoil.atajanov.jpalab.service.DepartmentService;
import pl.polsl.ismoil.atajanov.jpalab.view.View;

/**
 * An extension of BaseController class
 * Containing addition fields and methods specific to Department entity
 * @see BaseController
 * @author Ismoil Atajanov
 */
public class DepartmentController extends BaseController<Department> {

    

    /**
     * Constructor
     * @param view instance of view class
     * @see View
     * @param emf instance of EntityManagerFactory class responsible for creation of EntityManager
     * @version 1.1
     */
    public DepartmentController(View view, EntityManagerFactory emf) {
        super(view);
        service = new DepartmentService(emf);
    }
    
    
    
   

    /**
     * @see BaseController#update(int id)
     * @param id id of an entity to find
     * @return true if changes were successful or false when there was no changes
     * @throws NoEntityException if the entity with a given id does not exist
     */
    @Override
    public boolean update(int id)
            throws NoEntityException {
        Department department = new Department();
        department = (Department)service.getById(id);
        if (department != null) {
            view.print("Old name - " + department.getDepartmentName() + "\nEnter a new name or leave blank to make no changes: \n");
            String newName = view.getStringInput();
            view.print("Old address - " + department.getAddress() + "\nEnter a new address or leave blank to make no changes: \n");
            String newAddress = view.getStringInput();
            if (newName.length() == 0 && newAddress.length() == 0) {
                return false;
            }
            if (newName.length() > 0) {
                department.setDepartmentName(newName);
            }
            if (newAddress.length() > 0) {
                department.setAddress(newAddress);
            }
            service.update(department);
            return true;
        } else {
            throw new NoEntityException("No department entry with id = " + id + " was found in the database");
        }
    }

    
    
    
    public List<Department> findByParameters(String departmentName, String address){
        return ((DepartmentService)service).findByParemeters(departmentName,address);
    }

}

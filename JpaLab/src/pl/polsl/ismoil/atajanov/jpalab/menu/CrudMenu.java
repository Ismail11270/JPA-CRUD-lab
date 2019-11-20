package pl.polsl.ismoil.atajanov.jpalab.menu;

import java.util.Date;
import java.util.List;
import pl.polsl.ismoil.atajanov.jpalab.controller.DepartmentController;
import pl.polsl.ismoil.atajanov.jpalab.controller.EmployeeController;
import pl.polsl.ismoil.atajanov.jpalab.view.View;
import pl.polsl.ismoil.atajanov.jpalab.model.Department;
import pl.polsl.ismoil.atajanov.jpalab.model.Employee;
import pl.polsl.ismoil.atajanov.jpalab.exceptions.NoEntityException;
import pl.polsl.ismoil.atajanov.jpalab.exceptions.NullEntityException;
import pl.polsl.ismoil.atajanov.jpalab.controller.BaseController;
import pl.polsl.ismoil.atajanov.jpalab.model.Persistable;

/**
 * Class controlling the iterations of the program and the menu
 *
 * @author Ismoil Atajanov
 * @version 1.2
 */
public class CrudMenu {

    /**
     * Text for the first main menu window Appears first
     */
    private final String MENU_PAGE_ONE
            = "\nCRUD MENU\n"
            + "Enter 1 to perform operations on Departments\n"
            + "Enter 2 to perform operations on Employees\n"
            + "Or enter anything else to quit\n";
    /**
     * Text for Departments menu
     */
    private final String DEPARTMENTS_MENU
            = "\nDEPARTMENTS OPERATIONS\n"
            + "Press 1 to add a new department\n"
            + "Press 2 to update a department details\n"
            + "Press 3 to delete a department\n"
            + "Press 4 to find (a) department{s}\n"
            + "\nPress 9 to remove the contents of departments table\n\n"
            + "Press 0 to go back to the main menu\n";
    /**
     * Text for Employees menu
     */
    private final String EMPLOYEES_MENU
            = "\nEMPLOYEES OPERATIONS\n"
            + "Press 1 to add a new employee\n"
            + "Press 2 to update an employee details\n"
            + "Press 3 to delete an employee\n"
            + "Press 4 to find (an) employee{s}\n"
            + "\nPress 9 to remove the contents of employees table\n\n"
            + "Press 0 to go back to the main menu\n";
    /**
     * Text for both employees and departments menu, requesting to input a type
     * of query to perform
     */
    private final String SEARCH_OPTIONS
            = "\nSearch Options: \n"
            + "Enter 1 to find all the entries available\n"
            + "Enter 2 to find by id\n"
            + "Enter 3 to find by matching parameters\n"
            + "Enter 0 to go back\n";
    /**
     * Line separator text used to separate entities in a printed list
     */
    private final String SEPARATOR_LINE = "\n======================\n";

    /**
     * Object of View class Handles all I/O operations of the program
     *
     * @see View
     */
    private final View view;

    /**
     * Controller object Controls all the operations performed on Employee
     * entities
     *
     * @see EmployeeController
     */
    private final EmployeeController employeeController;

    /**
     * Controller object Controls all the operations performed on Department
     * entities
     *
     * @see DepartmentController
     */
    private final DepartmentController departmentController;

    /**
     * Constructor
     *
     * @param view instance of view class
     * @param employeeController instance of EmployeeController class
     * @param departmentController instance of DepartmentController class
     */
    public CrudMenu(View view, EmployeeController employeeController, DepartmentController departmentController) {
        this.departmentController = departmentController;
        this.view = view;
        this.employeeController = employeeController;
    }

    /**
     * Prints the first part of the menu
     */
    public void printFirstPage() {
        while (true) {
            view.print(MENU_PAGE_ONE);
            view.endl();
            int depOrEmp = view.getIntInput();
            view.endl();
            switch (depOrEmp) {
                case 1:
                    runDepartmentsMenu();
                    break;
                case 2:
                    runEmployeeMenu();
                    break;
                case 0:
                    return;
                default:
                    return;
            }
        }
    }

    /**
     * Print available operations on Employees table and execute appropriate
     * methods
     */
    private void runEmployeeMenu() {
        while (true) {
            view.print(EMPLOYEES_MENU);
            int crudOption = view.getIntInput();
            switch (crudOption) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    update(employeeController);
                    break;
                case 3:
                    delete(employeeController);
                    break;
                case 4:
                    queryEmployees();
                    break;
                case 9:
                    truncate(employeeController);
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * Print available operations on Employees table and execute appropriate
     * methods
     */
    private void runDepartmentsMenu() {
        while (true) {
            view.print(DEPARTMENTS_MENU);
            int crudOption = view.getIntInput();
            switch (crudOption) {
                case 1:
                    addDepartment();
                    break;
                case 2:
                    update(departmentController);
                    break;
                case 3:
                    delete(departmentController);
                    break;
                case 4:
                    queryDepartments();
                    break;
                case 9:
                    truncate(departmentController);
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * Print query option for Employees and execute appropriate methods
     */
    private void queryEmployees() {
        view.print(SEARCH_OPTIONS);
        while (true) {
            int option = view.getIntInput();
            switch (option) {
                //find all
                case 1:
                    findAll(employeeController);
                    view.print(SEARCH_OPTIONS);
                    break;
                //find by id
                case 2:
                    findById(employeeController);
                    view.print(SEARCH_OPTIONS);
                    break;
                case 3:
                    //ToDo
                    findEmployeesByParameters();
                    view.print(SEARCH_OPTIONS);
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * Print query option for Departments and execute appropriate methods for
     */
    private void queryDepartments() {
        //find all
        //find by id
        //find by parameters
        view.print(SEARCH_OPTIONS);
        while (true) {
            int option = view.getIntInput();
            switch (option) {
                //find all
                case 1:
                    findAll(departmentController);
                    view.print(SEARCH_OPTIONS);
                    break;
                //find by id
                case 2:
                    findById(departmentController);
                    view.print(SEARCH_OPTIONS);
                    break;
                case 3:
                    findDepartmentsByParameters();
                    view.print(SEARCH_OPTIONS);
                    break;
                case 0:
                    return;
            }
        }
    }

    /**
     * Method to let user modify database entries
     *
     * @param bc controller object for which the operation should be performed
     */
    private void update(BaseController bc) {
        view.print("Enter ID of the " + bc.getEntityName() + " to edit: ");
        int id = view.getIntInput();
        view.endl();
        try {
            if (bc.update(id)) {
                view.printRed("\nUpdated successfully\n");
                view.sleep(10);
            } else {
                view.printRed("No changes made.\n");
            }
        } catch (NoEntityException e) {
            view.printRed(e.getMessage() + "\n");
        } finally {
            view.sleep(100);
            view.print("\nPress ENTER key to continue...\n");
            view.getStringInput();
        }
    }

    /**
     * Method to let user delete database entries
     *
     * @param controller controller object for which the operation should be
     * performed
     */
    private void delete(BaseController controller) {
        view.print("\nEnter an ID of the " + controller.getEntityName() + " you want to delete\n");
        int id = view.getIntInput();
        try {
            if (controller instanceof DepartmentController) {
                List<Employee> list = employeeController.findByParameters(null,
                        (Department) departmentController.findById(id),
                        null);
                if (!list.isEmpty()) {
                    view.printRed("The Department cannot be deleted as long as there are employees assigned to it!\n");
                    view.sleep(100);
                    view.print("Press ENTER to continue...");
                    view.getStringInput();
                    return;
                }
            }
            controller.delete(id);
            view.printRed("\n" + controller.getEntityName().toUpperCase() + " entry deleted successfully!\n");
        } catch (NoEntityException ex) {
            view.printRed(ex.getMessage());
        } finally {
            view.sleep(100);
            view.print("\nPress ENTER key to continue...\n");
            view.getStringInput();
        }

    }

    /**
     * Method to let user add employees to the database
     */
    private void addEmployee() {
        Employee newEmployee = new Employee();
        view.print("Enter the full name of the employee: ");
        while (true) {
            String empName = view.getStringInput();
            view.endl();
            if (empName.length() == 0) {
                view.printRed("Please enter a valid name!\n");
                view.sleep(5);
            } else {
                newEmployee.setFullName(empName);
                break;
            }
        }
        view.print("Enter the birthday of the employee DD/MM/YYYY : ");
        int year, month, day;
        while (true) {
            String empBd = view.getStringInput();
            String date[] = empBd.split("/");
            if (date.length < 3) {
                view.printRed("\n Invalid date format please try again DD/MM/YYYY: ");
                continue;
            }
            try {
                day = Integer.parseInt(date[0]);
                month = Integer.parseInt(date[1]) - 1;
                year = Integer.parseInt(date[2]) - 1900;
                break;
            } catch (NumberFormatException nfe) {
                view.printRed("\nInvalid date format please try again DD/MM/YYYY: ");
            }
        }

        view.endl();
        view.print("Enter an ID of the department to which you are adding the employee: ");
        while (true) {
            int depId = view.getIntInput();
            try {
                Department dpmnt = (Department) departmentController.findById(depId);
                newEmployee.setDepartment(dpmnt);
                break;
            } catch (NoEntityException ex) {

                view.print("\nDepartment with such id doesn't exist!\n");

                view.print("\nEnter 1 to print all the departments available\n"
                        + "Or enter anything to cancel the operation\n");
                int choice = view.getIntInput();
                if (choice == 1) {
                    List<Department> list = departmentController.findAll();
                    view.print(SEPARATOR_LINE);
                    for (Department d : list) {
                        view.print(d.toString() + SEPARATOR_LINE);
                    }
                    view.print(SEPARATOR_LINE);
                    view.endl();
                    view.print("Enter id of the department: ");
                } else {
                    return;
                }
            }
        }
        newEmployee.setBirthDate(new Date(year, month, day));
        try {
            employeeController.add(newEmployee);
            view.printRed("New employee added successfully\n");
        } catch (NullEntityException ex) {
            view.printRed("Employee was not saved as it is null!\n");
        } finally {
            view.sleep(100);
            view.print("\nPress ENTER key to continue...\n");
            view.getStringInput();
        }
    }

    /**
     * Method to let user add departments to the database
     */
    private void addDepartment() {
        Department newDepartment = new Department();
        view.print("Enter the name of the department: ");
        while (true) {
            String departmentName = view.getStringInput();
            view.endl();
            if (departmentName.length() == 0) {
                view.printRed("Please enter a valid name!\n");
                view.sleep(5);
            } else {
                newDepartment.setDepartmentName(departmentName);
                break;
            }
        }
        view.print("Enter the address of the department:");
        String departmentAddress = view.getStringInput();

        newDepartment.setAddress(departmentAddress);

        try {
            departmentController.add(newDepartment);
            view.printRed("New department added successfully\n");
        } catch (NullEntityException ex) {
            view.printRed("Department was not saved as it is null!\n");
        } finally {
            view.sleep(100);
            view.print("\nPress ENTER key to continue...\n");
            view.getStringInput();
        }

        //else if failed, probably won't fail
    }

    /**
     * Method to let user search database table by id
     *
     * @param controller controller object for which the operation should be
     * performed
     */
    private void findById(BaseController controller) {
        view.print("Enter the id of the " + controller.getEntityName() + ": ");
        int id = view.getIntInput();
        view.endl();
        try {
            Persistable emp = controller.findById(id);
            view.print(SEPARATOR_LINE);
            view.print(emp.toString());
            view.print(SEPARATOR_LINE);
            view.print("\nPress ENTER key to continue...\n");
            view.getStringInput();
        } catch (NoEntityException e) {
            view.printRed(e.getMessage());
            view.sleep(100);
            view.print("\nPress ENTER key to continue...\n");
            view.getStringInput();
        }
    }

    /**
     * Method to let user access a list of table entries
     *
     * @param controller controller object for which the operation should be
     * performed
     */
    private void findAll(BaseController controller) {

        List<Persistable> entries = controller.findAll();
        if (entries.isEmpty()) {
            view.print("\nThe list is empty");
        } else {
           
            for (Persistable p : entries) {
                view.print(SEPARATOR_LINE);
                view.print(p.toString());
            }
        }

        view.print("\n\nPress ENTER key to continue...\n");
        view.getStringInput();
    }

    /**
     * Method to let user access departments matching specific parameters
     */
    private void findDepartmentsByParameters() {
        view.print("\nEnter a part of the department name: ");
        String depName = view.getStringInput();

        view.print("\nEnter a part of the department address: ");
        String depAddress = view.getStringInput();

        if (depName.length() == 0) {
            depName = null;
        }
        if (depAddress.length() == 0) {
            depAddress = null;
        }
        List<Department> list = departmentController.findByParameters(depName, depAddress);
        if (list.isEmpty()) {
            view.print("\nNo results matching the parameters found...\n");
        } else {
            for (Department d : list) {
                view.print(SEPARATOR_LINE);
                view.print(d.toString() + SEPARATOR_LINE);
                
            }
        }

        view.print("\n\nPress ENTER to continue...");
        view.getStringInput();
    }

    /**
     * Method to let user access employees matching specific parameters
     */
    private void findEmployeesByParameters() {
        Department department;
        String bDateString;
        Date bDate;
        view.print("\nEnter a part of employee's full name: ");
        String empName = view.getStringInput();

        view.print("\n\nEnter employee's department id: ");
        int depId = 0;
        try {
            depId = Integer.parseInt(view.getStringInput());
        } catch (NumberFormatException e) {
            view.print("\nDepartment not found..\n");
        }
        if (depId == 0) {
            department = null;
        } else {
            try {
                department = (Department) departmentController.findById(depId);
            } catch (NoEntityException ex) {
                view.print("\nDepartment not found..\n");
                department = null;
            }
        }

        view.print("\n\nEnter employee's day of birth (DD/MM/YYYY): ");
        int year, month, day;
        while (true) {
            bDateString = view.getStringInput();
            if (bDateString.length() == 0) {
                bDate = null;
                break;
            }
            String date[] = bDateString.split("/");
            try {
                day = Integer.parseInt(date[0]);
                month = Integer.parseInt(date[1]) - 1;
                year = Integer.parseInt(date[2]) - 1900;
                bDate = new Date(year, month, day);
                break;
            } catch (NumberFormatException nfe) {
                view.printRed(nfe.getMessage() + "\n Invalid date format please try again DD/MM/YYYY: ");
            }
        }

        List<Employee> list = employeeController.findByParameters(empName, department, bDate);
        if (list.isEmpty()) {
            view.print("\nNo results matching the parameters found...\n");
        } else {
            for (Employee e : list) {
                view.print(SEPARATOR_LINE);
                view.print(e.toString() + SEPARATOR_LINE);
                
            }
        }

        view.print("\n\nPress ENTER to continue...");
        view.getStringInput();

    }

    /**
     * Allows user to remove all the data from a table Equivalent of SQL
     * truncate operation
     *
     * @param controller controller object for which the operation should be
     * performed
     */
    private void truncate(BaseController controller) {
        view.print("Please confirm the operation by typing \"YES\": ");
        if (controller instanceof DepartmentController) {
            List<Employee> list = employeeController.findAll();
            if (!list.isEmpty()) {
                view.printRed("The Departments cannot be deleted as long as there are employees assigned to them, delete the employees table first!!\n");
                view.sleep(100);
                view.print("Press ENTER to continue...");
                view.getStringInput();
                return;
            }
        }
        while (true) {
            String yesNo = view.getStringInput();
            if (yesNo.toLowerCase().equals("yes")) {
                controller.truncateTable();
                view.print("Table " + controller.getEntityName() + " cleared successfully!\nPress ENTER to continue...\n");
                view.getStringInput();
                return;
            } else {
                view.print("Operation cancelled...\nPress ENTER to continue...\n");
                view.getStringInput();
                return;
            }
        }

    }

}

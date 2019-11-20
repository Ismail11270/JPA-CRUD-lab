/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.ismoil.atajanov.jpalab;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pl.polsl.ismoil.atajanov.jpalab.controller.DepartmentController;
import pl.polsl.ismoil.atajanov.jpalab.controller.EmployeeController;
import pl.polsl.ismoil.atajanov.jpalab.menu.CrudMenu;
import pl.polsl.ismoil.atajanov.jpalab.view.View;
import pl.polsl.ismoil.atajanov.jpalab.model.Department;
/**
 * Main class of the application implementing a database table of Departments and Employees
 * Performing CRUD operations on the table
 * @author Ismoil Atajanov
 * @version 1.1
 */
public class Main {
    
    /**
     * Main method
     * @param args arguments passed to the program
     */
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabPU");
        View view = new View();
        
        EmployeeController ec = new EmployeeController(view,emf);
        DepartmentController dc = new DepartmentController(view,emf);
        CrudMenu crudMenu = new CrudMenu(view,ec,dc);
        crudMenu.printFirstPage();
        emf.close();
        
        
    }
}

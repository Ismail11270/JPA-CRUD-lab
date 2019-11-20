/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.ismoil.atajanov.jpalab.controller;



import java.util.List;
import pl.polsl.ismoil.atajanov.jpalab.model.Persistable;

import pl.polsl.ismoil.atajanov.jpalab.exceptions.NoEntityException;
import pl.polsl.ismoil.atajanov.jpalab.exceptions.NullEntityException;
import pl.polsl.ismoil.atajanov.jpalab.service.BaseService;
import pl.polsl.ismoil.atajanov.jpalab.view.View;

/**
 * Abstract controller holding common methods of all entities' controllers
 *
 * @see DepartmentController
 * @see EmployeeController
 * @author Ismoil Atajanov
 * @param <T> type of the entity
 * @version 1.1
 */
public abstract class BaseController<T extends Persistable> {

    /**
     * BaseService instance
     */
    BaseService service;

    /**
     * Object of View class
     *
     * @see View
     */
    View view;

    /**
     * Constructor
     *
     * @param view instance of view class
     * @see View
     * creation of EntityManager
     */
    BaseController(View view) {
        this.view = view;
    }

    /**
     * Provides access to a string name of the Controller's entity
     * Generally used for displaying messages related to each entity
     * @return a String name 
     */
    public String getEntityName() {
        return service.getEntityName();
    }
    
    
    

    /**
     * Modify an entry in the table
     *
     * @param id - id of the entry to modify
     * @return true if update was successful or false if no changes were made
     * @throws NoEntityException when no entry with give id was found
     */
    public abstract boolean update(int id) throws NoEntityException;

    /**
     * Implementation of SQL insert operation
     *
     * @param entity - entity object to insert
     * @throws NullEntityException at an attempt of persisting a null object
     */
    public void add(T entity) throws NullEntityException {
        service.add(entity);
    }

    /**
     * Implementation of SQL truncate operation Clearing the entire table
     */
    public void truncateTable() {
        service.truncate();
    }

    /**
     * Implementation of SQL delete operation
     *
     * @param id id of a database entry to delete
     * @throws NoEntityException if the entry with such id doesn't exist
     */
    public void delete(int id)
            throws NoEntityException {
        service.delete(id);
    }

    /**
     * Implementation of SQL SELECT operation using the primary key
     *
     * deleted
     * @param id id of the database entry to find
     * @return an entity object that was found
     * @throws NoEntityException if no entity with a given id exist
     */
    public T findById(int id) throws NoEntityException {
        return (T) service.getById(id);
    }

    /**
     * Implementation of findAll named query to get all the entries in a table
     *
     * @return a list of entity objects from a table
     */
    public List<T> findAll() {
        return service.getAll();
    }

    
}

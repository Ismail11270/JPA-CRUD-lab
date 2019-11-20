/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.ismoil.atajanov.jpalab.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import pl.polsl.ismoil.atajanov.jpalab.model.Persistable;
import pl.polsl.ismoil.atajanov.jpalab.exceptions.NoEntityException;
import pl.polsl.ismoil.atajanov.jpalab.exceptions.NullEntityException;

/**
 * Abstract class holding controls of em operations
 * @param <T> Entity class
 * @version 1.0
 * @author Ismail
 */
public abstract class BaseService<T extends Persistable> {

    /**
     * Entity manager instance
     */
    EntityManager em;

    /**
     * Constructor
     * @param emf EntityManagerFactory used to create EntityManager
     */
    BaseService(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }

    /**
     * Provides access to Class of the Controller's entity
     *
     * @return instance of Class of currently used entity
     */
    public abstract Class getEntityClass();

    /**
     * Provides access to findAll named query of entity classes
     *
     * @return named query string
     */
    abstract String getFindAllQuery();

     /**
     * Provides access to a string name of the Controller's entity
     * Generally used for displaying messages related to each entity
     * @return a String name 
     */
    public abstract String getEntityName();

    /**
     * Get entity by id
     * @param id of the entity in the db
     * @return object T
     * @throws NoEntityException if no entity with id exists
     */
    public T getById(int id) throws NoEntityException {
        T entity = (T) em.find(getEntityClass(), id);
        if (entity != null) {
            return entity;
        } else {
            throw new NoEntityException();
        }
    }

    /**
     * Get all elements of a T table
     * @return list of entities
     */
    public List<T> getAll() {
        return em.createNamedQuery(getFindAllQuery()).getResultList();
    }

    /**
     * Persist an entity into the table
     * @param entity object to persist
     * @throws NullEntityException  if entity is null
     */
    public void add(T entity) throws NullEntityException {
        if (entity != null) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } else {
            throw new NullEntityException();
        }
    }
    
    /**
     * SQL Truncate implementation
     * Clearing the entire table without dropping it
     */
    public void truncate() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM " + getEntityName() + " where id >= 0 ").executeUpdate();
        em.getTransaction().commit();
    }

    /**
     * Delete an entry from a table
     * @param id of the entry
     * @throws NoEntityException if no entry with given id exists in the table
     */
    public void delete(int id) throws NoEntityException{
        T entity = (T) getById(id);
        if (entity != null) {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } else {
            throw new NoEntityException("No entity found with such id");
        }
    }
    /**
     * Update parameters of an entity
     * @param updatedEntity updated entity object
     */
    public void update(T updatedEntity) {
        em.getTransaction().begin();
        em.merge(updatedEntity);
        em.getTransaction().commit();
    }
}

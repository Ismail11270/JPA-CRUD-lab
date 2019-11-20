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
 *
 * @author Ismail
 */
public abstract class BaseService<T extends Persistable> {

    EntityManager em;

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

    public T getById(int id) throws NoEntityException {
        T entity = (T) em.find(getEntityClass(), id);
        if (entity != null) {
            return entity;
        } else {
            throw new NoEntityException();
        }
    }

    public List<T> getAll() {
        return em.createNamedQuery(getFindAllQuery()).getResultList();
    }

    public void add(T entity) throws NullEntityException {
        if (entity != null) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } else {
            throw new NullEntityException();
        }
    }

    public void truncate() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM " + getEntityName() + " where id >= 0 ").executeUpdate();
        em.getTransaction().commit();
    }

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

    public void update(T updatedEntity) {
        em.getTransaction().begin();
        em.merge(updatedEntity);
        em.getTransaction().commit();
    }
}

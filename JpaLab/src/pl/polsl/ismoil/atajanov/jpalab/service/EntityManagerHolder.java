/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.ismoil.atajanov.jpalab.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * EntityManager holder class, providing access to the entityManager
 * @author Ismoil Atajanov
 * @version 1.0
 */
public class EntityManagerHolder {
    
    /**
     * Instance of EntityManager
     */
    EntityManager em;
    /**
     * Constructor generating EntityManager from EntityManagerFactory
     * @param emf used to create em
     */
    public EntityManagerHolder(EntityManagerFactory emf){
        em = emf.createEntityManager();
    }
    
    /**
     * em transaction beginner
     */
    void beginTranscation(){
        em.getTransaction().begin();
    }
    
    /**
     * em transaction committer
     */
    void endTransaction(){
        em.getTransaction().commit();
    }
    
    
    
    
}

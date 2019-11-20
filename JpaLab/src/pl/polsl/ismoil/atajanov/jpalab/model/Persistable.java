/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.ismoil.atajanov.jpalab.model;

/**
 * Interface used to mark a class an entity class (persistable)
 * @see Department
 * @see Employee
 * @author Ismoil Atajanov
 * @version 1.0
 */
public interface Persistable {
    /**
     * Persistable to String implementation
     * @return String as a combination of all entity's parameters
     */
    
    String toString();
    /**
     * Primary key setter
     * @return id that is a primary key of the table
     */
    Integer getId();
    
    /**
     * Primary key setter
     * @param id new id
     */
    void setId(Integer id);
}

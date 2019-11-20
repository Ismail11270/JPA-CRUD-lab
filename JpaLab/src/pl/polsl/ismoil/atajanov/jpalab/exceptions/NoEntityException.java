/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.ismoil.atajanov.jpalab.exceptions;

/**
 * Exception reports that an entity that is accessed doesn't exist
 * @author Ismoil Atajanov
 * @version 1.0
 */
public class NoEntityException extends Exception {
    /**
     * COnstructor with a message
     * @param message error message
     */
    public NoEntityException(String message){
        super(message);
    }
    
    /**
     * Default constructor
     */
    public NoEntityException(){
        this("Entity does not exist");
    }
}

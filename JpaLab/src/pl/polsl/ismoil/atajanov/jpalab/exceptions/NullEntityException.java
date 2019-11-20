/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.ismoil.atajanov.jpalab.exceptions;

/**
 * Exception reports an attempt of an operation on a null entity
 * @author Ismoil Atajanov
 * @version 1.0
 */
public class NullEntityException extends Exception {
    /**
     * Constructor with a message
     * @param message error message
     */
    public NullEntityException(String message){
        super(message);
    }
    
    /**
     * Default constructor
     */
    public NullEntityException(){
        this("Entity is null!");
    }
}

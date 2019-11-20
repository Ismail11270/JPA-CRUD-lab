/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.ismoil.atajanov.jpalab.view;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ismail
 */
public class View {
    /**
     * print a message to output stream
     * @param message text to print
     */
    public void print(String message) {
        System.out.print(message);
    }
    /**
     * Print a message to error stream, to notify about errors
     * @param message error text to print
     */
    public void printRed(String message){
        System.err.print(message);
    }
    
    /**
     * Get integer input from the user
     * @return integer entered by user, or 0 if an input is not an integer 
     */
    public int getIntInput() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }else if(scanner.hasNext()) return 0;
        else return 0; 
    }
    
    /**
     * Get text input from the user
     * @return string input
     */
    public String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return null;
        }
    }
    
    /**
     * Time delay
     * Used only to compensate the latency of printRed() method
     * @param ms time in milliseconds
     */
    public void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Prints line break to output stream
     */
    public void endl() {
        print("\n");
    }
}

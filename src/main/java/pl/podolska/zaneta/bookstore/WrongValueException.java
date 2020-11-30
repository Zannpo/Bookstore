/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.podolska.zaneta.bookstore;

/**
 *
 * @author Zanpo
 */
public class WrongValueException extends Exception{
    
     public WrongValueException()
    {
         super("Error! Invalid amount! We dont have such amount of the title in the store!"); 
                           
    }
    
    public WrongValueException(String message)
    {
        super(message);
    }
    
}

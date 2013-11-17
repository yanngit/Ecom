/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

public class EcomException extends Exception {
    private String message;
    
    public EcomException(String text){
        this.message = text;
    }
    
    public String getMessage(){
        return this.message;
    }
}

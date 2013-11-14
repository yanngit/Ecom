/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author bach
 */
public enum OrderStateEnum {
    VALIDATED("Validée"), 
    SENT("Envoyée"), 
    PAYED("Payée"),
    FAULTY("Erreur");
    
    private String orderState;
    
    private OrderStateEnum(String orderState){
        this.orderState = orderState;
    
    }
    
    @Override
    public String toString(){
        return this.orderState;
    }
    
}

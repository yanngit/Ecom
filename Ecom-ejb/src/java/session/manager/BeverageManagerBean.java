/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.manager;

import entity.BeverageEntity;
import exceptions.EcomException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.AbstractEntityManager;

@Stateless
public class BeverageManagerBean extends AbstractEntityManager<BeverageEntity>{
    
    @PersistenceContext (name="Ecom_PU")
    private EntityManager em;

    public BeverageManagerBean() {
        super(BeverageEntity.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }   
    
    public List<BeverageEntity> getUnavailableBeverages(){
        return em.createNamedQuery("findUnavailableBeverages").getResultList();
    }
    
    public List<BeverageEntity> getAvailableBeverages(){
        return em.createNamedQuery("findAvailableBeverages").getResultList();
    }
    
    public void decreaseQuantityOfBeverage(Long id, int quantity) throws EcomException {
        BeverageEntity beverage = find(id);
        int newQuantity = beverage.getQuantity() - quantity;
        if(newQuantity < 0) {
            throw new EcomException("The quantity of the beverage ["+id+"] can't be negative");
        }
        beverage.setQuantity(newQuantity);
        this.edit(beverage);
    }
    
    public void increaseQuantityOfBeverage(Long id, int quantity) {
        BeverageEntity beverage = find(id);
        int newQuantity = beverage.getQuantity() + quantity;
        beverage.setQuantity(newQuantity);
        this.edit(beverage);
    }
}

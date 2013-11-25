package session.manager;

import entity.DecorationEntity;
import exceptions.EcomException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.AbstractEntityManager;

@Stateless
public class DecorationManagerBean extends AbstractEntityManager<DecorationEntity> {
    @PersistenceContext (name="Ecom_PU")
    private EntityManager em;

    public DecorationManagerBean() {
        super(DecorationEntity.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     public void decreaseQuantityOfDecoration(Long id, int quantity) throws EcomException {
        DecorationEntity decoration = find(id);
        int newQuantity = decoration.getQuantity() - quantity;
        if(newQuantity < 0) {
            throw new EcomException("The quantity of the decoration ["+id+"] can't be negative");
        }
        decoration.setQuantity(newQuantity);
        this.edit(decoration);
    }
     
      public void increaseQuantityOfDecoration(Long id, int quantity) {
        DecorationEntity decoration = find(id);
        int newQuantity = decoration.getQuantity() + quantity;
        decoration.setQuantity(newQuantity);
        this.edit(decoration);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.manager;

import entity.ClientAccountEntity;
import entity.OrderEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.AbstractEntityManager;

/**
 *
 * @author bach
 */
@Stateless
public class OrderManagerBean extends AbstractEntityManager<OrderEntity> {
    @PersistenceContext (name="Ecom_PU")
    private EntityManager em;
    @EJB
    private ClientAccountManagerBean clientManager;

    public OrderManagerBean() {
        super(OrderEntity.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<OrderEntity> getOrdersOfAccount(ClientAccountEntity account){
        return em.createNamedQuery("getOrdersOfAddress").setParameter("surname", account.getDelivery_address().getSurname()).setParameter("first_name",account.getDelivery_address().getFirst_name()).setParameter("street", account.getDelivery_address().getStreet()).getResultList();
    }
}

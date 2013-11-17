/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.manager;

import entity.OrderEntity;
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

    public OrderManagerBean() {
        super(OrderEntity.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

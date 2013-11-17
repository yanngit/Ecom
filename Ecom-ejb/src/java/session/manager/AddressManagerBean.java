/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.manager;

import entity.AddressEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.AbstractEntityManager;

/**
 *
 * @author bach
 */
@Stateless
public class AddressManagerBean extends AbstractEntityManager<AddressEntity> {
    @PersistenceContext (name="Ecom_PU")
    private EntityManager em;

    public AddressManagerBean() {
        super(AddressEntity.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

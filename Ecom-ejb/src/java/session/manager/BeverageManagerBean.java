/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.manager;

import entity.BeverageEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.AbstractEntityManager;

/**
 *
 * @author Alexis BRENON <brenon.alexis@gmail.com>
 */
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
}

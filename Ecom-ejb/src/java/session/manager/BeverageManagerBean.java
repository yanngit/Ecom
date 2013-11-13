/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.manager;

import session.interfaces.BeverageManagerRemoteItf;
import entity.BeverageEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.AbstractEntityManager;

/**
 *
 * @author Alexis BRENON <brenon.alexis@gmail.com>
 */
@Stateless (name="beverageManager", mappedName="session/manager/beverageManager")
public class BeverageManagerBean extends AbstractEntityManager<BeverageEntity> implements BeverageManagerRemoteItf {
    
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

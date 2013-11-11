/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.BeverageEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.EntityManagerItf;

/**
 *
 * @author Alexis BRENON <brenon.alexis@gmail.com>
 */
@Stateless
@LocalBean
public class BeverageManagerBean implements EntityManagerItf<BeverageEntity> {
    @PersistenceContext (name="Ecom_PU")
    private EntityManager em;

    @Override
    public BeverageEntity find(Object id) {
        return em.find(BeverageEntity.class, id);
    }

    @Override
    public List<BeverageEntity> findAll() {
        return em.createQuery("SELECT b FROM BEVERAGE b").getResultList();
    }

    @Override
    public void create(BeverageEntity entity) {
        em.persist(entity);
    }

    @Override
    public void edit(BeverageEntity entity) {
        em.merge(entity);
    }

    @Override
    public void remove(BeverageEntity entity) {
        em.remove(entity);
    }

    
}

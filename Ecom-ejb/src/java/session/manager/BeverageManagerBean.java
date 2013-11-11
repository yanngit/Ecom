/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session.manager;

import entity.BeverageEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.EntityManagerItf;

/**
 *
 * @author yann
 */
@Stateless (name="BeverageManager", mappedName="session/BeverageManager")
public class BeverageManagerBean implements  EntityManagerItf<BeverageEntity> {
    @PersistenceContext (name="Ecom_PU")
    private EntityManager em;
    
    @Override
    public void create(BeverageEntity beverage) {
        em.persist(beverage);
    }

    @Override
    public void edit(BeverageEntity beverage) {
        em.merge(beverage);
    }

    @Override
    public void remove(BeverageEntity beverage) {
        em.remove(beverage);
    }

    @Override
    public BeverageEntity find(Object id) {
        return em.find(entity.BeverageEntity.class,id);
    }

    @Override
    public List<BeverageEntity> findAll() {
        return em.createQuery("select b from BEVERAGE b").getResultList();
    }
}

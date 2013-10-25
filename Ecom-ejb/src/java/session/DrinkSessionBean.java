/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Drink;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author yann
 */
@Stateless (name="DrinkSession", mappedName="session/DrinkSession")
public class DrinkSessionBean implements  DrinkFacadeRemote {
    @PersistenceContext
    private EntityManager em;
    @Override
    public void create(Drink drink) {
        em.persist(drink);
    }

    @Override
    public void edit(Drink drink) {
        em.merge(drink);
    }

    @Override
    public void remove(Drink drink) {
        em.remove(drink);
    }

    @Override
    public Drink find(Object id) {
        return em.find(entity.Drink.class,id);
    }

    @Override
    public List<Drink> findAll() {
        return em.createQuery("select d from Drink d").getResultList();
    }
    
    @Override
    public String toString(){
        return "Hello";
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.manager;

import entity.BeverageEntity;
import entity.CocktailEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.EntityManagerItf;

/**
 *
 * @author yann
 */
@Stateless (name="CocktailManager", mappedName="session/CocktailManager")
public class CocktailManagerBean implements  EntityManagerItf<CocktailEntity> {
    @PersistenceContext (name="Ecom_PU")
    private EntityManager em;
    
    @Override
    public void create(CocktailEntity cocktail) {
        em.persist(cocktail);
    }

    @Override
    public void edit(CocktailEntity cocktail) {
        em.merge(cocktail);
    }

    @Override
    public void remove(CocktailEntity cocktail) {
        em.remove(cocktail);
    }

    @Override
    public CocktailEntity find(Object id) {
        return em.find(entity.CocktailEntity.class,id);
    }

    @Override
    public List<CocktailEntity> findAll() {
        return em.createQuery("select c from COCKTAIL c").getResultList();
    }
}

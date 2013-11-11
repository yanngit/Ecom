/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.manager;

import entity.DecorationEntity;
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
public class DecorationManagerBean implements  EntityManagerItf<DecorationEntity> {
    @PersistenceContext (name="Ecom_PU")
    private EntityManager em;
    
    @Override
    public void create(DecorationEntity decoration) {
        em.persist(decoration);
    }

    @Override
    public void edit(DecorationEntity decoration) {
        em.merge(decoration);
    }

    @Override
    public void remove(DecorationEntity decoration) {
        em.remove(decoration);
    }

    @Override
    public DecorationEntity find(Object id) {
        return em.find(entity.DecorationEntity.class,id);
    }

    @Override
    public List<DecorationEntity> findAll() {
        return em.createQuery("select c from COCKTAIL c").getResultList();
    }
}

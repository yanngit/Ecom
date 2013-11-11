package session;

import entity.CocktailEntity;
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
public class CocktailManagerBean implements EntityManagerItf<CocktailEntity> {
    @PersistenceContext (name="Ecom_PU")
    private EntityManager em;
    
    @Override
    public CocktailEntity find(Object id) {
        return em.find(CocktailEntity.class, id);
    }

    @Override
    public List<CocktailEntity> findAll() {
        return em.createQuery("SELECT c FROM COCKTAIL c").getResultList();
    }

    @Override
    public void create(CocktailEntity entity) {
        em.persist(entity);
    }

    @Override
    public void edit(CocktailEntity entity) {
        em.merge(entity);
    }

    @Override
    public void remove(CocktailEntity entity) {
        em.remove(entity);
    }

}

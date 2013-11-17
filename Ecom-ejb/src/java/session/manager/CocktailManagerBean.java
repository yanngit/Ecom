package session.manager;

import entity.CocktailEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.AbstractEntityManager;

@Stateless
public class CocktailManagerBean extends AbstractEntityManager<CocktailEntity> {
    @PersistenceContext (name="Ecom_PU")
    private EntityManager em;

    public CocktailManagerBean() {
        super(CocktailEntity.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<CocktailEntity> getUnavailableCocktails(){
        return em.createNamedQuery("findUnavailableCocktails").getResultList();
    }
    
    public List<CocktailEntity> getAvailableCocktails(){
        return em.createNamedQuery("findAvailableCocktails").getResultList();
    }
}

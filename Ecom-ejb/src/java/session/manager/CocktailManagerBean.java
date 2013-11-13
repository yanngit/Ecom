package session.manager;

import entity.CocktailEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.AbstractEntityManager;

/**
 *
 * @author Alexis BRENON <brenon.alexis@gmail.com>
 */
@Stateless (name="cocktailManager", mappedName="session/manager/cocktailManager")
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
}

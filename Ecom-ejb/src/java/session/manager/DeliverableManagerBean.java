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
import pojo.AbstractEntityManager;
import pojo.Deliverable;

@Stateless
public class DeliverableManagerBean extends AbstractEntityManager<Deliverable> {
    
    @PersistenceContext(name = "Ecom_PU")
    private EntityManager em;

    public DeliverableManagerBean() {
        super(Deliverable.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<CocktailEntity> getAllCocktails(BeverageEntity beverage) {
        return em.createNamedQuery("getCocktailsForBeverage").setParameter("name", beverage.getName()).getResultList();
    }
    
    public List<CocktailEntity> getCocktailsByKeyWordsBeverage(String name) {
        return em.createNamedQuery("getCocktailsByKeyWordsBeverage").setParameter("name", name.toLowerCase()).getResultList();
    }
}

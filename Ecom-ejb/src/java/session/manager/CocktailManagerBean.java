package session.manager;

import entity.BeverageEntity;
import entity.CocktailEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Lob;
import javax.persistence.PersistenceContext;
import org.apache.jasper.tagplugins.jstl.ForEach;
import pojo.AbstractEntityManager;
import pojo.Deliverable;

/**
 *
 * @author Alexis BRENON <brenon.alexis@gmail.com>
 */
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
        
        List maListe= this.getIdDelivrables();
        return em.createNamedQuery("findAvailableCocktails")
                //.setParameter("cent", maListe)
                .getResultList();
        
    }
    
    public List<Long> getIdDelivrables(){         
        List <Long> listeU = new ArrayList<Long>();
   
        listeU = em.createNamedQuery("getIdDelivrables")
                .getResultList();   
         
        int x = 155;
        listeU.add((long)x);
        return listeU;
    }
}

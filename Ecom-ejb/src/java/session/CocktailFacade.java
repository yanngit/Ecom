/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Cocktail;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author sohnoun
 */
@Stateless
@LocalBean
public class CocktailFacade implements CocktailFacadeLocal, CocktailFacadeRemote{
    //@EJB (name="Cocktail")
    //private CocktailManager cocktailManager;
    
    @PersistenceContext (name="Ecom_PU")
    private EntityManager em;
    @Override
    public Cocktail getCocktail(long id) {
        try
    	{
            final String queryString = "SELECT DISTINCT c from Cocktail c where c.ID = ?"+id;
            Query query = em.createQuery(queryString);
            List<Cocktail> result = query.getResultList();
            System.out.println("total Cocktail:"+result.size());
            return result.get(0);
        }
        
        catch (RuntimeException re)
        {
            System.out.println("getCocktail() failed : \n msg:"+ re.getMessage());
            throw re;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
    @Override
    public List<Cocktail> getAvailableCocktails() {
        try
        {
            final String queryString = "select c from Cocktail c where c.QUANTITY > 0";
            Query query = em.createQuery(queryString);
            List<Cocktail> result = query.getResultList();
            System.out.println(" total Cocktail:"+result.size());
            return result;
        }
        catch (RuntimeException re)
        {
            System.out.println("getAvailableCocktails() failed : \n msg:"+ re.getMessage());
            throw re;
        }
    }

    @Override
    public List<Cocktail> getAnavailableCocktails() {
    try
        {
            final String queryString = "select c from Cocktail c";
            Query query = em.createQuery(queryString);
            List<Cocktail> result = query.getResultList();
            System.out.println("total Cocktail:"+result.size());
            return result;
        }
        catch (RuntimeException re)
        {
            System.out.println("getAvailableCocktails() failed : \n msg:"+ re.getMessage());
            throw re;
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}

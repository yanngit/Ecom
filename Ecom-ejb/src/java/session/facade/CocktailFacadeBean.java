/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.facade;

import entity.CocktailEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import session.interfaces.CocktailFacadeLocalItf;
import session.manager.CocktailManagerBean;


/**
 *
 * @author sohnoun
 * @author bach
 */
@Stateless (name="CocktailFacade", mappedName="session/CocktailFacade")
public class CocktailFacadeBean implements CocktailFacadeLocalItf {
    @EJB
    private CocktailManagerBean cocktailManager;
   
    @Override
    public CocktailEntity getCocktail(long id) {
        
            final String queryString = "SELECT DISTINCT c from Cocktail c where c.ID = ?"+id;
            List<CocktailEntity> result = cocktailManager.executeSelect(queryString);
            System.out.println("total Cocktail:"+result.size());
            return result.get(0);
    }
   
    
    @Override
    public List<CocktailEntity> getAvailableCocktails() {
        final String queryString = "select c from Cocktail c where c.QUANTITY > 0";
        List<CocktailEntity> result = cocktailManager.executeSelect(queryString);
        System.out.println(" total Cocktail:"+result.size());
        return result;
    }

    @Override
    public List<CocktailEntity> getUnavailableCocktails() {
        final String queryString = "select c from Cocktail c";
        List<CocktailEntity> result = cocktailManager.executeSelect(queryString);
        System.out.println("total Cocktail:"+result.size());
        return result;
    }

}

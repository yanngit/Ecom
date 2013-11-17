/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.facade;

import entity.BeverageEntity;
import entity.CocktailEntity;
import exceptions.EcomException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import session.interfaces.CartFacadeLocalItf;
import session.interfaces.ClientFacadeRemoteItf;
import session.manager.BeverageManagerBean;
import session.manager.CocktailManagerBean;

@Stateful
public class ClientFacadeBean implements ClientFacadeRemoteItf {
    @EJB
    private CocktailManagerBean cocktailManager;
    @EJB
    private BeverageManagerBean beverageManager;
    @EJB
    private CartFacadeLocalItf cart;

    @Override
    public List<CocktailEntity> getAllCocktails() {
        return cocktailManager.findAll();
    }

    @Override
    public List<CocktailEntity> getAvailableCocktails() {
        return  this.cocktailManager.getAvailableCocktails();
            }

    @Override
    public List<CocktailEntity> getUnavailableCocktails() {
        return  this.cocktailManager.getUnavailableCocktails();
    }
    
    @Override
    public List<BeverageEntity> getUnavailableBeverages() {
        return  this.beverageManager.getUnavailableBeverages();
    }
    
    @Override
    public List<BeverageEntity> getAllBeverages() {
        return beverageManager.findAll();
    }

    @Override
    public List<BeverageEntity> getAvailableBeverages() {
        return this.beverageManager.getAvailableBeverages();
    }

    @Override
    public CocktailEntity getCocktail(Long id) {
        return cocktailManager.find(id);
    }

    @Override
    public void addArticle(Long id) throws EcomException {
        /*Vérifie que le cocktail soit toujours disponible, sinon exception*/
        if(cocktailManager.getAvailabilityByCocktailId(id)) {
                /*Ajoute le cocktail au panier et update le prix du panier*/
                cart.addArticle(id);
                cocktailManager.decreaseQuantityOfCocktail(id, 1);
        }
        else {
            throw new EcomException("Impossible d'ajouter le cocktail ["+id+"] au panier, il n'est plus disponible.");
        }
    }
    
     @Override
    public void removeArticle(Long id) throws EcomException {
                cart.removeArticle(id);
                cocktailManager.increaseQuantityOfCocktail(id, 1);
    }
    
    @Override
    public List<CocktailEntity> getCart() {
        return cart.getCocktails();
    }

    @Override
    public List<CocktailEntity> getMostPopularCocktails() {
        return cocktailManager.getMostPopularCocktails();
    }
}

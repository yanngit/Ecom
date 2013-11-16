/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.facade;

import entity.BeverageEntity;
import entity.CocktailEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import session.interfaces.ClientFacadeRemoteItf;
import session.manager.BeverageManagerBean;
import session.manager.CocktailManagerBean;

/**
 *
 * @author yann
 */
@Stateless
public class ClientFacadeBean implements ClientFacadeRemoteItf {
    @EJB
    private CocktailManagerBean cocktailManager;
    @EJB
    private BeverageManagerBean beverageManager;

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
}

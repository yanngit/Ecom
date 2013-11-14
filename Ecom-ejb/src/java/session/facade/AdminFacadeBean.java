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
import javax.ejb.LocalBean;
import session.interfaces.AdminFacadeRemoteItf;
import session.interfaces.BeverageFacadeLocalItf;

/**
 *
 * @author yann
 */
@Stateless
@LocalBean
public class AdminFacadeBean implements AdminFacadeRemoteItf {
    @EJB
    private BeverageFacadeLocalItf beverageFacade;
    
    @Override
    public void addBeverage(BeverageEntity beverage) {
        beverageFacade.create(beverage);
    }

    @Override
    public void removeBeverage(BeverageEntity beverage) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCocktail(CocktailEntity cocktail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeCocktail(CocktailEntity cocktail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CocktailEntity> getAllCocktails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CocktailEntity> getAvailableCocktails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CocktailEntity> getUnavailableCocktails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<BeverageEntity> getAllBeverages() {
        return beverageFacade.getAllBeverages();
    }

    @Override
    public List<BeverageEntity> getUnavailableBeverages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BeverageEntity> getAvailableBeverages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

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
import session.interfaces.BeverageFacadeLocalItf;
import session.interfaces.ClientFacadeRemoteItf;
import session.interfaces.CocktailFacadeLocalItf;

/**
 *
 * @author yann
 */
@Stateless
public class ClientFacadeBean implements ClientFacadeRemoteItf {
    @EJB
    private CocktailFacadeLocalItf cocktailFacade;
    @EJB
    private BeverageFacadeLocalItf beverageFacade;

    @Override
    public List<CocktailEntity> getAllCocktails() {
        List<CocktailEntity> list = this.cocktailFacade.getAvailableCocktails();
        list.addAll(this.cocktailFacade.getUnavailableCocktails());
        return list;
    }

    @Override
    public List<CocktailEntity> getAvailableCocktails() {
        return  this.cocktailFacade.getAvailableCocktails();
            }

    @Override
    public List<CocktailEntity> getUnavailableCocktails() {
        return  this.cocktailFacade.getUnavailableCocktails();
    }
    
    @Override
    public List<BeverageEntity> getUnavailableBeverages() {
        return  this.beverageFacade.getUnavailableBeverages();
    }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<BeverageEntity> getAllBeverages() {
        return beverageFacade.getAllBeverages();
    }

    @Override
    public List<BeverageEntity> getAvailableBeverages() {
        return this.beverageFacade.getAvailableBeverages();
    }

}

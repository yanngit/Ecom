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
import session.manager.BeverageManagerBean;

/**
 *
 * @author yann
 */
@Stateless
public class BeverageFacadeBean implements BeverageFacadeLocalItf {
    @EJB
    private BeverageManagerBean manager;
    
   

    @Override
    public void create(BeverageEntity entity) {
        manager.create(entity);
    }

    @Override
    public List<BeverageEntity> getUnavailableBeverages() { 
       return manager.getUnavailableBeverage();
    }

    @Override
    public List<BeverageEntity> getAllBeverages() {
        return manager.findAll(); 
    }

    @Override
    public List<BeverageEntity> getAvailableBeverages() {
        final String queryString = "select b from BeverageEntity b";
        List<BeverageEntity> result = manager.executeSelect(queryString);
        return result;
    }
   

}

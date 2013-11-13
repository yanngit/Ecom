/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.facade;

import entity.BeverageEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import session.interfaces.BeverageFacadeRemoteItf;
import session.manager.BeverageManagerBean;

/**
 *
 * @author yann
 */
@Stateless (name="BeverageFacade", mappedName="session/BeverageFacade")
public class BeverageFacadeBean implements BeverageFacadeRemoteItf {
    @EJB
    private BeverageManagerBean manager;
    
    public List<BeverageEntity> getAllDrinks(){
        List<BeverageEntity> res = manager.findAll();
        return res;
    }

    @Override
    public void create(BeverageEntity entity) {
        manager.create(entity);
    }
   

}

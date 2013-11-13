/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import entity.BeverageEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import session.interfaces.BeverageManagerRemoteItf;

/**
 *
 * @author yann
 */
@ManagedBean(name="dataManagedBean")
@RequestScoped
public class DataManagedBean {

   // private @EJB CocktailFacadeLocalItf drink;
    @EJB (name="beverageManager")
    private BeverageManagerRemoteItf beverage;
            
    public DataManagedBean () {
        super();
    }
    
   /* public List<CocktailEntity> getAvailableCocktails(){
        return drink.getAvailableCocktails();
    }*/
    
    public List<BeverageEntity> getListDrinks(){
        return beverage.findAll();
    }
}

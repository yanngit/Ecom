/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import entity.BeverageEntity;
import entity.CocktailEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import session.interfaces.ClientFacadeRemoteItf;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author yann
 */
@ManagedBean(name="dataManagedBean")
@RequestScoped
public class DataManagedBean {

   // private @EJB CocktailFacadeLocalItf drink;
    @EJB
    private ClientFacadeRemoteItf client;
            
    public DataManagedBean () {
        super();
    }
    
    public CocktailEntity getCocktail(Long id) throws Exception {
        throw NotImplementedException("*** ECOM *** : TODO get Cocktail with it's ID.");
    }
    
   public List<CocktailEntity> getListCocktails(){
        return client.getAllCocktails();
    }
    
    public List<BeverageEntity> getListBeverages(){
        return client.getAllBeverages();
    }
    
    public List<BeverageEntity>  getListAvailableBeverages(){
        return client.getAvailableBeverages();
    }
    
    public List<BeverageEntity>  getListUnavailableBeverages(){
        return client.getUnavailableBeverages();
    }

    private Exception NotImplementedException(String _ecom___todo_get_Cocktail_with_its_ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

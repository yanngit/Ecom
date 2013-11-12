/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import entity.CocktailEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import session.interfaces.CocktailFacadeLocalItf;

/**
 *
 * @author yann
 */
@ManagedBean(name="dataManagedBean")
@RequestScoped
public class DataManagedBean {

    private @EJB CocktailFacadeLocalItf drink;
    
    public DataManagedBean () {
        super();
    }
    
    public List<CocktailEntity> getAvailableCocktails(){
        return drink.getAvailableCocktails();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import entity.BeverageEntity;
import entity.CocktailEntity;
import exceptions.EcomException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import session.facade.CartFacadeBean;
import session.interfaces.CartFacadeLocalItf;
import session.interfaces.ClientFacadeRemoteItf;

/**
 *
 * @author yann
 */
@ManagedBean(name = "dataManagedBean")
@RequestScoped
public class DataManagedBean {

    @EJB
    private ClientFacadeRemoteItf client;
    @EJB
    private CartFacadeLocalItf cart;

    public DataManagedBean() {
        super();
    }

    public CocktailEntity getCocktail(Long id) throws Exception {
        return client.getCocktail(id);
    }

    public List<CocktailEntity> getListCocktails() {
        return client.getAllCocktails();
    }

    public List<BeverageEntity> getListBeverages() {
        return client.getAllBeverages();
    }

    public List<BeverageEntity> getListAvailableBeverages() {
        return client.getAvailableBeverages();
    }

    public List<BeverageEntity> getListUnavailableBeverages() {
        return client.getUnavailableBeverages();
    }
    
    public void addArticleToCart(Long id) throws EcomException {
        cart.addArticle(id);
    }
    
    public List<CocktailEntity> getCart() {
        return cart.getCocktails();
    }
}

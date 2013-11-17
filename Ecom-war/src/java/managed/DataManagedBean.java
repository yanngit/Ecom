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
import javax.faces.bean.SessionScoped;
import session.interfaces.ClientFacadeRemoteItf;

/**
 *
 * @author yann
 */
@ManagedBean(name = "dataManagedBean")
@SessionScoped
public class DataManagedBean {

    @EJB
    private ClientFacadeRemoteItf client;

    public DataManagedBean() {
        super();
    }

    public CocktailEntity getCocktail(Long id) throws Exception {
        return client.getCocktail(id);
    }

    public List<CocktailEntity> getListCocktails() {
        return client.getAllCocktails();
    }
    
    public List<CocktailEntity> getListAvailableCocktails() {
        return client.getAvailableCocktails();
    }
    
    public List<CocktailEntity> getListUnavailableCocktails() {
        return client.getUnavailableCocktails();
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
        client.addArticle(id);
    }
    
    public void removeArticleToCart(Long id) throws EcomException {
        client.removeArticle(id);
    }
    
    public List<CocktailEntity> getCart() {
        return client.getCart();
    }
    
    public List<CocktailEntity> getListMostPopularCocktails() {
        return client.getMostPopularCocktails();
    }
    
    public List<CocktailEntity> getListNewestCocktails() {
        return client.getNewestCocktails();
    }
    
    public List<CocktailEntity> getListCocktailsWithAlcohol() {
        return client.getCocktailsWithAlcohol();
    }
    
    public List<CocktailEntity> getListCocktailsWithoutAlcohol() {
        return client.getCocktailsWithoutAlcohol();
    }
    
    public List<CocktailEntity> listCocktailsByFirstLetter(char letter) {
        return client.getCocktailsByFirstLetter(letter);
    }
    
    public List<CocktailEntity> listCocktailsByName(String name) {
        return client.getCocktailsByName(name);
    }

}

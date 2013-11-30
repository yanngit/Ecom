/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import entity.BeverageEntity;
import entity.CocktailEntity;
import exceptions.EcomException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pojo.Deliverable;
import session.interfaces.ClientFacadeRemoteItf;

/**
 *
 * @author yann
 */
@ManagedBean(name = "dataManagedBean")
@SessionScoped
public class DataManagedBean {

    /* Main facade to interact with Change to the application */
    @EJB
    private ClientFacadeRemoteItf client;
    /* Save the cocktail we want to see details */
    private CocktailEntity currentCocktail = null;

    public DataManagedBean() {
        super();
    }

    /* Navigate to the cocktailDetails.xhtml page and record the cocktail we
     * want to watch.
     */
    public String displayCocktailDetails(CocktailEntity cocktail) {
        this.currentCocktail = getCocktailFull(cocktail);
        return "cocktailDetails.xhtml?faces-redirect=true";
    }

    /* All getters */
    public CocktailEntity getCurrentCocktail() {
        return currentCocktail;
    }

    public CocktailEntity getCocktail(Long id) throws Exception {
        return client.getCocktail(id);
    }

    public CocktailEntity getCocktailFull(Long id) {
        return client.getCocktailFull(id);
    }

    public CocktailEntity getCocktailFull(CocktailEntity cocktail) {
        return client.getCocktailFull(cocktail.getID());
    }

    public List<Deliverable> getCocktailDeliverables(Long id) {
        return getCocktailFull(id).getDeliverables();
    }

    public List<Deliverable> getCocktailDeliverables(CocktailEntity cocktail) {
        List<Deliverable> deliverables;
        deliverables = getCocktailFull(cocktail).getDeliverables();
        return deliverables;
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

    public List<CocktailEntity> getCartContent() {
        return client.getCartContent();
    }

    public Integer getCartLength() {
        return client.getCartSize();
    }

    public Float getCartPrice() {
        return client.getCartPrice();
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

    /* Setters, symbolizing an action */
    public void addArticleToCart(Long id) throws EcomException {
        client.addArticle(id);
    }

    public void removeArticleToCart(Long id) throws EcomException {
        client.removeArticle(id);
    }

    public List<CocktailEntity> listCocktailsByFirstLetter(char letter) {
        return client.getCocktailsByFirstLetter(letter);
    }

    public List<CocktailEntity> listCocktailsByName(String name) {
        return client.getCocktailsByName(name);
    }

    /* Nice function, but doesn't respect convention name, or application
     * architecture... */
    public List<List<CocktailEntity>> listAllCocktailsByFirstLetter() {
        List<List<CocktailEntity>> list = new ArrayList<>();
        for (char ch : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            if (client.getCocktailsByFirstLetter(ch).size() > 0) {
                list.add(client.getCocktailsByFirstLetter(ch));
            }
        }
        return list;
    }

    public List<List<CocktailEntity>> listAllVirginCocktailsByFirstLetter() {
        List<List<CocktailEntity>> list = new ArrayList<>();
        for (char ch : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            if (client.getCocktailsWithoutAlcoholByFirstLetter(ch).size() > 0) {
                list.add(client.getCocktailsWithoutAlcoholByFirstLetter(ch));
            }
        }
        return list;
    }

    public List<List<CocktailEntity>> listAllCocktailsWithAlcoholByFirstLetter() {
        List<List<CocktailEntity>> list = new ArrayList<>();
        for (char ch : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            if (client.getCocktailsWithAlcoholByFirstLetter(ch).size() > 0) {
                list.add(client.getCocktailsWithAlcoholByFirstLetter(ch));
            }
        }
        return list;
    }
}

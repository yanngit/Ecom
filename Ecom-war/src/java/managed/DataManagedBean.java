/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import entity.AddressEntity;
import entity.BeverageEntity;
import entity.CocktailEntity;
import entity.DecorationEntity;
import entity.OrderEntity;
import exceptions.EcomException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pojo.Deliverable;
import pojo.OrderStateEnum;
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
    private AddressEntity entireAddress = null;
    private OrderEntity order=null;
    private int quantity = 1;
    private CocktailEntity cocktailQuantity = null;

    public DataManagedBean() {
        super();
    }
    
    /*récupérer le nb de cocktail de type cocktail dans le caddie*/
    public String getQuantityForCocktailInCart(CocktailEntity cocktail){
        return client.getQuantityForCocktail(cocktail);
    }

    public void increaseQuantity(CocktailEntity cocktail) {
        /*Si c'est la première incrémentation pas de problème*/
        if (cocktailQuantity == null) {
            cocktailQuantity = cocktail;
            quantity++;
        } else {
            /*Si on incrémente le meme cocktail OK*/
            if (cocktailQuantity.equals(cocktail)) {
                quantity++;
            } /*Sinon on repars à 2 et on oublie ce qui c'est passé avant*/ else {
                cocktailQuantity = cocktail;
                quantity = 2;
            }
        }
    }

    public void decreaseQuantity(CocktailEntity cocktail) {
        if (cocktailQuantity != null) {
            if (cocktailQuantity.equals(cocktail) && quantity > 1) {
                quantity--;
            }
        }
    }

    /*Récupérer la quantity a afficher pour la mise en panier, local au bean*/
    public String getQuantityForCocktail(CocktailEntity cocktail) {
        if (cocktailQuantity != null) {
            if (cocktailQuantity.equals(cocktail)) {
                return String.valueOf(quantity);
            }
        }
        return "1";
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

    public List<DecorationEntity> getCocktailDecorations(Long id) {
        return client.getCocktailDecorations(id);
    }

    public List<BeverageEntity> getCocktailBeverages(CocktailEntity cocktail) {
        return client.getCocktailBeverages(cocktail);
    }

    public List<Deliverable> getCocktailDeliverables(Long id) {
        return getCocktailFull(id).getDeliverables();
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
    public String addArticleToCart(CocktailEntity cocktail) throws EcomException {
        int qty = 1;
        if (cocktailQuantity != null) {
            if (cocktailQuantity.equals(cocktail)) {
                qty = quantity;
            }
        }
        client.addArticleToCart(cocktail.getID(), qty);
        quantity = 1;
        cocktailQuantity = null;
        return "index.xhtml?faces-redirect=true";
    }

    public void removeArticleToCart(Long id) throws EcomException {
        client.removeArticleFromCart(id);
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
    
    //ajouter par bach
    public void creatOrder(String firstName,String lastName, String street, String postalCode, String city){        
        //System.out.println(city);
        entireAddress = new AddressEntity();
        entireAddress.setFirst_name(firstName);
        entireAddress.setSurname(lastName);
        entireAddress.setStreet(street);
        entireAddress.setPostal_code(postalCode);
        entireAddress.setCity(city);
        entireAddress.setCountry("France");
        
        order = new OrderEntity();
        List<OrderEntity> listOrder = new ArrayList<>();
        List<AddressEntity> listAddress = new ArrayList<>();
        
        client.addAddress(entireAddress);
        listAddress.add(client.getAddress(entireAddress.getId()));
        
        order.setAddresses(listAddress);
        order.setCocktails(client.getCartContent());
        order.setStatus(OrderStateEnum.PAYED);
        listOrder.add(order);
        client.addAddress(entireAddress);
        client.addOrder(order);
       
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.facade;

import entity.AddressEntity;
import entity.BeverageEntity;
import entity.CocktailEntity;
import entity.DecorationEntity;
import entity.OrderEntity;
import exceptions.EcomException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import session.interfaces.CartFacadeLocalItf;
import session.interfaces.ClientFacadeRemoteItf;
import session.manager.AddressManagerBean;
import session.manager.BeverageManagerBean;
import session.manager.CocktailManagerBean;
import session.manager.OrderManagerBean;

@Stateful
public class ClientFacadeBean implements ClientFacadeRemoteItf {

    @EJB
    private CocktailManagerBean cocktailManager;
    @EJB
    private BeverageManagerBean beverageManager;
    @EJB
    private CartFacadeLocalItf cart;
    @EJB
    private OrderManagerBean orderManager;
    @EJB
    private AddressManagerBean addressManager;

    @Override
    public List<CocktailEntity> getAllCocktails() {
        return cocktailManager.findAll();
    }

    @Override
    public List<CocktailEntity> getAvailableCocktails() {
        return this.cocktailManager.getAvailableCocktails();
    }

    @Override
    public List<CocktailEntity> getUnavailableCocktails() {
        return this.cocktailManager.getUnavailableCocktails();
    }

    @Override
    public List<BeverageEntity> getUnavailableBeverages() {
        return this.beverageManager.getUnavailableBeverages();
    }

    @Override
    public List<BeverageEntity> getAllBeverages() {
        return beverageManager.findAll();
    }

    @Override
    public List<BeverageEntity> getAvailableBeverages() {
        return this.beverageManager.getAvailableBeverages();
    }

    @Override
    public CocktailEntity getCocktail(Long id) {
        return cocktailManager.find(id);
    }

    @Override
    public CocktailEntity getCocktailFull(Long id) {
        CocktailEntity cocktail = cocktailManager.find(id);
        /* Force deliverables list instanciation */
        cocktail.getDeliverables().size();
        return cocktail;
    }

    @Override
    public void addArticle(Long id) throws EcomException {
        /*Vérifie que le cocktail soit toujours disponible, sinon exception*/
        if (cocktailManager.getAvailabilityByCocktailId(id)) {
            /*Ajoute le cocktail au panier et update le prix du panier*/
            cart.addArticle(id);
            /*A faire dans la validate panier*/
            //cocktailManager.decreaseQuantityOfCocktail(id, 1);
        } else {
            throw new EcomException("Impossible d'ajouter le cocktail [" + id + "] au panier, il n'est plus disponible.");
        }
    }

    @Override
    public void removeArticle(Long id) throws EcomException {
        cart.removeArticle(id);
        cocktailManager.increaseQuantityOfCocktail(id, 1);
    }

    @Override
    public List<CocktailEntity> getCartContent() {
        return cart.getCocktails();
    }

    @Override
    public List<CocktailEntity> getMostPopularCocktails() {
        return cocktailManager.getMostPopularCocktails();
    }

    @Override
    public List<CocktailEntity> getNewestCocktails() {
        return cocktailManager.getNewestCocktails();
    }

    @Override
    public List<CocktailEntity> getCocktailsWithAlcohol() {
        return cocktailManager.getCocktailsWithAlcohol();
    }

    @Override
    public List<CocktailEntity> getCocktailsWithoutAlcohol() {
        return cocktailManager.getVirginCocktails();
    }

    @Override
    public List<CocktailEntity> getCocktailsByFirstLetter(char letter) {
        return cocktailManager.getCocktailsByFirstLetter(letter);
    }

    @Override
    public List<CocktailEntity> getCocktailsByName(String name) {
        return cocktailManager.getCocktailsByName(name);
    }

    @Override
    public List<CocktailEntity> getCocktailsWithAlcoholByFirstLetter(char letter) {
        return cocktailManager.getCocktailsWithAlcoholByFirstLetter(letter);
    }

    @Override
    public List<CocktailEntity> getCocktailsWithoutAlcoholByFirstLetter(char letter) {
        return cocktailManager.getVirginCocktailsByFirstLetter(letter);
    }

    @Override
    public Float getCartPrice() {
        return cart.getPrice();
    }

    @Override
    public Integer getCartSize() {
        return cart.getSize();
    }
    
    @Override
    public List<BeverageEntity> getCocktailBeverages(Long id) {
        return cocktailManager.getCocktailBeverages(id);
    }
    
    @Override
    public List<DecorationEntity> getCocktailDecorations(Long id) {
        return cocktailManager.getCocktailDecorations(id);
    } 

    @Override
    public OrderEntity addOrder(OrderEntity o) {
        return orderManager.create(o);
    }

    @Override
    public AddressEntity addAddress(AddressEntity address) {
        return addressManager.create(address);
    }

    @Override
    public AddressEntity getAddress(Long id) {
        return addressManager.find(id);
    }

    @Override
    public OrderEntity getOrder(Long id) {
        return orderManager.find(id);
    }
}

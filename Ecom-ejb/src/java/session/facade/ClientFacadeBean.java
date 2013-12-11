/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.facade;

import entity.AddressEntity;
import entity.BeverageEntity;
import entity.ClientAccountEntity;
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
import session.manager.ClientAccountManagerBean;
import session.manager.CocktailManagerBean;
import session.manager.OrderManagerBean;

@Stateful
public class ClientFacadeBean implements ClientFacadeRemoteItf {

    @EJB
    private CocktailManagerBean cocktailManager;
    @EJB
    private BeverageManagerBean beverageManager;
    @EJB
    private AddressManagerBean addressManager;
    @EJB
    private OrderManagerBean orderManager;
    @EJB
    private ClientAccountManagerBean clientAccountManager;
    @EJB
    private CartFacadeLocalItf cart;
    private ClientAccountEntity account = null;

    /*
     * Operation on Deliverables
     */
    /* Beverages */
    @Override
    public List<BeverageEntity> getUnavailableBeverages() {
        return this.beverageManager.getUnavailableBeverages();
    }

    @Override
    public List<BeverageEntity> getAvailableBeverages() {
        return this.beverageManager.getAvailableBeverages();
    }

    @Override
    public List<BeverageEntity> getCocktailBeverages(CocktailEntity cocktail) {
        return cocktailManager.getCocktailBeverages(cocktail);
    }

    @Override
    public List<DecorationEntity> getCocktailDecorations(Long id) {
        return cocktailManager.getCocktailDecorations(id);
    }


    /*
     * Operation on Cocktails (fetching)
     */
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
    public CocktailEntity getCocktail(Long id) {
        /* Here, the deliverables list will not be instanciated and serialized.
         */
        return cocktailManager.find(id);
    }

    @Override
    public CocktailEntity getCocktailFull(CocktailEntity cocktail) {
        CocktailEntity cocktail1 = cocktailManager.find(cocktail.getID());
        /* Force deliverables list instanciation for serialization */
        cocktail1.getDeliverables().size();
        return cocktail1;
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

    /*
     * Operation on the cart.
     */
    @Override
    public void addArticleToCart(Long id, int qty) throws EcomException {
        /*Vérifie que le cocktail soit toujours disponible, sinon exception*/
        if (cocktailManager.getAvailabilityByCocktailId(id)) {
            /*Ajoute le cocktail au panier et update le prix du panier*/
            cart.addArticle(id, qty);
            /*A faire dans la validate panier*/
            //cocktailManager.decreaseQuantityOfCocktail(id, 1);
        } else {
            throw new EcomException("Impossible d'ajouter le cocktail [" + id + "] au panier, il n'est plus disponible.");
        }
    }

    @Override
    public void removeArticleFromCart(Long id) throws EcomException {
        cart.removeArticle(id);
    }

    @Override
    public Float getCartPrice() {
        return cart.getPrice();
    }

    @Override
    public Integer getCartSize() {
        return cart.getSize();
    }

    /*
     * Account creation functions
     */
    @Override
    public AddressEntity addAddress(AddressEntity address) {
        return addressManager.create(address);
    }

    @Override
    public OrderEntity addOrder(OrderEntity o) {
        return orderManager.create(o);
    }


    @Override
    public AddressEntity getAddress(Long id) {
        return addressManager.find(id);
    }

    @Override
    public OrderEntity getOrder(Long id) {
        return orderManager.find(id);
    }

    @Override
    public ClientAccountEntity addClient(ClientAccountEntity client) {
        return clientAccountManager.create(client);
    }

    @Override
    public void terminateTransactions() {
        addressManager.flush();
        orderManager.flush();
        clientAccountManager.flush();
    }

    @Override
    public String getQuantityForCocktail(CocktailEntity cocktail) {
        return cart.getQuantityForCocktail(cocktail);
    }

    @Override
    public List<BeverageEntity> getCocktailBeverages(Long id) {
        return cocktailManager.getCocktailBeverages(getCocktail(id));
    }

    @Override
    public ClientAccountEntity connect(String login, String password) {
        if(account == null){
            account = clientAccountManager.getAccountByAuthentification(login,password);
        }
        return account;
    }

    @Override
    public void clearCart() {
        cart.emptyCart();
    }

    @Override
    public List<OrderEntity> getOrdersOfAccount(ClientAccountEntity account) {
        return orderManager.getOrdersOfAccount(account);
    }

    @Override
    public void modifyAddress(AddressEntity address) {
        addressManager.edit(address);
    }
}

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
import javax.ejb.Stateless;
import pojo.Product;
import session.interfaces.AdminFacadeRemoteItf;
import session.manager.AddressManagerBean;
import session.manager.BeverageManagerBean;
import session.manager.ClientAccountManagerBean;
import session.manager.CocktailManagerBean;
import session.manager.DecorationManagerBean;
import session.manager.OrderManagerBean;

@Stateless
public class AdminFacadeBean implements AdminFacadeRemoteItf {

    @EJB
    private BeverageManagerBean beverageManager;
    @EJB
    private DecorationManagerBean decorationManager;
    @EJB
    private CocktailManagerBean cocktailManager;
    @EJB
    private OrderManagerBean orderManager;
    @EJB
    private AddressManagerBean addressManager;
    @EJB
    private ClientAccountManagerBean clientAccountManager;

    /*
     * Add, Update and Remove entities.
     */
    /* Beverage */
    @Override
    public BeverageEntity addBeverage(BeverageEntity beverage) {
        return beverageManager.create(beverage);
    }

    @Override
    public BeverageEntity updateBeverage(BeverageEntity beverage) {
        return beverageManager.edit(beverage);
    }

    @Override
    public void removeBeverage(BeverageEntity beverage) {
        beverageManager.remove(beverage);
    }

    /* Decoration */
    @Override
    public DecorationEntity addDecoration(DecorationEntity deco) {
        return decorationManager.create(deco);
    }

    @Override
    public DecorationEntity updateDecoration(DecorationEntity deco) {
        return decorationManager.edit(deco);
    }

    @Override
    public void removeDecoration(DecorationEntity deco) {
        decorationManager.remove(deco);
    }

    /* Cocktail */
    @Override
    public CocktailEntity addCocktail(CocktailEntity cocktail) {
        return cocktailManager.create(cocktail);
    }

    @Override
    public CocktailEntity updateCocktail(CocktailEntity entity) {
        return cocktailManager.edit(entity);
    }

    @Override
    public void removeCocktail(CocktailEntity cocktail) {
        cocktailManager.remove(cocktail);
    }

    /* Address */
    @Override
    public AddressEntity addAddress(AddressEntity address) {
        return addressManager.create(address);
    }

    @Override
    public void removeAddress(AddressEntity address) {
        addressManager.remove(address);
    }

    /* Order */
    @Override
    public OrderEntity addOrder(OrderEntity order) {
        return orderManager.create(order);
    }

    @Override
    public void removeOrder(OrderEntity order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /* Client Account */
    @Override
    public ClientAccountEntity addClient(ClientAccountEntity client) {
        return clientAccountManager.create(client);
    }

    @Override
    public void removeClient(ClientAccountEntity client) {
        clientAccountManager.remove(client);
    }

    @Override
    public void terminateTransactions() {
        beverageManager.flush();
        decorationManager.flush();
        cocktailManager.flush();
        addressManager.flush();
        orderManager.flush();
        clientAccountManager.flush();
    }

    /* *************************************************************************
     * Getter
     */
    /* Beverages */
    @Override
    public BeverageEntity getBeverage(Long ID) {
        return beverageManager.find(ID);
    }

    @Override
    public List<BeverageEntity> getAllBeverages() {
        return beverageManager.findAll();
    }

    @Override
    public List<BeverageEntity> getUnavailableBeverages() {
        return beverageManager.getUnavailableBeverages();
    }

    @Override
    public List<BeverageEntity> getAvailableBeverages() {
        return beverageManager.getAvailableBeverages();
    }

    @Override
    public List<BeverageEntity> getCocktailBeverages(CocktailEntity cocktail) {
        return cocktailManager.getCocktailBeverages(cocktail);
    }

    /* Decorations */
    @Override
    public DecorationEntity getDecoration(Long ID) {
        return decorationManager.find(ID);
    }

    @Override
    public List<DecorationEntity> getAllDecorations() {
        return decorationManager.findAll();
    }

    public List<DecorationEntity> getCocktailDecorations(Long id) {
        return cocktailManager.getCocktailDecorations(id);
    }

    /* Cocktails */
    @Override
    public List<CocktailEntity> getAllCocktails() {
        return cocktailManager.findAll();
    }

    @Override
    public List<CocktailEntity> getAvailableCocktails() {
        return cocktailManager.getAvailableCocktails();
    }

    @Override
    public List<CocktailEntity> getUnavailableCocktails() {
        return cocktailManager.getUnavailableCocktails();
    }

    @Override
    public CocktailEntity getCocktail(Long id) {
        /* The deliverables list isn't instanciated and serialized. */
        return cocktailManager.find(id);
    }

    @Override
    public CocktailEntity getCocktailFull(CocktailEntity cocktail) {
        /* Force deliverables list instanciation and serialization */
        cocktail.getDeliverables().size();
        return cocktail;
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

    /* Addresses */
    @Override
    public List<AddressEntity> getAllAddresses() {
        return addressManager.findAll();
    }

    /* Orders */
    /* Clients */
    @Override
    public List<ClientAccountEntity> getAllClients() {
        return clientAccountManager.findAll();
    }

    /*
     * Cart operations (unavailable for admin)
     */
    @Override
    public void addArticleToCart(Long id, int qty) throws EcomException {
        throw new UnsupportedOperationException("Not supported for the admin.");
    }

    @Override
    public List<CocktailEntity> getCartContent() {
        throw new UnsupportedOperationException("Not supported for the admin.");
    }

    @Override
    public Float getCartPrice() {
        throw new UnsupportedOperationException("Not supported for the admin.");
    }

    @Override
    public Integer getCartSize() {
        throw new UnsupportedOperationException("Not supported for the admin.");
    }
    /*
     * Getters
     ************************************************************************* */

    @Override
    public void removeArticle(CocktailEntity cocktail) throws EcomException {
        throw new UnsupportedOperationException("Not supported for the admin.");
    }

    @Override
    public String getQuantityForCocktail(CocktailEntity cocktail) {
        throw new UnsupportedOperationException("Not supported for the admin.");
    }

    @Override
    public List<BeverageEntity> getCocktailBeverages(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AddressEntity getAddress(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public OrderEntity getOrder(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ClientAccountEntity connect(String login, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearCart() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrderEntity> getOrdersOfAccount(ClientAccountEntity account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifyAddress(AddressEntity address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BeverageEntity> getAllBeveragesWithAlcohol() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<BeverageEntity> getAllBeveragesWithoutAlcohol() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

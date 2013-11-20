/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.facade;

import entity.AddressEntity;
import entity.BeverageEntity;
import entity.CocktailEntity;
import entity.OrderEntity;
import exceptions.EcomException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import session.interfaces.AdminFacadeRemoteItf;
import session.manager.AddressManagerBean;
import session.manager.BeverageManagerBean;
import session.manager.CocktailManagerBean;
import session.manager.OrderManagerBean;

@Stateless
public class AdminFacadeBean implements AdminFacadeRemoteItf{
    @EJB
    private BeverageManagerBean beverageManager;
    @EJB
    private CocktailManagerBean cocktailManager;
    @EJB
    private OrderManagerBean orderManager;
    @EJB
    private AddressManagerBean addressManager;
    @Override
    public void addBeverage(BeverageEntity beverage) {
        beverageManager.create(beverage);
    }

    @Override
    public void removeBeverage(BeverageEntity beverage) {
        beverageManager.remove(beverage);
    }

    @Override
    public void addCocktail(CocktailEntity cocktail) {
        cocktailManager.create(cocktail);
    }

    @Override
    public void removeCocktail(CocktailEntity cocktail) {
        cocktailManager.remove(cocktail);
    }

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
    public CocktailEntity getCocktail(Long id) {
        return cocktailManager.find(id);
    }

    @Override
    public void addArticle(Long id) throws EcomException {
        throw new UnsupportedOperationException("Not supported for the admin."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CocktailEntity> getCart() {
        throw new UnsupportedOperationException("Not supported for the admin."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addOrder(OrderEntity o) {
       orderManager.create(o);
    }

    @Override
    public void addAddress(AddressEntity address) {
        addressManager.create(address);
    }
    @Override
    public List<AddressEntity> getAllAddresses() {
        return addressManager.findAll();
    }

    @Override
    public void removeArticle(Long id) throws EcomException {
        throw new UnsupportedOperationException("Not supported for the admin."); //To change body of generated methods, choose Tools | Templates.
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

}

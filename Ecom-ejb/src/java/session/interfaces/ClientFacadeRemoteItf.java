/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.interfaces;

import entity.AddressEntity;
import entity.BeverageEntity;
import entity.ClientAccountEntity;
import entity.CocktailEntity;
import entity.DecorationEntity;
import entity.OrderEntity;
import exceptions.EcomException;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface ClientFacadeRemoteItf {

    /*Transactions*/
    public void decreaseQuantityOfCocktail(CocktailEntity cocktail, int number) throws EcomException;

    
    /*Search cocktail*/
    public List<CocktailEntity> getCocktailsForBeverage(BeverageEntity beverage);
    
    public List<CocktailEntity> getCocktailsByPower(pojo.CocktailPowerEnum power);
    
    public List<CocktailEntity> getCocktailsByFlavor(pojo.CocktailFlavorEnum flavor);
    
    /* Fetch deliverables */
    public List<BeverageEntity> getAvailableBeverages();

    public List<BeverageEntity> getUnavailableBeverages();

    public List<BeverageEntity> getCocktailBeverages(CocktailEntity cocktail);

    public List<DecorationEntity> getCocktailDecorations(Long id);
    
    public List<BeverageEntity> getAllBeveragesWithAlcohol();
    
    public List<BeverageEntity> getAllBeveragesWithoutAlcohol();

    /* Fetch cocktails */
    public List<CocktailEntity> getAllCocktails();

    public List<CocktailEntity> getAvailableCocktails();

    public List<CocktailEntity> getUnavailableCocktails();

    public CocktailEntity getCocktail(Long id);

    public CocktailEntity getCocktailFull(CocktailEntity cocktail);

    public List<CocktailEntity> getCartContent();

    public List<CocktailEntity> getMostPopularCocktails();

    public List<CocktailEntity> getNewestCocktails();

    public List<CocktailEntity> getCocktailsWithAlcohol();

    public List<CocktailEntity> getCocktailsWithoutAlcohol();

    public List<CocktailEntity> getCocktailsByFirstLetter(char letter);

    public List<CocktailEntity> getCocktailsWithAlcoholByFirstLetter(char letter);

    public List<CocktailEntity> getCocktailsWithoutAlcoholByFirstLetter(char letter);

    public List<CocktailEntity> getCocktailsByExp(String name);
    
    public List<CocktailEntity> getCocktailsByName(String name);

    /* Cart operations */
    public void removeArticle(CocktailEntity cocktail, int quantity) throws EcomException;

    public void addArticleToCart(Long id, int qty) throws EcomException;

    public Float getCartPrice();

    public Integer getCartSize();
    
    public List<BeverageEntity> getCocktailBeverages(Long id);
    
    public AddressEntity addAddress(AddressEntity entireAddress);

    public OrderEntity addOrder(OrderEntity order);
    
    public AddressEntity getAddress(Long id);
    
    public OrderEntity getOrder(Long id);
    
    public void clearCart();
    /*
     * Account creation functions
     */
    public ClientAccountEntity addClient(ClientAccountEntity client);

    public ClientAccountEntity connect(String login, String password);
    
    public List<OrderEntity> getOrdersOfAccount(ClientAccountEntity account);
    
    public void terminateTransactions();

    public String getQuantityForCocktail(CocktailEntity cocktail);    
    
    public void modifyAddress(AddressEntity address);
    
    public Long checkAddress(AddressEntity address);
}

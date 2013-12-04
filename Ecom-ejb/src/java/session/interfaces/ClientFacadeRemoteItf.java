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

    /* Fetch deliverables */
    public List<BeverageEntity> getAvailableBeverages();

    public List<BeverageEntity> getUnavailableBeverages();

    public List<BeverageEntity> getCocktailBeverages(Long id);

    public List<DecorationEntity> getCocktailDecorations(Long id);

    /* Fetch cocktails */
    public List<CocktailEntity> getAllCocktails();

    public List<CocktailEntity> getAvailableCocktails();

    public List<CocktailEntity> getUnavailableCocktails();

    public CocktailEntity getCocktail(Long id);

    public CocktailEntity getCocktailFull(Long id);

    public List<CocktailEntity> getMostPopularCocktails();

    public List<CocktailEntity> getNewestCocktails();

    public List<CocktailEntity> getCocktailsWithAlcohol();

    public List<CocktailEntity> getCocktailsWithoutAlcohol();

    public List<CocktailEntity> getCocktailsByFirstLetter(char letter);

    public List<CocktailEntity> getCocktailsWithAlcoholByFirstLetter(char letter);

    public List<CocktailEntity> getCocktailsWithoutAlcoholByFirstLetter(char letter);

    public List<CocktailEntity> getCocktailsByName(String name);

    /* Cart operations */
    public void addArticleToCart(Long id) throws EcomException;

    public void removeArticleFromCart(Long id) throws EcomException;

    public List<CocktailEntity> getCartContent();

    public Float getCartPrice();

    public Integer getCartSize();

    /*
     * Account creation functions
     */
    public AddressEntity updateAddress(AddressEntity address);

    public OrderEntity updateOrder(OrderEntity order);

    public ClientAccountEntity updateClient(ClientAccountEntity client);
}

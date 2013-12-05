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
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface AdminFacadeRemoteItf extends ClientFacadeRemoteItf {

    /*
     * Add Update and Remove
     */
    public BeverageEntity addBeverage(BeverageEntity beverage);

    public BeverageEntity updateBeverage(BeverageEntity beverage);

    public void removeBeverage(BeverageEntity beverage);

    public DecorationEntity addDecoration(DecorationEntity deco);

    public DecorationEntity updateDecoration(DecorationEntity deco);

    public void removeDecoration(DecorationEntity deco);

    public CocktailEntity addCocktail(CocktailEntity cocktail);

    public CocktailEntity updateCocktail(CocktailEntity cocktail);

    public void removeCocktail(CocktailEntity cocktail);

    /* addXXXX() avalaible for clients */
    public void removeAddress(AddressEntity address);

    public void removeClient(ClientAccountEntity client);

    public void removeOrder(OrderEntity order);
    /*
     * Get
     */

    public List<BeverageEntity> getAllBeverages();

    public List<DecorationEntity> getAllDecorations();

    /*
     * getAllCocktails(); declared for clients
     */
    public List<AddressEntity> getAllAddresses();

    public List<ClientAccountEntity> getAllClients();
}

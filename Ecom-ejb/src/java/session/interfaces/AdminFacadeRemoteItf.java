/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.interfaces;

import entity.AddressEntity;
import entity.BeverageEntity;
import entity.CocktailEntity;
import entity.OrderEntity;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface AdminFacadeRemoteItf extends ClientFacadeRemoteItf {
    public BeverageEntity addBeverage(BeverageEntity beverage);
    public List<BeverageEntity> getAllBeverages();
    public void removeBeverage(BeverageEntity beverage);
    public void addCocktail(CocktailEntity cocktail);
    public void removeCocktail(CocktailEntity cocktail);
    public void addOrder(OrderEntity order);
    public void addAddress(AddressEntity address);
    public List<AddressEntity> getAllAddresses();
}

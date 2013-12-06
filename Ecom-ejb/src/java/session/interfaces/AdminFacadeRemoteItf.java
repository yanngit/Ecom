/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.interfaces;

import entity.AddressEntity;
import entity.BeverageEntity;
import entity.CocktailEntity;
import entity.DecorationEntity;
import entity.OrderEntity;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface AdminFacadeRemoteItf extends ClientFacadeRemoteItf {

    public BeverageEntity addBeverage(BeverageEntity beverage);

    public void updateBeverage(BeverageEntity beverage);

    @Override
    public List<BeverageEntity> getAllBeverages();

    public void removeBeverage(BeverageEntity beverage);

    public CocktailEntity addCocktail(CocktailEntity cocktail);

    public void updateCocktail(CocktailEntity cocktail);

    public void removeCocktail(CocktailEntity cocktail);
    
    @Override
    public OrderEntity addOrder(OrderEntity order);

    @Override
    public AddressEntity addAddress(AddressEntity address);

    public List<AddressEntity> getAllAddresses();

    public List<DecorationEntity> getAllDecorations();

    public DecorationEntity addDecoration(DecorationEntity deco);

    public void updateDecoration(DecorationEntity deco);

    public void removeDecoration(DecorationEntity deco);
}

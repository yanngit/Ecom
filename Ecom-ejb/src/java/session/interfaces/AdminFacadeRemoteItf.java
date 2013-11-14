/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.interfaces;

import entity.BeverageEntity;
import entity.CocktailEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author yann
 */
@Remote
public interface AdminFacadeRemoteItf extends ClientFacadeRemoteItf {
    public void addBeverage(BeverageEntity beverage);
    public List<BeverageEntity> getAllBeverages();
    public void removeBeverage(BeverageEntity beverage);
    public void addCocktail(CocktailEntity cocktail);
    public void removeCocktail(CocktailEntity cocktail);
}

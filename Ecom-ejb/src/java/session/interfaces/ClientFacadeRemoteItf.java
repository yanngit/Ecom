/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.interfaces;

import entity.BeverageEntity;
import entity.CocktailEntity;
import exceptions.EcomException;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface ClientFacadeRemoteItf {
    public List<CocktailEntity> getAllCocktails();
    public List<CocktailEntity> getAvailableCocktails();
    public List<CocktailEntity> getUnavailableCocktails();
    public CocktailEntity getCocktail(Long id);
    public List<BeverageEntity> getAllBeverages();
    public List<BeverageEntity> getAvailableBeverages();
    public List<BeverageEntity> getUnavailableBeverages();
    public void addArticle(Long id) throws EcomException;
    public void removeArticle(Long id) throws EcomException;
    public List<CocktailEntity> getCart();
    public List<CocktailEntity> getMostPopularCocktails();
    public List<CocktailEntity> getNewestCocktails();

}

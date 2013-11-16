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
public interface ClientFacadeRemoteItf {
    public List<CocktailEntity> getAllCocktails();
    public List<CocktailEntity> getAvailableCocktails();
    public List<CocktailEntity> getUnavailableCocktails();
    public CocktailEntity getCocktail(Long id);
    public List<BeverageEntity> getAllBeverages();
    public List<BeverageEntity> getAvailableBeverages();
    public List<BeverageEntity> getUnavailableBeverages();
}

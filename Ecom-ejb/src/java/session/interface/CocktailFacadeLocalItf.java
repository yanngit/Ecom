/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.facade;

import entity.CocktailEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sohnoun
 */
@Local
public interface CocktailFacadeLocalItf {
    CocktailEntity getCocktail(long id);
    List<CocktailEntity> getAvailableCocktails();
    List<CocktailEntity> getAnavailableCocktails();
}

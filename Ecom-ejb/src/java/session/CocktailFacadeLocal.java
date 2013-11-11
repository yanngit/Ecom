/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Cocktail;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sohnoun
 */
@Local
public interface CocktailFacadeLocal {
    Cocktail getCocktail(long id);
    List<Cocktail> getAvailableCocktails();
    List<Cocktail> getAnavailableCocktails();
}

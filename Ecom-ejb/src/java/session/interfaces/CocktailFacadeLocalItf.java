/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.interfaces;

import entity.CocktailEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sohnoun
 */
@Local
public interface CocktailFacadeLocalItf {
    public CocktailEntity getCocktail(long id);
    public List<CocktailEntity> getAvailableCocktails();
    public List<CocktailEntity> getUnavailableCocktails();
}

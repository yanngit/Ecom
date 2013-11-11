/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CocktailEntity;
import java.util.List;

/**
 *
 * @author yann
 */
public interface CocktailManagerItf {
    public void create(CocktailEntity cocktail);
    public void edit(CocktailEntity cocktail);
    public void remove(CocktailEntity cocktail);
    public CocktailEntity find(Object id);
    public List<CocktailEntity> findAll();
}

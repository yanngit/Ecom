/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.interfaces;

import entity.CocktailEntity;
import exceptions.EcomException;
import java.util.List;
import java.util.Map;
import javax.ejb.LocalBean;

@LocalBean
public interface CartFacadeLocalItf {
    public void setName(String name);
    public String getName();
    public void addArticle(long ID, int qty) throws EcomException;
    public void removeArticle(CocktailEntity cocktail, int qty) throws EcomException;
    public List<CocktailEntity> getCocktails();
    public void emptyCart();
    public float getReduction();
    public float getPrice();
    public int getSize();
    public String getQuantityForCocktail(CocktailEntity cocktail);
    public Map<CocktailEntity,Integer> getMap();
}

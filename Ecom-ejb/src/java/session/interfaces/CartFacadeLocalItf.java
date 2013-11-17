/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.interfaces;

import entity.CocktailEntity;
import exceptions.EcomException;
import java.util.List;
import javax.ejb.LocalBean;

@LocalBean
public interface CartFacadeLocalItf {
    public void setName(String name);
    public String getName();
    public void addArticle(long ID) throws EcomException;
    public void removeArticle(long ID) throws EcomException;
    public List<CocktailEntity> getCocktails();
    public void emptyCart();
    public float getReduction();
    public float getPrice();
}

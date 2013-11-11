/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Cocktail;
import exceptions.EcomException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author yann
 */
@Local
public interface CartFacadeLocal {
    public void setName(String name);
    public String getName();
    public void addArticle(long ID) throws EcomException;
    public void removeArticle(long ID) throws EcomException;
    public List<Cocktail> getCocktails();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.facade;

import session.interfaces.CartFacadeLocalItf;
import entity.CocktailEntity;
import exceptions.EcomException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import session.interfaces.CocktailFacadeLocalItf;

/**
 *
 * @author yann
 */
@Stateful
public class CartFacadeBean implements CartFacadeLocalItf {
    @EJB (name="CocktailFacade")
    private CocktailFacadeLocalItf cocktailFacade;
    private List<CocktailEntity> cart = new ArrayList<>();
    private String name = null;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addArticle(long ID) throws EcomException {
        CocktailEntity c = cocktailFacade.getCocktail(ID);
        if(c == null){
             throw new EcomException("Can't add the cocktail ["+ID+"] in the cart "+name);
        }
        else {
             cart.add(c);
        }
       
    }
    
    public int getIndexInCartOf(long ID){
        int i = 0;
        for(CocktailEntity c : cart){
            if(c.getID()== ID){
                return i;
            }
            i ++;
        }
        return -1;
    }

    @Override
    public void removeArticle(long ID) throws EcomException{
        int index = getIndexInCartOf(ID);
        if(index == -1){
            throw new EcomException("Can't remove the cocktail ["+ID+"] in the cart"+name);
        }
        else{
            cart.remove(index);  
        }
    }

    @Override
    public List<CocktailEntity> getCocktails() {
        return cart;
    }
    
    

}

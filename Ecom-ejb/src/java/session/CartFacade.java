/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Cocktail;
import exceptions.EcomException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author yann
 */
@Stateful
public class CartFacade implements CartFacadeLocal {
    @EJB (name="Cocktail")
    private CocktailFacadeLocal cocktailFacade;
    private List<Cocktail> cart = new ArrayList<>();
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
        Cocktail c = cocktailFacade.getCocktail(ID);
        if(c == null){
             throw new EcomException("Can't add the cocktail ["+ID+"] in the cart "+name);
        }
        else {
             cart.add(c);
        }
       
    }
    
    public int getIndexInCartOf(long ID){
        int i = 0;
        for(Cocktail c : cart){
            if(c.getId() == ID){
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
    public List<Cocktail> getCocktails() {
        return cart;
    }
    
    

}

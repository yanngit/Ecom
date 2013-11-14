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
    private float price = 0;

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
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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
        updatePrice();
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
        updatePrice();
    }

    @Override
    public List<CocktailEntity> getCocktails() {
        return cart;
    }
    
    public void updatePrice(){
        this.price = 0;
        for(CocktailEntity c : getCocktails()){
            this.price = this.price + c.getPrice();
        }
        if(getCocktails().size() > 5){
            this.price = price - 10*price/100;
        }
    }
    
    

}

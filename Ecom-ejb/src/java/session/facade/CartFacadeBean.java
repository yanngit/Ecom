/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.facade;

import session.interfaces.CartFacadeLocalItf;
import entity.CocktailEntity;
import exceptions.EcomException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import session.manager.CocktailManagerBean;

@Stateful
public class CartFacadeBean implements CartFacadeLocalItf {
    @EJB
    private CocktailManagerBean cocktailManager;
    private Map<CocktailEntity,Integer> cart = new HashMap<>();
    private String name = null;
    private float price = 0;

    private float reduction = 0;

    @Override
    public int getSize() {
        int size = 0;
        for(CocktailEntity c : cart.keySet()){
            size += cart.get(c);
        }
        return size;
    }

    @Override
    public float getReduction() {
        return reduction;
    }

    private void setReduction(float reduction) {
        this.reduction = reduction;
    }

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
    public float getPrice() {
        return price;
    }

    private void setPrice(float price) {
        this.price = price;
    }
    
    @Override
    public void addArticle(long ID, int qty) throws EcomException {
        CocktailEntity c = cocktailManager.find(ID);
        if(c == null){
             throw new EcomException("Can't add the cocktail ["+ID+"] in the cart "+name);
        }
        else {
            /*Si le cocktail est déjà présent, on met à jour la quantité, sinon on l'ajoute*/
            if(cart.containsKey(c)){
                cart.put(c, cart.get(c) + qty );
            } else {
                cart.put(c,new Integer(qty));
            }
             updatePrice(c.getPrice()*qty);
        }
    }
    
    
    @Override
    public void removeArticle(long ID) throws EcomException{
        CocktailEntity c = cocktailManager.find(ID);
        cart.remove(c);
        float prix = c.getPrice();
        updatePrice(-prix);
    }

    @Override
    public List<CocktailEntity> getCocktails() {
        List<CocktailEntity> res = new ArrayList<>();
        for(CocktailEntity c : cart.keySet()){
            res.add(c);
        }
        Collections.sort(res);
        return res;
    }
    
    private void updatePrice(float price){
        this.price += price;
        if(getSize() > 5){
            this.reduction = 10*price/100;
        }
        else{
            this.reduction = 0;
        }
    }

    @Override
    public void emptyCart() {
        this.cart.clear();
        this.setPrice(0);
        this.setReduction(0);
    }

    @Override
    public String getQuantityForCocktail(CocktailEntity cocktail) {
       if(cart.containsKey(cocktail)){
           return String.valueOf(cart.get(cocktail));
       }
       /*THROW erreur*/
       return "0";
    }
}

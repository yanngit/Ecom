/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managed;

import entity.Drink;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import session.DrinkFacadeLocal;

/**
 *
 * @author yann
 */
@ManagedBean(name="drinkBean")
@RequestScoped
public class DrinkManagedBean implements Serializable {
    private @EJB DrinkFacadeLocal drink;
    
    public DrinkManagedBean () {
        super();
    }
    
    public Drink[] getListDrinks(){
        return drink.findAllLocal();
    }
}

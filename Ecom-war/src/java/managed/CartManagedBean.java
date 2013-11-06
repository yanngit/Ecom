/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import session.CartFacadeRemote;

/**
 *
 * @author yann
 */
@ManagedBean(name="cartBean")
@SessionScoped
public class CartManagedBean implements Serializable {

     private @EJB CartFacadeRemote cart;
    
    public CartManagedBean () {
        super();
    }
    
    public String getName(){
        return cart.getName();
    }
    
    public void setName(String name){
        cart.setName(name);
    }
}

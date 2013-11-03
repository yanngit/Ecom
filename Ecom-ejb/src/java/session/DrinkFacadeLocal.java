/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Drink;
import javax.ejb.Local;

/**
 *
 * @author yann
 */
@Local
public interface DrinkFacadeLocal {
    public void create(Drink drink);
    public void edit(Drink drink);
    public void remove(Drink drink);
    public Drink find(Object id);
    public Drink[] findAllLocal();
}

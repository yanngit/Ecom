/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.BeverageEntity;
import java.util.List;

/**
 *
 * @author yann
 */
public interface BeverageManagerItf {
    public void create(BeverageEntity beverage);
    public void edit(BeverageEntity beverage);
    public void remove(BeverageEntity beverage);
    public BeverageEntity find(Object id);
    public List<BeverageEntity> findAll();
}

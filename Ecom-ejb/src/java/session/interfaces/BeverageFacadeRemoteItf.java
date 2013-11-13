/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.interfaces;

import entity.BeverageEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author yann
 */
@Remote
public interface BeverageFacadeRemoteItf {
    public List<BeverageEntity> getAllDrinks();
    public void create(BeverageEntity entity);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.interfaces;

import entity.BeverageEntity;
import java.util.List;
import javax.ejb.LocalBean;

/**
 *
 * @author yann
 */
@LocalBean
public interface BeverageFacadeLocalItf {
    public List<BeverageEntity> getAllBeverages();
    public void create(BeverageEntity entity);
    
    public List<BeverageEntity> getUnavailableBeverages();
    public List<BeverageEntity> getAvailableBeverages();
}

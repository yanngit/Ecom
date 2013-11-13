/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.interfaces;

import entity.BeverageEntity;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author yann
 */
@Remote
public interface BeverageManagerRemoteItf extends Serializable {
    public BeverageEntity find(Object id);
    public List<BeverageEntity> findAll() ;
    public void create(BeverageEntity entity);   
    public void edit(BeverageEntity entity);
    public void remove(BeverageEntity entity);
}

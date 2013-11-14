/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.interfaces;

import entity.OrderEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author bach
 */
@Local
public interface OrderFacadeLocalItf {
    public List<OrderEntity> getAllOrders();
    public void creat(OrderEntity entity);
}

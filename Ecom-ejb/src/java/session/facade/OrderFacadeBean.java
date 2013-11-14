/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session.facade;

import entity.OrderEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import session.interfaces.OrderFacadeLocalItf;
import session.manager.OrderManagerBean;

/**
 *
 * @author bach
 */

@Local
public class OrderFacadeBean implements OrderFacadeLocalItf {

    @EJB
    private OrderManagerBean manager;
    
    @Override
    public List<OrderEntity> getAllOrders() {
        List<OrderEntity> res = manager.findAll();
        return res;
    }

    @Override
    public void creat(OrderEntity entity) {
        manager.create(entity);
    }

}

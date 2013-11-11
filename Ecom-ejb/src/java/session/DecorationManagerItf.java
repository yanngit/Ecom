/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.DecorationEntity;
import java.util.List;

/**
 *
 * @author yann
 */
public interface DecorationManagerItf {
    public void create(DecorationEntity decoration);
    public void edit(DecorationEntity decoration);
    public void remove(DecorationEntity decoration);
    public DecorationEntity find(Object id);
    public List<DecorationEntity> findAll();
}

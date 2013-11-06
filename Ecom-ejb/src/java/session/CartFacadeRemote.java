/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.ejb.Remote;

/**
 *
 * @author yann
 */
@Remote
public interface CartFacadeRemote {
    public void setName(String name);
    public String getName();
}

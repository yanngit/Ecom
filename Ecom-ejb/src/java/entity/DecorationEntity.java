/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import pojo.Deliverable;

/**
 *
 * @author alexis
 */
@Entity
@Table(name="DECORATION")
public class DecorationEntity extends Deliverable {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "entity.DecorationEntity[" + ID + " : " + name + " ]";
    }
    
}

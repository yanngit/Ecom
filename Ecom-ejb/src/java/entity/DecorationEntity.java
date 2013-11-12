/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import pojo.Deliverable;

/**
 *
 * @author alexis
 */
@Entity
@Table(name="DECORATION")
public class DecorationEntity extends Deliverable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    protected Long ID;
    private static final long serialVersionUID = 1L;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
    
    @Override
    public String toString() {
        return "entity.DecorationEntity[" + ID + " : " + name + " ]";
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import pojo.Deliverable;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author alexis
 */
@Entity
@Table(name="BEVERAGE")
public class BeverageEntity extends Deliverable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    protected Long ID;
    private static final long serialVersionUID = 1L;
    @Column(name="CAPACITY")
    protected Integer capacity; /* in cL */
    @Column(name="DEGREE")
    protected Integer alcoholicDegree;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
    
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getAlcoholicDegree() {
        return alcoholicDegree;
    }

    public void setAlcoholicDegree(Integer alcoholicDegree) {
        this.alcoholicDegree = alcoholicDegree;
    }
    
    @Override
    public String toString() {
        return "entity.BeverageEntity[" + ID + " : " + name + "]";
    }
}

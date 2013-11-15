/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import pojo.Deliverable;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author alexis
 */
@NamedQueries({
@NamedQuery(name="findUnavailableBeverages",query="SELECT b FROM BeverageEntity b WHERE b.quantity <= 0"),
@NamedQuery(name="findAvailableBeverages", query="SELECT b FROM BeverageEntity b WHERE b.quantity >= 1")
})

@Entity
@DiscriminatorValue("Beverage")
@Table(name="BEVERAGE")
public class BeverageEntity extends Deliverable  {
    private static final long serialVersionUID = 1L;
    @Column(name="CAPACITY")
    protected Integer capacity; /* in cL */
    @Column(name="DEGREE")
    protected Integer alcoholicDegree;

    public BeverageEntity(){
        super();
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

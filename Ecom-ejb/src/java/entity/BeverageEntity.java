/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import pojo.Deliverable;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author alexis
 */
@NamedQuery(
    name="findUnavailable",
    query="SELECT b FROM BeverageEntity b WHERE b.quantity < :val"
)

@Entity
@DiscriminatorValue("Beverage")
@Table(name="BEVERAGE")
public class BeverageEntity extends Deliverable  {
    private static final long serialVersionUID = 1L;
    @Column(name="CAPACITY")
    @NotNull
    protected Integer capacity; /* in cL */
    @Column(name="DEGREE")
    @NotNull
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

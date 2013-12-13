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
import javax.validation.constraints.NotNull;

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
    @NotNull
    /**
     * The capacity of the beverage container, in cl
     */
    protected Integer capacity;
    @Column(name="DEGREE")
    @NotNull
    /**
     * The alcoholic degree of the beverage : if the beverage is not alcohol, the degree is equal to 0
     */
    protected Integer alcoholicDegree;

    public BeverageEntity(){
        super();
    }
    
    /**
     * Get the capacity of the beverage container
     * @return An Integer representing the capacity of the beverage container
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Set the capacity of the beverage container
     * @param capacity An Integer representing the capacity 
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Set the alcoholic degree of the beverage
     * @return An Integer representing the alcoholic degree of the beverage
     */
    public Integer getAlcoholicDegree() {
        return alcoholicDegree;
    }

    /**
     * Get the alcoholic degree of the beverage
     * @param alcoholicDegree An Integer representing the alcoholic degree
     */
    public void setAlcoholicDegree(Integer alcoholicDegree) {
        this.alcoholicDegree = alcoholicDegree;
    }
    
   /**
     * Get a string representation of the beverage
     * @return A string representing the beverage
     */
    @Override
    public String toString() {
        return "entity.BeverageEntity[" + ID + " : " + name + "]";
    }
}

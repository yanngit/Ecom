/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import pojo.Deliverable;

@Entity
@DiscriminatorValue("Decoration")
@Table(name="DECORATION")
public class DecorationEntity extends Deliverable {
    private static final long serialVersionUID = 1L;

    public DecorationEntity(){
        super();
    }
    
    /**
     * Get a string representation of the decoration
     * @return A string representing the decoration
     */
    @Override
    public String toString() {
        return "entity.DecorationEntity[" + ID + " : " + name + " ]";
    }
    
}

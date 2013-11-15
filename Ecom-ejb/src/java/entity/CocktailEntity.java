/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import pojo.CocktailFlavorEnum;
import pojo.CocktailPowerEnum;
import pojo.Deliverable;
import pojo.Product;

/**
 *
 * @author alexis
 */
/*@NamedQueries({
@NamedQuery(name="findUnavailableCocktails",query="SELECT c FROM CocktailEntity c WHERE c.quantity <= 0"),
@NamedQuery(name="findAvailableCocktails", query="SELECT c FROM CocktailEntity c WHERE c.quantity >= 1")
})*/
@Entity
@Table(name="COCKTAIL")
public class CocktailEntity extends Product  {
    private static final long serialVersionUID = 1L;
    @Column(name="PHOTO")
    protected String photoURI;
    @Column(name="RECIPE")
    @NotNull
    protected String recipe;
    @Column(name="FLAVOR")
    @Enumerated(value=EnumType.ORDINAL)
    @NotNull
    protected CocktailFlavorEnum flavor;
    @Column(name="POWER")
    @Enumerated(value=EnumType.ORDINAL)
    @NotNull
    protected CocktailPowerEnum power;
    @ManyToMany(mappedBy="cocktails")
    @NotNull
    protected List<Deliverable> deliverables;

    public CocktailEntity(){
        super();
    }
    
    public String getPhotoURI() {
        return photoURI;
    }

    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public CocktailFlavorEnum getFlavor() {
        return flavor;
    }

    public void setFlavor(CocktailFlavorEnum flavor) {
        this.flavor = flavor;
    }

    public CocktailPowerEnum getPower() {
        return power;
    }

    public void setPower(CocktailPowerEnum power) {
        this.power = power;
    }

    public List<Deliverable> getDeliverables() {
        return deliverables;
    }

    public void setDeliverables(List<Deliverable> deliverables) {
        this.deliverables = deliverables;
    }
        
    @Override
    public String toString() {
        return "entity.CocktailEntity[" + ID + " : " + name + "]";
    }
    
}

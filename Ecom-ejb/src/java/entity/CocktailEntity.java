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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import pojo.CocktailFlavorEnum;
import pojo.CocktailPowerEnum;
import pojo.Deliverable;
import pojo.Product;

/**
 *
 * @author alexis
 */
@Entity
@Table(name="COCKTAIL")
public class CocktailEntity extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    protected Long ID;
    private static final long serialVersionUID = 1L;
    @Column(name="PHOTO")
    protected String photoURI;
    @Column(name="RECIPE")
    protected String recipe;
    @Column(name="FLAVOR")
    @Enumerated(value=EnumType.ORDINAL)
    protected CocktailFlavorEnum flavor;
    @Column(name="POWER")
    @Enumerated(value=EnumType.ORDINAL)
    protected CocktailPowerEnum power;
    @ManyToMany(mappedBy="cocktails")
    protected List<Deliverable> deliverables;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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

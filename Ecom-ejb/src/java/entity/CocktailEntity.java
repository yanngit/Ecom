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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import pojo.CocktailFlavorEnum;
import pojo.CocktailPowerEnum;
import pojo.Deliverable;
import pojo.Product;

/**
 *
 * @author alexis
 */

@NamedQueries({
@NamedQuery(name="findUnavailableCocktails",query="SELECT c FROM CocktailEntity c"),





//@NamedQuery(name="findAvailableCocktails", query="SELECT c.deliverables FROM CocktailEntity c WHERE c.ID IN :cent"),// WHERE c.ID IN :cent"),//  "

//@NamedQuery(name="findUnavailableCocktails",query="SELECT c FROM CocktailEntity c INNER JOIN c.deliverables d WHERE d.quantity <= 0"),


@NamedQuery(name="findAvailableCocktails", query="SELECT c.ID FROM CocktailEntity c INNER JOIN c.deliverables d "),// WHERE c.ID IN :cent"),//  "

/*
"SELECT p FROM Pan p WHERE p.id IN " +
            "(SELECT p.id FROM p.panRes prs WHERE id IN " +
            "(SELECT r.id FROM PanRes r where r.pant = :pant))"+                       
            " ORDER BY pr.clD"
 */      
        

//la var cent .... les ID de tt les delivrables        
@NamedQuery(name="getIdDelivrables", query="SELECT d.ID FROM Deliverable d")//  ")

//les delivrables disponibles        
        
})

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
    @ManyToMany
    @JoinTable(
        name="COCKTAIL_COMPOSITION",
        joinColumns=@JoinColumn(
            name="COCKTAIL_ID",
            referencedColumnName="ID"),
        inverseJoinColumns=@JoinColumn(
            name="DELIVERABLE_ID",
            referencedColumnName="ID"))
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

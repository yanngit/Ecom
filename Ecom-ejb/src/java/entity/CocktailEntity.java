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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import pojo.CocktailFlavorEnum;
import pojo.CocktailPowerEnum;
import pojo.Deliverable;
import pojo.Product;


@NamedQueries({
@NamedQuery(name="findUnavailableCocktails",query="SELECT c FROM CocktailEntity c INNER JOIN c.deliverables d WHERE d.quantity <= 0"),
@NamedQuery(name="findAvailableCocktails", query="SELECT c FROM CocktailEntity c WHERE c.ID NOT IN (SELECT co.ID FROM CocktailEntity co INNER JOIN co.deliverables de WHERE de.quantity <= 0)"),
@NamedQuery(name="getPopularCocktails", query="SELECT c FROM CocktailEntity c ORDER BY SIZE(c.orders) DESC"),
@NamedQuery(name="getNewestCocktails", query="SELECT c FROM CocktailEntity c ORDER BY(c.ID) DESC"),
@NamedQuery(name="getVirginCocktails", query="SELECT c FROM CocktailEntity c WHERE c.virgin = 1"),
@NamedQuery(name="getCocktailsWithAlcohol", query="SELECT c FROM CocktailEntity c WHERE c.ID NOT IN (SELECT co.ID FROM CocktailEntity co WHERE co.virgin = 1)"),
@NamedQuery(name="getCocktailsByExp", query="SELECT c FROM CocktailEntity c WHERE c.name LIKE :exp"),
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
    @ManyToMany(mappedBy="cocktails")
    protected List<OrderEntity> orders;
    @Column(name="VIRGIN")
    protected Boolean virgin;
    
    public List<OrderEntity> getOrders(){
        return orders;
    }
    
    public void setOrders(List<OrderEntity> list){
        orders = list;
    }

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
        virgin = true;
        this.deliverables = deliverables;
        for(Deliverable d : deliverables){
            if(d instanceof BeverageEntity){
                BeverageEntity b = (BeverageEntity)d;
                if(b.getAlcoholicDegree() > 0){
                    virgin = false;
                }
            }
        }
    }
        
    @Override
    public String toString() {
        return "entity.CocktailEntity[" + ID + " : " + name + "]";
    }
}

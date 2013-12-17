package pojo;

import entity.CocktailEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@NamedQueries({
    @NamedQuery(name = "getCocktailsForBeverage", query = "SELECT d.cocktails FROM Deliverable d WHERE d.name = :name ")
})

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "DELIVERABLE_TYPE",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Deliverable")
public class Deliverable extends Product {

    @Column(name = "QUANTITY")
    @NotNull
    protected Integer quantity;
    /* n-to-n relation with cocktails */
    @ManyToMany
    @JoinTable(
            name = "COCKTAIL_COMPOSITION",
            joinColumns =
            @JoinColumn(
            name = "DELIVERABLE_ID",
            referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(
            name = "COCKTAIL_ID",
            referencedColumnName = "ID"))
    protected List<CocktailEntity> cocktails;

    protected Deliverable() {
        super();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<CocktailEntity> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<CocktailEntity> cocktails) {
        this.cocktails = cocktails;
    }

    @Override
    public String toString() {
        return "pojo.Deliverable[" + ID + " : " + name + "]";
    }
}

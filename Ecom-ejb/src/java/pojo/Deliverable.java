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
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

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
    @ManyToMany(mappedBy = "deliverables")
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

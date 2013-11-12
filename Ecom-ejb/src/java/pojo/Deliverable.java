package pojo;

import entity.CocktailEntity;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author alexis
 */
public class Deliverable extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    protected Long ID;
    
    @Column(name="QUANTITY")
    protected Integer quantity;
    
    /* n-to-n relation with cocktails */
    @ManyToMany
    @JoinTable(
            name="COCKTAIL_COMPOSITION",
            joinColumns=@JoinColumn(
                name="DELIVERABLE_ID",
                referencedColumnName="DELIVERABLE_ID_REF"),
            inverseJoinColumns=@JoinColumn(
                name="COCKTAIL_ID",
                referencedColumnName="COCKTAIL_ID_REF"))
    protected List<CocktailEntity> cocktails;

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

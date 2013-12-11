/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import pojo.CocktailFlavorEnum;
import pojo.CocktailPowerEnum;
import pojo.Deliverable;
import pojo.Product;

@NamedQueries({
    @NamedQuery(name = "findProposal", query = "SELECT c FROM CocktailEntity c WHERE c.statePublication = 0"),
    @NamedQuery(name = "findUnavailableCocktails", query = "SELECT c FROM CocktailEntity c WHERE c.available = 0"),
    @NamedQuery(name = "findAvailableCocktails", query = "SELECT c FROM CocktailEntity c WHERE c.available = 1"),
    @NamedQuery(name = "getPopularCocktails", query = "SELECT c FROM CocktailEntity c ORDER BY SIZE(c.orders) DESC"),
    @NamedQuery(name = "getNewestCocktails", query = "SELECT c FROM CocktailEntity c ORDER BY(c.ID) DESC"),
    @NamedQuery(name = "getCocktailsByVirginDetail", query = "SELECT c FROM CocktailEntity c WHERE c.virgin = :num"),
    @NamedQuery(name = "getCocktailsByExp", query = "SELECT c FROM CocktailEntity c WHERE c.name LIKE :exp"),
    @NamedQuery(name = "getCocktailsByExpAndVirginDetail", query = "SELECT c FROM CocktailEntity c WHERE c.virgin = :num and c.name LIKE :exp"),})
@Entity
@Table(name = "COCKTAIL")
public class CocktailEntity extends Product {

    private static final long serialVersionUID = 1L;
    @Column(name = "PHOTO")
    protected String photoURI;
    @Column(name = "RECIPE")
    @Lob
    @NotNull
    protected String recipe;
    @Column(name = "FLAVOR")
    @Enumerated(value = EnumType.ORDINAL)
    @NotNull
    protected CocktailFlavorEnum flavor;
    @Column(name = "POWER")
    @Enumerated(value = EnumType.ORDINAL)
    @NotNull
    protected CocktailPowerEnum power;
    /* Cascade : when you persist a cocktail, persist all its Deliverables
     *           You can update deliverables through cocktail (merge)
     */
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "cocktails")
    @NotNull
    protected List<Deliverable> deliverables;
    @ManyToMany(mappedBy = "cocktails")
    protected List<OrderEntity> orders;
    @ManyToOne(
            fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ACCOUNT_FK", referencedColumnName = "ID")
    protected ClientAccountEntity client;
    @Column(name = "VIRGIN")
    protected Boolean virgin;
    @Column(name = "AVAILABLE")
    protected Boolean available;
    @Column(name = "STATE_PUBLICATION")
    protected Boolean statePublication;

    public Boolean getStatePublication() {
        return statePublication;
    }

    public void setStatePublication(Boolean statePublication) {
        this.statePublication = statePublication;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean getAvailable() {
        return available;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> list) {
        orders = list;
    }

    public CocktailEntity() {
        super();
    }

    public String getPhotoURIName() {
        return photoURI.split("\\.")[0];
    }

    public String getPhotoURIExt() {
        return photoURI.split("\\.")[1];
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
        for (Deliverable d : deliverables) {
            if (d instanceof BeverageEntity) {
                BeverageEntity b = (BeverageEntity) d;
                if (b.getAlcoholicDegree() > 0) {
                    virgin = false;
                }
            }
        }
    }

    public ClientAccountEntity getClient() {
        return client;
    }

    public void setClient(ClientAccountEntity client) {
        this.client = client;
    }

    public Boolean getVirgin() {
        return virgin;
    }

    public void setVirgin(Boolean virgin) {
        this.virgin = virgin;
    }

    @Override
    public String toString() {
        return "entity.CocktailEntity[" + ID + " : " + name + "]";
    }

    public void setPower(CocktailFlavorEnum cocktailFlavorEnum) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

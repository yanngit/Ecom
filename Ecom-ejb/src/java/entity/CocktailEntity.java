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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
public class CocktailEntity extends Product implements Comparable<CocktailEntity>{

    private static final long serialVersionUID = 1L;
    @Column(name = "PHOTO")
    /**
     * URI of the photo representing the cocktail
     */
    protected String photoURI;
    @Column(name = "RECIPE")
    @Lob
    @NotNull
    /**
     * Recipe of the cocktail
     */
    protected String recipe;
    @Column(name = "FLAVOR")
    @Enumerated(value = EnumType.ORDINAL)
    @NotNull
    /**
     * Flavor of the cocktail
     */
    protected CocktailFlavorEnum flavor;
    @Column(name = "POWER")
    @Enumerated(value = EnumType.ORDINAL)
    @NotNull
    /**
     * Power of the cocktail
     */
    protected CocktailPowerEnum power;
    /* Cascade : when you persist a cocktail, persist all its Deliverables
     *           You can update deliverables through cocktail (merge)
     */
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "cocktails")
    @NotNull
    /**
     * List of deliverables composing the cocktail
     */
    protected List<Deliverable> deliverables;
    @ManyToMany(mappedBy = "cocktails")
    /**
     * List of orders containing the cocktail
     */
    protected List<OrderEntity> orders;
    @ManyToOne(
            fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ACCOUNT_FK", referencedColumnName = "ID")
    /**
     * If not null, representing the account client responsible for the proposal
     */
    protected ClientAccountEntity client;
    @Column(name = "VIRGIN")
    /**
     * A boolean that indicates if the cocktail is virgin
     */
    protected Boolean virgin;
    @Column(name = "AVAILABLE")
    /**
     * A boolean that indicates if the cocktail is available
     */
    protected Boolean available;
    @Column(name = "STATE_PUBLICATION")
    /**
     * A boolean that indicates if the cocktail is published
     */
    protected Boolean statePublication;
    
    /**
     * Get the publication state
     * @return True if the cocktail is published, false otherwise
     */
    public Boolean getStatePublication() {
        return statePublication;
    }

    /**
     * Set the publication state
     * @param statePublication A boolean representing the publication state
     */
    public void setStatePublication(Boolean statePublication) {
        this.statePublication = statePublication;
    }
 
    /**
     * Set the availability of the cocktail
     * @param available A boolean representing the availability
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }
 
    /**
     * Get the availability of the cocktail
     * @return True if the cocktail is available, false otherwise
     */
    public boolean getAvailable() {
        return available;
    }
 
    /**
     * Get the list of orders containing the cocktail
     * @return A list of OrderEntity containing the cocktail
     */
    public List<OrderEntity> getOrders() {
        return orders;
    }
 
    /**
     * Set the list of orders containing the cocktail
     * @param list A list of orders containing the cocktail
     */
    public void setOrders(List<OrderEntity> list) {
        orders = list;
    }

    /**
     * Get the name of the photo URI associated to the cocktail
     * @return A string representing the photo URI name
     */
    public String getPhotoURIName() {
        return photoURI.split("\\.")[0];
    }

    /**
     * Get the extension of the photo URI associated to the cocktail
     * @return A string representing the photo URI extension
     */
    public String getPhotoURIExt() {
        return photoURI.split("\\.")[1];
    }

    /**
     * Get the photo URI associated to the cocktail
     * @return A string representing the photo URI
     */
    public String getPhotoURI() {
        return photoURI;
    }

    /**
     * Set the photo URI associated to the cocktail
     * @param photoURI A string representing the photo URI
     */
    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }

    /**
     * Set the recipe associated to the cocktail
     * @return A string representing the recipe associated to the cocktail
     */
    public String getRecipe() {
        return recipe;
    }

    /**
     * Get the recipe associated to the cocktail
     * @param recipe string representing the recipe
     */
    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    /**
     * Get the flavor associated to the cocktail
     * @return A CocktailFlavorEnum representing the flavor associated to the cocktail
     */
    public CocktailFlavorEnum getFlavor() {
        return flavor;
    }

    /**
     * Set the flavor associated to the cocktail
     * @param flavor A CocktailFlavorEnum representing the flavor
     */
    public void setFlavor(CocktailFlavorEnum flavor) {
        this.flavor = flavor;
    }

    /**
     * Get the power associated to the cocktail
     * @return A CocktailPowerEnum representing the power associated to the cocktail
     */
    public CocktailPowerEnum getPower() {
        return power;
    }

    /**
     * Set the power associated to the cocktail
     * @param power A CocktailPowerEnum representing the power
     */
    public void setPower(CocktailPowerEnum power) {
        this.power = power;
    }

    /**
     * Get the list of deliverables contained in the cocktail
     * @return A list of Deliverable contained in the cocktail
     */
    public List<Deliverable> getDeliverables() {
        return deliverables;
    }

    /**
     * Set the list of deliverables contained in the cocktail
     * @param deliverables A list of Deliverable
     */
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

    /**
     * Get the client who proposed the cocktail
     * @return A ClientAccountEntity representing the author or NULL if the cocktail was added by the administrator
     */
    public ClientAccountEntity getClient() {
        return client;
    }

    /**
     * Set the client who proposed the cocktail
     * @param client A ClientAccountEntity representing the author
     */
    public void setClient(ClientAccountEntity client) {
        this.client = client;
    }

    /**
     * Get the boolean that indicates if the cocktail is virgin
     * @return True if the cocktail is virgin, false otherwise
     */
    public Boolean getVirgin() {
        return virgin;
    }

    /**
     * Set the boolean that indicates if the cocktail is virgin
     * @param virgin A boolean that indicates if the cocktail is virgin
     */
    public void setVirgin(Boolean virgin) {
        this.virgin = virgin;
    }

    /**
     * Get a string representation of the cocktail
     * @return A string representing the cocktail
     */
    @Override
    public String toString() {
        return "entity.CocktailEntity[" + ID + " : " + name + "]";
    }

    /**
     * Compare two CocktailEntity
     * @param c The CocktailEntity to compare with
     * @return 0 if cocktails are equals, 1 if the cocktail is greater than the one passed in param, -1 otherwise
     */
    @Override
    public int compareTo(CocktailEntity c) {
        if(c.getName().compareTo(getName()) == 0){
            return 0;
        } else if (getName().compareTo(c.getName()) > 0){
            return 1;
        } else {
            return -1;
        }
    }
}

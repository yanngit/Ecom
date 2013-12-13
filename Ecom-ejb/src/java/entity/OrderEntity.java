/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import pojo.OrderStateEnum;
import java.util.List;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NamedQueries({
    @NamedQuery(name = "getOrdersOfAddress", query = "SELECT o FROM OrderEntity o inner join o.addresses oa WHERE oa.surname = :surname and oa.first_name = :first_name and oa.street = :street")
})
@Entity
@Table(name="ORDERENTITY")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    /**
     * Identifier of the order
     */
    protected Long id;
    @Column(name="STATUS")
    @Enumerated(value=EnumType.ORDINAL)
    /**
     * Status of the order
     */
    protected OrderStateEnum status;
    @ManyToMany
    @JoinTable(name="ORDER_ADDRESS",
        joinColumns=@JoinColumn(
            name="ORDER_ID",
            referencedColumnName="ID"),
        inverseJoinColumns=@JoinColumn(
            name="ADDRESS_ID",
            referencedColumnName="ID"))
    @NotNull
    /**
     * List of addresses associated to the order
     */
    protected List<AddressEntity> addresses;
    
    @ManyToMany
    @JoinTable(name="ORDER_COCKTAIL",
        joinColumns=@JoinColumn(
            name="ORDER_ID",
            referencedColumnName="ID"),
        inverseJoinColumns=@JoinColumn(
            name="COCKTAIL_ID",
            referencedColumnName="ID"))
    @NotNull
    /**
     * List of cocktails associated to the order
     */
    protected List<CocktailEntity> cocktails;

    /**
     * Get the list of cocktails associated to the order
     * @return A list of CocktailEntity associated to the order
     */
    public List<CocktailEntity> getCocktails(){
        return cocktails;
    }
    
    /**
     * Set the list of cocktails associated to the order
     * @param list A list of CocktailEntity
     */
    public void setCocktails(List<CocktailEntity> list){
        cocktails = list;
    }
    
    /**
     * Get the list of addresses associated to the order
     * @return A list of AddressEntity associated to the order
     */
    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    /**
     * Set the list of addresses associated to the order
     * @param addresses A list of AddressEntity
     */
    public void setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
    }
    
    /**
     * Get the status of the order
     * @return An OrderStateEnum representing the state r
     */
    public OrderStateEnum getStatus() {
        return status;
    }

    /**
     * Set the status of the order
     * @param status An OrderStateEnum representing the state
     */
    public void setStatus(OrderStateEnum status) {
        this.status = status;
    }

    /**
     * Set the identifier associated to the order
     * @param id A long representing the order identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the identifier associated to the order
     * @return A long representing the order identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the hash code of the order
     * @return An int representing the hash code of the order
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Compare two orders 
     * @param object The object to compare 
     * @return True if orders are equals, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OrderEntity)) {
            return false;
        }
        OrderEntity other = (OrderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Get a string representation of the order
     * @return A string representing the order
     */
    @Override
    public String toString() {
        return "entity.OrderEntity[ id=" + id + " ]";
    }
    
}

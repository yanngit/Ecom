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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ORDERENTITY")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    protected Long id;
    @Column(name="STATUS")
    @Enumerated(value=EnumType.ORDINAL)
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
    protected List<CocktailEntity> cocktails;

    public List<CocktailEntity> getCocktails(){
        return cocktails;
    }
    
    public void setCocktails(List<CocktailEntity> list){
        cocktails = list;
    }
    
    
    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
    }
    
    /*@JoinColumn(name = "CONTENT")
    protected List<CocktailEntity> content;*/

    public OrderStateEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStateEnum status) {
        this.status = status;
    }

    /*public List<CocktailEntity> getContent() {
        return content;
    }*/

    /*public void setContent(List<CocktailEntity> content) {
        this.content = content;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderEntity)) {
            return false;
        }
        OrderEntity other = (OrderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderEntity[ id=" + id + " ]";
    }
    
}

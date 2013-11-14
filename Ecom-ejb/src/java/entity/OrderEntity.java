/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import pojo.OrderStateEnum;
import java.util.List;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author bach
 */
@Entity
@Table(name="Order")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    protected Long id;
    @Column(name="STATE")
    protected OrderStateEnum state;
    @Column(name="CLIENT")
    protected ClientAccountEntity client;
    @Column(name="DELIVERY ADDRESS")
    protected String delivery_address;
    @Column(name = "CONTENT")
    protected List<CocktailEntity> content;

    public OrderStateEnum getState() {
        return state;
    }

    public void setState(OrderStateEnum state) {
        this.state = state;
    }

    public ClientAccountEntity getClient() {
        return client;
    }

    public void setClient(ClientAccountEntity client) {
        this.client = client;
    }

    public String getAddress() {
        return delivery_address;
    }

    public void setAddress(String address) {
        this.delivery_address = address;
    }

    public List<CocktailEntity> getContent() {
        return content;
    }

    public void setContent(List<CocktailEntity> content) {
        this.content = content;
    }

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

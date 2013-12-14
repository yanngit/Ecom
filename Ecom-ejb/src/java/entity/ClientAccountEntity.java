/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author yann
 */
@NamedQueries({
    @NamedQuery(name = "getAccountByAuthentification", query = "SELECT a FROM ClientAccountEntity a WHERE a.login = :login and a.password = :password")
})
@Entity
@Table(name = "CLIENTACCOUNT")
public class ClientAccountEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    /**
     * Identifier of the client account
     */
    protected Long id;
    @Column(name = "LOGIN")
    @NotNull
    /**
     * Login of the client account
     */
    protected String login;
    @Column(name = "PASSWORD")
    @NotNull
    /**
     * Password of the client account : must be encrypt before
     */
    protected String password;
    @OneToOne
    @NotNull
    /**
     * Delivery address for the client account
     */
    protected AddressEntity delivery_address;
    /* Cascade : persist the cocktails proposed by this client when you persist
     *    or merge it.
     */
    @OneToMany(mappedBy = "client",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    /**
     * List of cocktails proposed by the user of the client account
     */
    protected List<CocktailEntity> cocktails;

    /**
     * Get the identifier associated to the client address
     * @return A long representing the client address identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the identifier associated to the client account
     * @param id A long representing the client account identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the login associated to the client account
     * @return A string representing the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the login associated to the client account
     * @param login A string representing the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get the password associated to the client account
     * @return A string representing the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password associated to the client account
     * @param password A string representing the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the delivery address associated to the client account
     * @return An AddressEntity representing the delivery address
     */
    public AddressEntity getDelivery_address() {
        return delivery_address;
    }

    /**
     * Get the delivery address associated to the client account
     * @param delivery_address An AddressEntity representing the delivery address
     */
    public void setDelivery_address(AddressEntity delivery_address) {
        this.delivery_address = delivery_address;
    }

    /**
     * Get the hash code of the client account
     * @return An int representing the hash code of the client address
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

   /**
     * Compare two client account 
     * @param object The object to compare 
     * @return True if accounts are equals, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientAccountEntity)) {
            return false;
        }
        ClientAccountEntity other = (ClientAccountEntity) object;
        if ((!this.login.equals(other.login))
                || (!this.password.equals(other.password))) {
            return false;
        }
        return true;
    }

    /**
     * Get a string representation of the client account
     * @return A string representing the client account
     */
    @Override
    public String toString() {
        return "entity.ClientAccount[ id=" + id + ", login=" + login + " ]";
    }
}

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CLIENTACCOUNT")
public class ClientAccountEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    protected Long id;
    @Column(name = "LOGIN")
    @NotNull
    protected String login;
    @Column(name = "PASSWORD")
    @NotNull
    protected String password;
    /* Cascade : when you persist or merge an account, persist or merge its
     *    address.
     */
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @NotNull
    protected AddressEntity delivery_address;
    /* Cascade : persist the cocktails proposed by this client when you persist
     *    or merge it.
     */
    @OneToMany(mappedBy = "client",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    protected List<CocktailEntity> cocktails;

    /*Il manque la liste d'order*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AddressEntity getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(AddressEntity delivery_address) {
        this.delivery_address = delivery_address;
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

    @Override
    public String toString() {
        return "entity.ClientAccount[ id=" + id + ", login=" + login + " ]";
    }
}

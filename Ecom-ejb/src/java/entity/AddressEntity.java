/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SURNAME")
    protected String surname;
    @Column(name = "FIRST_NAME")
    protected String first_name;
    @Column(name = "Street")
    protected String street;
    @Column(name = "POSTAL_CODE")
    protected String postal_code;
    @Column(name = "CITY")
    protected String city;
    @Column(name = "COUNTRY")
    protected String country;
    @ManyToMany(mappedBy = "addresses")
    protected List<OrderEntity> orders;
    

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        if (!(object instanceof AddressEntity)) {
            return false;
        }
        AddressEntity other = (AddressEntity) object;
        if ((!this.surname.equals(other.surname))
                || (!this.street.equals(other.street))
                || (!this.city.equals(other.city))
                || (!this.country.equals(other.country))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Address[" + id + "] : \n"
                + "\t" + first_name + " " + surname + "\n"
                + "\t" + street + "\n"
                + "\t" + postal_code + " " + city + "\n"
                + "\t" + country.toUpperCase();
    }
}

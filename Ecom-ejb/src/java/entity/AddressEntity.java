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
    /**
     * Identifier of the address
     */
    private Long id;
    @Column(name = "SURNAME")
    /**
     * Receiver last name
     */
    protected String surname;
    @Column(name = "FIRST_NAME")
    /**
     * Receiver first name  
     */
    protected String first_name;
    @Column(name = "Street")
    /**
     * Address street
     */
    protected String street;
    @Column(name = "POSTAL_CODE")
    /**
     * Address postal code
     */
    protected String postal_code;
    @Column(name = "CITY")
    /**
     * Address city
     */
    protected String city;
    @Column(name = "COUNTRY")
    /**
     * Address country
     */
    protected String country;
    @ManyToMany(mappedBy = "addresses")
    /**
     * List of orders associated to the address
     */
    protected List<OrderEntity> orders;
    

    /**
     * Get associated orders
     * @return List of OrderEntity representing orders associated to the address
     */
    
    public List<OrderEntity> getOrders() {
        return orders;
    }

    /**
     * Set associated orders
     * @param orders List of OrderEntity representing orders
     */
    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    /**
     * Get the surname associated to the address
     * @return A string representing the surname of the address
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the surname associated to the address
     * @param surname A string representing the surname 
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Get the first name associated to the address
     * @return A string representing the first name of the address
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Set the first name associated to the address
     * @param first_name A string representing the first name 
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Get the street associated to the address
     * @return A string representing the street associated to the address
     */
    public String getStreet() {
        return street;
    }
    
    /**
     * Set the street associated to the address
     * @param street A string representing the street 
     */
    public void setStreet(String street) {
        this.street = street;
    }
    
    /**
     * Get the postal code associated to the address
     * @return A string representing the postal code associated to the address
     */
    public String getPostal_code() {
        return postal_code;
    }
    
    /**
     * Set the postal code associated to the address
     * @param postal_code A string representing the postal code 
     */
    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }
    
    /**
     * Get the city associated to the address
     * @return A string representing the city associated to the address 
     */
    public String getCity() {
        return city;
    }
    
    /**
     * Set the city associated to the address
     * @param city A string representing the city
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * Get the country associated to the address
     * @return A string representing the country associated to the address 
     */
    public String getCountry() {
        return country;
    }
    
    /**
     * Set the country associated to the address
     * @param country A string representing the country
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    /**
     * Get the identifier associated to the address
     * @return A long representing the address identifier
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Set the identifier associated to the address
     * @param id A long representing the address identifier
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Get the hash code of the address
     * @return An int representing the hash code of the address
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    /**
     * Compare two addresses 
     * @param object The object to compare 
     * @return True if addresses are equals, false otherwise
     */
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
    
    /**
     * Get a string representation of the address
     * @return A string representing the address
     */
    @Override
    public String toString() {
        return "pojo.Address[" + id + "] : \n"
                + "\t" + first_name + " " + surname + "\n"
                + "\t" + street + "\n"
                + "\t" + postal_code + " " + city + "\n"
                + "\t" + country.toUpperCase();
    }
}

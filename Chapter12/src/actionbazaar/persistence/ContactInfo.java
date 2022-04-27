package actionbazaar.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "ContactInfo.findAll", query = "select o from ContactInfo o")
})
@Table(name = "CONTACT_DETAILS")
public class ContactInfo implements Serializable {
    private String city;
    private String contactUserId;
    private String country;
    private String email;
    private String phone;
    private String stateCode;
    private String streetname1;
    private String streetname2;
    private String zipCode;

    public ContactInfo() {
    }

    public ContactInfo(String city, String contactUserId, String country,
                       String email, String phone, String stateCode,
                       String streetname1, String streetname2,
                       String zipCode) {
        this.city = city;
        this.contactUserId = contactUserId;
        this.country = country;
        this.email = email;
        this.phone = phone;
        this.stateCode = stateCode;
        this.streetname1 = streetname1;
        this.streetname2 = streetname2;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Id
    @Column(name="CONTACT_USER_ID", nullable = false)
    public String getContactUserId() {
        return contactUserId;
    }

    public void setContactUserId(String contactUserId) {
        this.contactUserId = contactUserId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 10)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name="STATE_CODE")
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStreetname1() {
        return streetname1;
    }

    public void setStreetname1(String streetname1) {
        this.streetname1 = streetname1;
    }

    public String getStreetname2() {
        return streetname2;
    }

    public void setStreetname2(String streetname2) {
        this.streetname2 = streetname2;
    }

    @Column(name="ZIP_CODE")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}

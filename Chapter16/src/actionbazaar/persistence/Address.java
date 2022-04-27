package actionbazaar.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {
    
  private String country;
  private String city;
  private String stateCode;
  private String streetname1;
  private String streetname2;
  private String zipCode;

    
    public Address() {
        super();
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
  @Column(name="STATE_CODE")
    public String getStateCode() {
        return stateCode;
    }

    public void setStreetname1(String streetname1) {
        this.streetname1 = streetname1;
    }

    public String getStreetname1() {
        return streetname1;
    }

    public void setStreetname2(String streetname2) {
        this.streetname2 = streetname2;
    }

    public String getStreetname2() {
        return streetname2;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
  @Column(name="ZIP_CODE")
    public String getZipCode() {
        return zipCode;
    }
}

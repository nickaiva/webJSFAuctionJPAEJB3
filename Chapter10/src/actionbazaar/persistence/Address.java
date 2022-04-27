package actionbazaar.persistence;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {
    
  protected String streetName1;
   protected String streetName2;
   protected String city;
   protected String state;
   protected String zipCode;
   protected String country;


   public Address() {}

  public Address(String streetName1, String streetName2, String city,
                  String state, String zipCode, String country) {
     this.streetName1 = streetName1;
     this.streetName2 = streetName2;
     this.city = city;
     this.state = state;
     this.zipCode = zipCode;
     this.country = country;
   }


    public void setStreetName1(String streetName1) {
        this.streetName1 = streetName1;
    }

    public String getStreetName1() {
        return streetName1;
    }

    public void setStreetName2(String streetName2) {
        this.streetName2 = streetName2;
    }

    public String getStreetName2() {
        return streetName2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}

package actionbazaar.persistence;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Address  implements java.io.Serializable {
    @SuppressWarnings("compatibility:4385704072977973959")
    private static final long serialVersionUID = 8608794720962208552L;
  @Column(name="STREETNAME1", nullable=false)
    protected String streetName1;
  @Column(name="STREETNAME2", nullable=false)
    protected String streetName2;
    @Column(name = "CITY")
    protected String city;
    @Column(name="ZIP_CODE")
    protected String zipCode;
  @Column(name="STATE_CODE")
    protected String state;
  @Column(name="COUNTRY", nullable=false)
    protected String country;
  
    public Address() {   }
  
  public Address(String streetName1, 
                        String streetName2,
                        String city, 
                        String zipCode, 
                        String state,
                        String country) {
      this.streetName1=streetName1;
      this.streetName2=streetName2;
      this.city=city;
      this.state =state;
      this.zipCode=zipCode;
      this.country=country;
      }
  
    public void setStreetName1(String streetName1) {
        this.streetName1 = streetName1.toUpperCase();
    }

    public String getStreetName1() {
        return streetName1;
    }

    public void setStreetName2(String streetName2) {
        this.streetName2 = streetName2.toUpperCase();
    }

    public String getStreetName2() {
        return streetName2;
    }

  public void setZipCode(String zipCode) {
      this.zipCode = zipCode.toUpperCase();
  }

  public String getZipCode() {
      return zipCode;
  }
  
    public void setCity(String city) {
        this.city = city.toUpperCase();
    }

    public String getCity() {
        return city;
    }

  

    public void setState(String state) {
        this.state = state.toUpperCase();
    }

    public String getState() {
        return state;
    }

  

    public void setCountry(String country) {
        this.country = country.toUpperCase();
    }

    public String getCountry() {
        return country;
    }
}

package actionbazaar.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "ShippingInfo.findAll", query = "select o from ShippingInfo o")
})
@Table(name = "SHIPPING_INFO")
public class ShippingInfo implements Serializable {
    private String city;
    private Long shippingId;
    private String state;
    private String street;

    public ShippingInfo() {
    }
  
  public ShippingInfo(String street, String city, String state ) {
      this.street = street;
      this.city = city;
      this.state = state; 
    }

  
    public ShippingInfo(String city, Long shippingId, String state,
                        String street) {
        this.city = city;
        this.shippingId = shippingId;
        this.state = state;
        this.street = street;
    }

    @Column(length = 30)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Id
    @Column(name="SHIPPING_ID", nullable = false)
  @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    @Column(length = 30)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(length = 30)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

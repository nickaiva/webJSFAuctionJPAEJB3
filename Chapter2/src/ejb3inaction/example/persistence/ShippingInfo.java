package ejb3inaction.example.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Id
    @Column(name="SHIPPING_ID", nullable = false)
    private Long shippingId;
    private String state;
    private String street;

    public ShippingInfo() {
    }

    public ShippingInfo(String city, Long shippingId, String state,
                        String street) {
        this.city = city;
        this.shippingId = shippingId;
        this.state = state;
        this.street = street;
    }

   

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

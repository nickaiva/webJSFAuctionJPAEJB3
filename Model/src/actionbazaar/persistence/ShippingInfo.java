package actionbazaar.persistence;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@NamedQueries({
  @NamedQuery(name = "ShippingInfo.findAll", query = "select o from ShippingInfo o"),
   @NamedQuery(name = "ShippingInfo.findByShippingId", query = "select o from ShippingInfo o where o.shippingId = :shippingId "),
   @NamedQuery(name = "ShippingInfo.findByShippingAddressStreetName1", query = "select o from ShippingInfo o where o.address.streetName1 LIKE :streetName1 ")
})
@Table(name = "SHIPPING_INFO")
public class ShippingInfo implements Serializable {
    @SuppressWarnings("compatibility:-2719022250416352972")
    private static final long serialVersionUID = -7676475345104870359L;
    @Id
    @Column(name="SHIPPING_ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "SHIPPING_SEQ")
    private Long shippingId;

    @Embedded
    private Address address;
    @OneToMany(mappedBy = "shippingInfo")
    private Set<Order> orderSet;
 /*   @Column(name="OPT_LOCK")
    private Long optLock;
*/
    public ShippingInfo() {
    }

    public ShippingInfo( Long shippingId,Address address) {
        this.shippingId = shippingId;
        this.address = address;
             
    }

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

    public Order addOrder(Order order) {
        getOrderSet().add(order);
        order.setShippingInfo(this);
        return order;
    }

    public Order removeOrder(Order order) {
        getOrderSet().remove(order);
        order.setShippingInfo(null);
        return order;
    }
/*
    public void setOptLock(Long optLock) {
        this.optLock = optLock;
    }

    public Long getOptLock() {
        return optLock;
    }
*/

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}

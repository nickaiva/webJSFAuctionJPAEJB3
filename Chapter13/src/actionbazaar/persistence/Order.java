package actionbazaar.persistence;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "Order.findAll", query = "select o from Order o")
})
@Table(name = "ORDERS")
public class Order implements Serializable {
    private Bidder bidder;
    private  BillingInfo billingInfo;
    private Long orderId;
    private ShippingInfo shippingInfo;
    private OrderStatus orderStatus;
    private List<Item> items; 
    
    public Order() {
    }

    public Order( Long orderId,
                  OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }

    @Id
    @Column(name="ORDER_ID", nullable = false)
  @GeneratedValue(strategy=GenerationType.AUTO)
      
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Column(name="ORDER_STATUS", length = 10)
    @Enumerated(EnumType.STRING)
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "ORDER_BIDDER_ID",referencedColumnName = "USER_ID")
    public Bidder getBidder() {
        return bidder;
    }

    public void setBillingInfo( BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }
@OneToOne(cascade=CascadeType.ALL)
//@JoinColumn(name = "ORDER_SHIPPING_ID", referencedColumnName = "USER_BILLING_ID")
  @PrimaryKeyJoinColumn(name="ORDER_SHIPPING_ID",referencedColumnName="USER_BILLING_ID")
    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }
@OneToOne(cascade = CascadeType.ALL)
//@JoinColumn(name = "ORDER_SHIPPING_ID", referencedColumnName = "SHIPPING_ID")
 @PrimaryKeyJoinColumn(name="ORDER_SHIPPING_ID",referencedColumnName="SHIPPING_ID") 
    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
@OneToMany(mappedBy = "order" , cascade=CascadeType.ALL)
    public List<Item> getItems() {
        return items;
    }
}

package ejb3inaction.example.persistence;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "Order.findAll", query = "select o from Order o")
})
@Table(name = "ORDERS")
public class Order implements Serializable {
    @Column(name="ORDER_BIDDER_ID")
    private Long orderBidderId;
    @Column(name="ORDER_BILLING_ID")
    private Long orderBillingId;
    @Id
    @Column(name="ORDER_ID", nullable = false)
    private Long orderId;
    @Column(name="ORDER_SHIPPING_ID")
    private Long orderShippingId;
    @Column(name="ORDER_STATUS")
    private String orderStatus;

    private List<Long> items;
    private ShippingInfo shippingInfo;
    private BillingInfo billingInfo;

    public Order() {
    }
  
  public void setItems(List<Long> items) {
          this.items = items;
      }
  
    public Order(Long orderBidderId, Long orderBillingId, Long orderId,
                 Long orderShippingId, String orderStatus) {
        this.orderBidderId = orderBidderId;
        this.orderBillingId = orderBillingId;
        this.orderId = orderId;
        this.orderShippingId = orderShippingId;
        this.orderStatus = orderStatus;
    }

    public Long getOrderBidderId() {
        return orderBidderId;
    }

    public void setOrderBidderId(Long orderBidderId) {
        this.orderBidderId = orderBidderId;
    }

    public Long getOrderBillingId() {
        return orderBillingId;
    }

    public void setOrderBillingId(Long orderBillingId) {
        this.orderBillingId = orderBillingId;
    }

    public Long getOrderId() {
        //return orderId;
        return new Long(100);
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderShippingId() {
        return orderShippingId;
    }

    public void setOrderShippingId(Long orderShippingId) {
        this.orderShippingId = orderShippingId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

    public BillingInfo getBillingInfo() {
        return billingInfo;
    }
}

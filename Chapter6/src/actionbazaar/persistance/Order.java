package actionbazaar.persistance;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private String orderBidderId;
    @Id
    @Column(name="ORDER_ID", nullable = false)
    private Long orderId;
    @Column(name="ORDER_SHIPPING_ID")
    private Long orderShippingId;
    @Column(name="ORDER_STATUS")
    private String orderStatus;
    @ManyToOne
    @JoinColumn(name = "ORDER_BILLING_ID")
    private BillingDetails billingDetails;

    public Order() {
    }

    public Order(String orderBidderId, BillingDetails billingDetails, Long orderId,
                 Long orderShippingId, String orderStatus) {
        this.orderBidderId = orderBidderId;
        this.billingDetails = billingDetails;
        this.orderId = orderId;
        this.orderShippingId = orderShippingId;
        this.orderStatus = orderStatus;
    }

    public String getOrderBidderId() {
        return orderBidderId;
    }

    public void setOrderBidderId(String orderBidderId) {
        this.orderBidderId = orderBidderId;
    }


    public Long getOrderId() {
        return orderId;
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

    public BillingDetails getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(BillingDetails billingDetails) {
        this.billingDetails = billingDetails;
    }
}

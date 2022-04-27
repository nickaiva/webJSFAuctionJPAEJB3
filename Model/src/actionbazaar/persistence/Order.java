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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/*@TableGenerator (name="ORDER_TABLE_GENERATOR",
table="SEQUENCE",
pkColumnName="SEQ_NAME",
valueColumnName="SEQ_COUNT",
pkColumnValue="SEQ_GEN", initialValue =100 , allocationSize = 20, schema = "AB")
*/
@SequenceGenerator(name="ORDER_SEQUENCE_GENERATOR",
                  sequenceName="ORDER_SEQUENCE",
                    initialValue=100, allocationSize=20)
@Entity
@NamedQueries({
  @NamedQuery(name = "Order.findAll", query = "select o from Order o"),
  @NamedQuery(name = "Order.findByOrderId", query = "select o from Order o where o.orderId = :orderId "),
   @NamedQuery(name = "Order.findByBidder", query = "select o from Order o where o.bidder.userId LIKE :bidderId "),
  @NamedQuery(name = "Order.findByStatus", query = "select o from Order o where o.orderStatus = :orderStatus "),
   @NamedQuery(name = "Order.findByBillingInfo", query = "select o from Order o where o.billingInfo = :billingInfo "),
   @NamedQuery(name = "Order.findByShippingInfo", query = "select o from Order o where o.shippingInfo = :shippingInfo ")
  
})
@Table(name = "ORDERS")
public class Order implements Serializable {
    @SuppressWarnings("compatibility:-1883252575878923679")
    private static final long serialVersionUID = 1L;
    @OneToOne(cascade=CascadeType.ALL) 
      @JoinColumn(name="ORDER_BIDDER_ID", referencedColumnName="USER_ID")                           
      private Bidder bidder;   
  @Id
  // @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "ORDER_SEQUENCE"  )
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "ORDER_SEQUENCE_GENERATOR")
  //@GeneratedValue(strategy = GenerationType.TABLE ,generator = "ORDER_TABLE_GENERATOR")
  //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ORDER_ID", nullable = false )
    private Long orderId;
    @Column(name="ORDER_STATUS", length = 10)
  @Enumerated(EnumType.STRING) 
    private OrderStatus orderStatus;
  @OneToMany(mappedBy="order")//cascade=CascadeType.ALL
     private List<Item> items;                                       
  @OneToOne(cascade=CascadeType.ALL)  
      @JoinColumn(name="ORDER_BILLING_ID", referencedColumnName="BILLING_ID")
    private BillingInfo billingInfo;
  @OneToOne(cascade=CascadeType.ALL)  
      @JoinColumn(name="ORDER_SHIPPING_ID", referencedColumnName="SHIPPING_ID") 
    private ShippingInfo shippingInfo;

    public Order() {
    }

    public Order( BillingInfo billingInfo,
                        Long orderId,
                        ShippingInfo shippingInfo, 
                        OrderStatus orderStatus) {
       
        this.billingInfo = billingInfo;
        this.orderId = orderId;
        this.shippingInfo = shippingInfo;
        this.orderStatus = orderStatus;
    }

  

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }

    public Bidder getBidder() {
        return bidder;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public List getItems() {
        return items;
    }
}

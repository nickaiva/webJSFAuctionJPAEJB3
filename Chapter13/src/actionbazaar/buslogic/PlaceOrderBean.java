package actionbazaar.buslogic;

import actionbazaar.persistence.Bidder;
import actionbazaar.persistence.BillingInfo;
import actionbazaar.persistence.Item;
import actionbazaar.persistence.Order;
import actionbazaar.persistence.ShippingInfo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import javax.ejb.TransactionAttribute;

import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateful(name = "PlaceOrder", mappedName = "ejb3inaction-Chapter13-PlaceOrder")
@Remote
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)       
/* */
public class PlaceOrderBean implements PlaceOrder {
    
  // Use EntityManager with Extended Persistence Context
  @PersistenceContext(unitName = "Chapter13",
                      type = PersistenceContextType.EXTENDED) 
      EntityManager em;

  /** Define instance variables to store the entities
      those stay managed between method invocations **/
                                            
      private List<Item> items;                                       
      private ShippingInfo shippingInfo;                              
      private BillingInfo billingInfo;   
      private Bidder bidder;
      private Order order;                             

      public PlaceOrderBean () {
          items = new ArrayList<Item>();
          order = new Order();
      }

    public void setBidder(String bidderId) {
      this.bidder = em.find(Bidder.class,bidderId);
    }

    public void addItem(Long itemId) {
              Item item = em.find(Item.class,itemId);
              items.add(item);
              item.setOrder(order);
    }

    public void setShippingInfo(ShippingInfo shippingInfo) {
      this.shippingInfo = shippingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
      this.billingInfo = billingInfo;
    }

  /** This method is invoked at the end of work flow
       persistence context is flushed
       at the end of the method and entities become detached
  **/
   @Remove                                                           
   @TransactionAttribute(TransactionAttributeType.REQUIRED)          
   public Long confirmOrder() {
     order.setBidder(bidder);
     order.setItems(items);
     order.setShippingInfo(shippingInfo);
     order.setBillingInfo(billingInfo);
     em.persist(order);
     return order.getOrderId();
   }

}

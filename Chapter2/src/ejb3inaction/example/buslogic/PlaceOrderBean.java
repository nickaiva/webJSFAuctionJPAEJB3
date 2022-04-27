package ejb3inaction.example.buslogic;

import ejb3inaction.example.persistence.Bid;
import ejb3inaction.example.persistence.ShippingInfo;
import ejb3inaction.example.persistence.BillingInfo;


import ejb3inaction.example.persistence.Order;

import java.util.List;
import java.util.ArrayList;

import javax.annotation.Resource;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;

import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateful(name = "PlaceOrder", mappedName = "PlaceOrder")
@Remote
public class PlaceOrderBean implements PlaceOrder {
    @PersistenceContext(unitName="actionBazaar")
    private EntityManager em;
    private Long bidderID;
    private List<Long> items;
    private ShippingInfo shippingInfo;
    private BillingInfo billingInfo;
    
    @Resource(name = "jms/QueueConnectionFactory",mappedName="weblogic.examples.ejb30.QueueConnectionFactory")
     private QueueConnectionFactory connectionFactory;

     @Resource(name = "jms/OrderBillingQueue", mappedName="jms/OrderBillingQueue")
     private Queue billingQueue;
  
      public PlaceOrderBean() {
        items = new ArrayList<Long>();
    }

  public void setBidderId(Long bidderId) {
          this.bidderID = bidderId;
      }
  
  private void billOrder(Order order) {
          try {
              QueueConnection connection = connectionFactory.createQueueConnection();
              QueueSession session = connection.createQueueSession(false,
                      QueueSession.AUTO_ACKNOWLEDGE);
              MessageProducer producer = session.createProducer(billingQueue);
              ObjectMessage message = session.createObjectMessage();
              message.setObject(order);
              producer.send(message);
              producer.close();
              session.close();
              connection.close();
          } catch (Exception e) {
              e.printStackTrace();
          }

      }

      public void addItem(Long itemId) {
          items.add(itemId);
      }

    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public Bid persistBid(Bid bid) {
        em.persist(bid);
        return bid;
    }

    public Bid mergeBid(Bid bid) {
        return em.merge(bid);
    }

    public void removeBid(Bid bid) {
        bid = em.find(Bid.class, bid.getBidId());
        em.remove(bid);
    }

    /** <code>select o from Bid o</code> */
    public List<Bid> getBidFindAll() {
        return em.createNamedQuery("Bid.findAll").getResultList();
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
  
  private void saveOrder(Order order) {
        // implement code to save order

    }


    

   @Remove
    public Long confirmOrder() {
              Order order = new Order();
              order.setOrderBidderId(bidderID); 
              order.setItems(items);
              order.setShippingInfo(shippingInfo);
              order.setBillingInfo(billingInfo);
              saveOrder(order);
              billOrder(order);
              return order.getOrderId();
        }

    public void setItems(List<Long> items) {
        this.items = items;
    }

    public List<Long> getItems() {
        return items;
    }
}

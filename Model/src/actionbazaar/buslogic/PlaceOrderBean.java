package actionbazaar.buslogic;


import actionbazaar.persistence.Bidder;
import actionbazaar.persistence.BillingInfo;
import actionbazaar.persistence.Item;
import actionbazaar.persistence.Order;
import actionbazaar.persistence.OrderStatus;
import actionbazaar.persistence.ShippingInfo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.Query;


@Stateless(name = "PlaceOrder", mappedName = "ejb3inaction-Model-PlaceOrder")
@Remote
@Local
@DeclareRoles(value = { "BIDDER", "ADMINISTRATOR" })
//@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PlaceOrderBean extends CustomBean implements PlaceOrder,
                                                          PlaceOrderLocal {


    // Use EntityManager with Extended Persistence Context for StateFull only!


    /** Define instance variables to store the entities
      those stay managed between method invocations **/

    private List<Item> items;
    private ShippingInfo shippingInfo;
    private BillingInfo billingInfo;
    private Bidder bidder;
    private Order order;

    public PlaceOrderBean() {
        items = new ArrayList<Item>();
        order = new Order();
        billingInfo = new BillingInfo();
        shippingInfo = new ShippingInfo();
    }


    /** This method is invoked at the end of work flow
        persistence context is flushed
        at the end of the method and entities become detached

    @Remove
     **/
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long confirmOrder() {
        
        order.setBidder(bidder);
        order.setItems(items);
        order.setOrderStatus(OrderStatus.COMPLETE);
        order.setShippingInfo(shippingInfo);
        order.setBillingInfo(billingInfo);
        em.persist(order);
        // //em.flush();
        if (logger.isDebugEnabled())
            logger.debug("ORDER CONFIRMED!");

        return order.getOrderId();
    }

    public void setBidder(String bidderId) {
        this.bidder = em.find(Bidder.class, bidderId);
        logger.debug("Found Bidder with  BidderId: " + bidder.getUserId());
    }

    public void addItem(Long itemId) {
        
        Item item = em.find(Item.class, itemId);
        if (logger.isDebugEnabled())
            logger.debug("Adding item with itemId: " + item.getItemId());
        items.add(item);
        item.setOrder(order);
    }

    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public void setShippingInfo(Long shippingId) {
        this.shippingInfo = em.find(ShippingInfo.class, shippingId);
        if (logger.isDebugEnabled())
            logger.debug("Found shippingInfo with shippingId: " +
                         shippingInfo.getShippingId());
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

    public void setBillingInfo(Long billingId) {
        this.billingInfo = em.find(BillingInfo.class, billingId);
        if (logger.isDebugEnabled())
            logger.debug("Found billingInfo with billingrId: " +
                         billingInfo.getBillingId());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long addOrder(String bidderId, 
                                    ArrayList<Long> itemsId,
                                    Long shippingId,
                                    Long billingId) {
        /*Avoid exception due to use of same sequence value for two consecutive inserts*/
        order.setOrderId(0L);
        setBidder(bidderId);
        setBillingInfo(billingId);
        setShippingInfo(shippingId);
        for (Long itemId : itemsId) {
            addItem(itemId);
        }
        order.setBidder(bidder);
        order.setShippingInfo(shippingInfo);
        order.setBillingInfo(billingInfo);
        order.setItems(items);
        order.setOrderStatus(OrderStatus.NEW);
        em.persist(order);
        //em.flush();
        if (logger.isDebugEnabled())
            logger.debug("Persisted order with new orderId: " +
                         order.getOrderId());

        return this.order.getOrderId();
    }

    /** <code>select o from Order o where o.bidder.userId LIKE :bidderId </code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Order> getOrderFindByBidder(String bidderId) {
        
        String currentUserId = context.getCallerPrincipal().getName();
        if (logger.isDebugEnabled())
            logger.debug("currentUserId " + currentUserId + " is admin? " +
                         context.isCallerInRole("ADMINISTRATOR"));
        if (context.isCallerInRole("ADMINISTRATOR")) {
            if (logger.isDebugEnabled())
                logger.debug("bidderId " + bidderId);
            Query q =
                em.createNamedQuery("Order.findByBidder").setParameter("bidderId",
                                                                       bidderId);
            q.setFirstResult(0);
            q.setMaxResults(10);
            return q.getResultList();
        } else {
            if (logger.isDebugEnabled())
                logger.debug("currentUserId " + currentUserId);
            Query q =
                em.createNamedQuery("Order.findByBidder").setParameter("bidderId",
                                                                       currentUserId);
            q.setFirstResult(0);
            q.setMaxResults(10);
            return q.getResultList();
        }
    }

    public Order mergeOrder(Order order) {
        return em.merge(order);
    }

    public Order persistOrder(Order order) {
        em.persist(order);
        return order;
    }
    
    @RolesAllowed(value = { "ADMINISTRATOR"})
    public void removeOrder(Order order) {
        order = em.find(Order.class, order.getOrderId());
        em.remove(order);
    }

    /**
     * @param orderId
     */
    @RolesAllowed(value = {  "ADMINISTRATOR"})
    public void deleteOrder(Long orderId) {
        order = em.find(Order.class, orderId);
        em.remove(order);
    }

}

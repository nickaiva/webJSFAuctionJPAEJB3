package actionbazaar.buslogic;


import actionbazaar.persistence.Bid;
import actionbazaar.persistence.Bidder;
import actionbazaar.persistence.BillingInfo;
import actionbazaar.persistence.Category;
import actionbazaar.persistence.ContactInfo;
import actionbazaar.persistence.Item;
import actionbazaar.persistence.Order;
import actionbazaar.persistence.Seller;
import actionbazaar.persistence.ShippingInfo;
import actionbazaar.persistence.User;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.Calendar;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.Query;


@Remote
@Local
@Stateless(name = "PlaceItem", mappedName = "ejb3inaction-Model-PlaceItem")
@DeclareRoles(value = { "SELLER", "ADMINISTRATOR" })
public class PlaceItemBean extends CustomBean implements PlaceItem,
                                                         PlaceItemLocal {

    public PlaceItemBean() {
    }


    public ContactInfo persistContactInfo(ContactInfo contactInfo) {
        em.persist(contactInfo);
        return contactInfo;
    }

    public ContactInfo mergeContactInfo(ContactInfo contactInfo) {
        return em.merge(contactInfo);
    }

    public void removeContactInfo(ContactInfo contactInfo) {
        contactInfo =
                em.find(ContactInfo.class, contactInfo.getContactUserId());
        em.remove(contactInfo);
    }

    /** <code>select o from ContactInfo o</code> */
    public List<ContactInfo> getContactInfoFindAll() {
        return em.createNamedQuery("ContactInfo.findAll").getResultList();
    }

    public Seller persistSeller(Seller seller) {
        em.persist(seller);
        return seller;
    }

    public Seller mergeSeller(Seller seller) {
        return em.merge(seller);
    }

    public void removeSeller(Seller seller) {
        seller = em.find(Seller.class, seller.getUserId());
        em.remove(seller);
    }

    /** <code>select o from Seller o</code> */
    public List<Seller> getSellerFindAll() {
        return em.createNamedQuery("Seller.findAll").getResultList();
    }

    public BillingInfo persistBillingInfo(BillingInfo billingInfo) {
        em.persist(billingInfo);
        return billingInfo;
    }

    public BillingInfo mergeBillingInfo(BillingInfo billingInfo) {
        return em.merge(billingInfo);
    }

    public void removeBillingInfo(BillingInfo billingInfo) {
        billingInfo = em.find(BillingInfo.class, billingInfo.getBillingId());
        em.remove(billingInfo);
    }

    /** <code>select o from BillingInfo o</code> */
    public List<BillingInfo> getBillingInfoFindAll() {
        return em.createNamedQuery("BillingInfo.findAll").getResultList();
    }

    public Bidder persistBidder(Bidder bidder) {
        em.persist(bidder);
        return bidder;
    }

    public Bidder mergeBidder(Bidder bidder) {
        return em.merge(bidder);
    }

    public void removeBidder(Bidder bidder) {
        bidder = em.find(Bidder.class, bidder.getUserId());
        em.remove(bidder);
    }

    /** <code>select o from Bidder o</code> */
    public List<Bidder> getBidderFindAll() {
        return em.createNamedQuery("Bidder.findAll").getResultList();
    }

    public Order persistOrder(Order order) {
        em.persist(order);
        return order;
    }

    public Order mergeOrder(Order order) {
        return em.merge(order);
    }

    public void removeOrder(Order order) {
        order = em.find(Order.class, order.getOrderId());
        em.remove(order);
    }

    /** <code>select o from Order o</code> */
    public List<Order> getOrderFindAll() {
        return em.createNamedQuery("Order.findAll").getResultList();
    }

    public Category persistCategory(Category category) {
        em.persist(category);
        return category;
    }

    public Category mergeCategory(Category category) {
        return em.merge(category);
    }

    public void removeCategory(Category category) {
        category = em.find(Category.class, category.getCategoryId());
        em.remove(category);
    }

    /** <code>select o from Category o</code> */
    public List<Category> getCategoryFindAll() {
        return em.createNamedQuery("Category.findAll").getResultList();
    }

    public Item persistItem(Item item) {
        em.persist(item);
        return item;
    }

    public Item mergeItem(Item item) {
        return em.merge(item);
    }

    @RolesAllowed(value = { "ADMINISTRATOR", "SELLER" })
    public void removeItem(Item item) {
        // item = em.find(Item.class, item.getItemId());
        em.remove(em.merge(item));
    }

    @RolesAllowed(value = { "ADMINISTRATOR", "SELLER" })
    public void deleteItem(Long itemId) {
        Item item = em.find(Item.class, itemId);
        em.remove(em.merge(item));
    }

    /** <code>select o from Item o</code> */
    public List<Item> getItemFindAll() {
        return em.createNamedQuery("Item.findAll").getResultList();
    }

    public ShippingInfo persistShippingInfo(ShippingInfo shippingInfo) {
        em.persist(shippingInfo);
        return shippingInfo;
    }

    public ShippingInfo mergeShippingInfo(ShippingInfo shippingInfo) {
        return em.merge(shippingInfo);
    }

    public void removeShippingInfo(ShippingInfo shippingInfo) {
        shippingInfo =
                em.find(ShippingInfo.class, shippingInfo.getShippingId());
        em.remove(shippingInfo);
    }

    /** <code>select o from ShippingInfo o</code> */
    public List<ShippingInfo> getShippingInfoFindAll() {
        return em.createNamedQuery("ShippingInfo.findAll").getResultList();
    }

    public User persistUser(User user) {
        em.persist(user);
        return user;
    }

    public User mergeUser(User user) {
        return em.merge(user);
    }

    public void removeUser(User user) {
        user = em.find(User.class, user.getUserId());
        em.remove(user);
    }

    /** <code>select o from User o</code> */
    public List<User> getUserFindAll() {
        return em.createNamedQuery("User.findAll").getResultList();
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

    /** <code>SELECT b FROM Bid b  WHERE b.bidDate <= :bidDate</code> */
    public List<Bid> getBidsByDate(Date bidDate) {
        return em.createNamedQuery("Bid.getBidsByDate").setParameter("bidDate",
                                                                     bidDate).getResultList();
    }

    public Item updateItem(Long itemId, String itemName, Double initialPrice,
                           String description, byte[] image) {

        Item item = em.find(Item.class, itemId);
        item.setItemName(itemName);
        item.setDescription(description);
        item.setInitialPrice(initialPrice);
        item.setImage(image);
        em.merge(item);
        return item;
    }

    public Item updateItem(Item item) {
        em.merge(item);
        return item;
    }

    public Item undoItemChanges(Item item) {
        em.refresh(em.merge(item));
        return item;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @RolesAllowed(value = { "SELLER", "ADMINISTRATOR" })
    public Long placeItem( String itemName, 
                                    String sellerId,
                                    Double initialPrice,
                                    String description,
                                    byte[] image ) {


        java.util.Date today = new java.util.Date();
        java.sql.Timestamp currentDate =
            new java.sql.Timestamp(today.getTime());

        /* add 5 days of bidding*/
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, +5);
        java.sql.Timestamp bidEndDate =
            new java.sql.Timestamp(endDate.getTimeInMillis());

        Item item =
            new Item(); /*bidEndDate,currentDate,currentDate,initialPrice,0L,itemName,null*/

        item.setBidEndDate(bidEndDate);
        item.setCreatedDate(currentDate);
        item.setBidStartDate(currentDate);
        item.setItemName(itemName);
        item.setInitialPrice(initialPrice);
        item.setDescription(description);
        item.setImage(image);

        try {
            Seller seller = em.find(Seller.class, sellerId);
            item.setSeller(seller);
            em.persist(item);
            //em.flush();
            if (logger.isDebugEnabled())
                logger.debug("itemId " + item.getItemId() + " persisted");
            return item.getItemId();
        } catch (Exception e) {
            context.setRollbackOnly();
            logger.info(e.getMessage());
        }
        return null;
    }


    /** <code>SELECT b FROM Item b WHERE b.seller.userId LIKE :sellerId ORDER BY  b.createdDate DESC</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> getItemGetItemsBySellerId(String sellerId) {
        return em.createNamedQuery("Item.getItemsBySellerId").setParameter("sellerId",
                                                                           sellerId).getResultList();
    }


    /** <code>SELECT b FROM Item b WHERE b.itemName LIKE :itemName ORDER BY  b.createdDate DESC</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> getItemGetItemsByName( String itemName,
                                                                    String userId) {

        String currentUserId = context.getCallerPrincipal().getName();
        if (logger.isDebugEnabled())
            logger.debug("IS CURRENT USER " + currentUserId + "  ADMIN? " +
                         context.isCallerInRole("ADMINISTRATOR"));
        if (context.isCallerInRole("ADMINISTRATOR")) {
            /* context.isCallerInRole("ADMINISTRATOR") returns false if roles  not declared in application.xml*/
            Query query =
                em.createNamedQuery("Item.getItemsByName").setParameter("userId",
                                                                        userId);
            query.setParameter("itemName", itemName);
            return query.getResultList();
        } else {
            Query query =
                em.createNamedQuery("Item.getItemsByName").setParameter("userId",
                                                                        currentUserId);
            query.setParameter("itemName", itemName);
            return query.getResultList();
        }
    }

    /** <code>SELECT b FROM Item b WHERE b.bidEndDate <= :bidEndDate ORDER BY  b.createdDate DESC</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> getItemGetItemsByEndDate(Timestamp bidEndDate) {
        return em.createNamedQuery("Item.getItemsByEndDate").setParameter("bidEndDate",
                                                                          bidEndDate).getResultList();
    }

    /** <code>SELECT b FROM Item b WHERE b.bidStartDate <= :bidStartDate ORDER BY  b.createdDate DESC</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> getItemGetItemsByStartDate(Timestamp bidStartDate) {
        return em.createNamedQuery("Item.getItemsByStartDate").setParameter("bidStartDate",
                                                                            bidStartDate).getResultList();
    }

    /** <code>SELECT b FROM Item b WHERE b.bidStartDate BETWEEN :bidStartDate AND  :bidEndDate ORDER BY  b.createdDate DESC</code> */
    // @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> getItemGetItemsByDateInterval(Timestamp bidStartDate,
                                                    Timestamp bidEndDate) {
        Query q =
            em.createNamedQuery("Item.getItemsByDateInterval").setParameter("bidStartDate",
                                                                            bidStartDate).setParameter("bidEndDate",
                                                                                                       bidEndDate);
        q.setFirstResult(0);
        q.setMaxResults(10);
        return q.getResultList();
    }

    /** <code>select o from Item o WHERE o.bidEndDate <= CURRENT_DATE ORDER BY o.itemName</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> getItemFindRemovedFromFurtherBiddingItems() {
        return em.createNamedQuery("Item.findRemovedFromFurtherBiddingItems").getResultList();
    }

    /** <code>select o, b from Item o, Bid b   WHERE o.bidEndDate <= CURRENT_TIMESTAMP  AND b.bidStatus ='WINNER' AND o.itemId =b.item.itemId ORDER BY o.itemName</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> getItemFindWonItems(String userId) {
        
        String currentUserId = context.getCallerPrincipal().getName();
        if (logger.isDebugEnabled())
            logger.debug("IS CURRENT USER " + currentUserId + "  ADMIN? " +
                         context.isCallerInRole("ADMINISTRATOR"));

        if (context.isCallerInRole("ADMINISTRATOR")) {
            /* context.isCallerInRole("ADMINISTRATOR") returns false if roles  not declared in application.xml*/
            return em.createNamedQuery("Item.findWonItems").setParameter("userId",
                                                                         userId).getResultList();

        } else {
            return em.createNamedQuery("Item.findWonItems").setParameter("userId",
                                                                         currentUserId).getResultList();
        }
    }

    /** <code>select o from Item o WHERE o.bidEndDate > CURRENT_TIMESTAMP ORDER BY o.itemName</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Item> getItemFindAvailableItems() {
        return em.createNamedQuery("Item.findAvailableItems").getResultList();
    }

    @Override
    public byte[] getImageByItemId(Long itemId) {
        return (byte[])em.createNamedQuery("Item.getImageByItemId").setParameter("itemId",
                                                                                 itemId).getSingleResult();
    }



    @Override
    public Item getItemGetItemById(Long itemId) {
        return (Item)em.createNamedQuery("Item.getItemById").setParameter("itemId",
                                                                          itemId).getSingleResult();
    }

}

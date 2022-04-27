package actionbazaar.buslogic;


import actionbazaar.persistence.Address;
import actionbazaar.persistence.Bid;
import actionbazaar.persistence.BidStatus;
import actionbazaar.persistence.Bidder;
import actionbazaar.persistence.BidderStatus;
import actionbazaar.persistence.BillingInfo;
import actionbazaar.persistence.Category;
import actionbazaar.persistence.ContactInfo;
import actionbazaar.persistence.Item;
import actionbazaar.persistence.Order;
import actionbazaar.persistence.OrderStatus;
import actionbazaar.persistence.Seller;
import actionbazaar.persistence.ShippingInfo;
import actionbazaar.persistence.User;

import java.sql.Date;
import java.sql.Timestamp;

import java.util.List;

import javax.annotation.security.DeclareRoles;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;


@Remote
@Local
@DeclareRoles(value = {"BIDDER", "SELLER", "ADMINISTRATOR"})
@Stateless(name = "ManageContactInfo", mappedName = "ejb3inaction-Model-ManageContactInfo")
public class ManageContactInfoBean extends CustomBean implements ManageContactInfo,
                                                                       ManageContactInfoLocal {
   
  
    public ManageContactInfoBean() {
    }


    public ContactInfo persistContactInfo(ContactInfo contactInfo) {
        em.persist(contactInfo);
        return contactInfo;
    }

    public ContactInfo mergeContactInfo(ContactInfo contactInfo) {
        return em.merge(contactInfo);
    }

    public void removeContactInfo(ContactInfo contactInfo) {
        contactInfo = em.find(ContactInfo.class, contactInfo.getContactUserId());
        em.remove(contactInfo);
    }

    /** <code>select o from ContactInfo o</code> */
    public List<ContactInfo> getContactInfoFindAll() {
        return em.createNamedQuery("ContactInfo.findAll").getResultList();
    }

    /** <code>SELECT b FROM ContactInfo b WHERE b.contactUserId LIKE :contactUserId </code> */
     @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ContactInfo> getContactInfoGetContactInfoByUser(String contactUserId) {
              String currentUserId = context.getCallerPrincipal().getName();
              if (logger.isDebugEnabled())
                logger.debug("IS CURRENT USER " + currentUserId + "  ADMIN? " + context.isCallerInRole("ADMINISTRATOR"));
                    
              if (context.isCallerInRole("ADMINISTRATOR")) {
              /* context.isCallerInRole("ADMINISTRATOR") returns always false if not declared as admin mapped to CS_AB_ADMIN 
               * in weblogic-application.xml*/
                    return em.createNamedQuery("ContactInfo.getContactInfoByUser").setParameter("contactUserId", contactUserId).getResultList();
              } else {
                      return em.createNamedQuery("ContactInfo.getContactInfoByUser").setParameter("contactUserId", currentUserId).getResultList();
              }
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

    /** <code>select o from BillingInfo o where o.billingId = :billingId</code> */
    public List<BillingInfo> getBillingInfoFindByBillingId(Long billingId) {
        return em.createNamedQuery("BillingInfo.findByBillingId").setParameter("billingId", billingId).getResultList();
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

    /** <code>select o from Bidder o WHERE o.userId = :userId</code> */
    public List<Bidder> getBidderFindBidsByBidder(String userId) {
        return em.createNamedQuery("Bidder.findBidsByBidder").setParameter("userId", userId).getResultList();
    }

    /** <code>select o from Bidder o WHERE o.bidderStatus = :bidderStatus</code> */
    public List<Bidder> getBidderFindBidderByStatus(BidderStatus bidderStatus) {
        return em.createNamedQuery("Bidder.findBidderByStatus").setParameter("bidderStatus", bidderStatus).getResultList();
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

    /** <code>select o from Order o where o.orderId = :orderId </code> */
    public List<Order> getOrderFindByOrderId(Long orderId) {
        return em.createNamedQuery("Order.findByOrderId").setParameter("orderId", orderId).getResultList();
    }

    /** <code>select o from Order o where o.bidder = :bidder </code> */
    public List<Order> getOrderFindByBidder(Bidder bidder) {
        return em.createNamedQuery("Order.findByBidder").setParameter("bidder", bidder).getResultList();
    }

    /** <code>select o from Order o where o.orderStatus = :orderStatus </code> */
    public List<Order> getOrderFindByStatus(OrderStatus orderStatus) {
        return em.createNamedQuery("Order.findByStatus").setParameter("orderStatus", orderStatus).getResultList();
    }

    /** <code>select o from Order o where o.billingInfo = :billingInfo </code> */
    public List<Order> getOrderFindByBillingInfo(BillingInfo billingInfo) {
        return em.createNamedQuery("Order.findByBillingInfo").setParameter("billingInfo", billingInfo).getResultList();
    }

    /** <code>select o from Order o where o.shippingInfo = :shippingInfo </code> */
    public List<Order> getOrderFindByShippingInfo(ShippingInfo shippingInfo) {
        return em.createNamedQuery("Order.findByShippingInfo").setParameter("shippingInfo", shippingInfo).getResultList();
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

    /** <code>select o from Category o where o.categoryId = :categoryId</code> */
    public List<Category> getCategoryFindByCategoryId(Long categoryId) {
        return em.createNamedQuery("Category.findByCategoryId").setParameter("categoryId", categoryId).getResultList();
    }

    /** <code>select o from Category o where o.createdBy = :createdBy</code> */
    public List<Category> getCategoryFindByCreatedBy(String createdBy) {
        return em.createNamedQuery("Category.findByCreatedBy").setParameter("createdBy", createdBy).getResultList();
    }

    public Item persistItem(Item item) {
        em.persist(item);
        return item;
    }

    public Item mergeItem(Item item) {
        return em.merge(item);
    }

    public void removeItem(Item item) {
        item = em.find(Item.class, item.getItemId());
        em.remove(item);
    }

    /** <code>select o from Item o</code> */
    public List<Item> getItemFindAll() {
        return em.createNamedQuery("Item.findAll").getResultList();
    }

    /** <code>SELECT b FROM Item b WHERE b.seller = :seller ORDER BY  b.createdDate DESC</code> */
    public List<Item> getItemGetItemsBySeller(Seller seller) {
        return em.createNamedQuery("Item.getItemsBySeller").setParameter("seller", seller).getResultList();
    }

    /** <code>SELECT b FROM Item b WHERE b.itemName LIKE :itemName ORDER BY  b.createdDate DESC</code> */
    public List<Item> getItemGetItemsByName(String itemName) {
        return em.createNamedQuery("Item.getItemsByName").setParameter("itemName", itemName).getResultList();
    }

    /** <code>SELECT b FROM Item b WHERE b.bidEndDate <= :bidEndDate ORDER BY  b.createdDate DESC</code> */
    public List<Item> getItemGetItemsByEndDate(Timestamp bidEndDate) {
        return em.createNamedQuery("Item.getItemsByEndDate").setParameter("bidEndDate", bidEndDate).getResultList();
    }

    /** <code>SELECT b FROM Item b WHERE b.bidStartDate <= :bidStartDate ORDER BY  b.createdDate DESC</code> */
    public List<Item> getItemGetItemsByStartDate(Timestamp bidStartDate) {
        return em.createNamedQuery("Item.getItemsByStartDate").setParameter("bidStartDate", bidStartDate).getResultList();
    }

    /** <code>SELECT b FROM Item b WHERE b.bidStartDate BETWEEN :bidStartDate AND  :bidEndDate ORDER BY  b.createdDate DESC</code> */
    public List<Item> getItemGetItemsByDateInterval(Timestamp bidStartDate,
                                                    Timestamp bidEndDate) {
        return em.createNamedQuery("Item.getItemsByDateInterval").setParameter("bidStartDate", bidStartDate).setParameter("bidEndDate", bidEndDate).getResultList();
    }

    public ShippingInfo persistShippingInfo(ShippingInfo shippingInfo) {
        em.persist(shippingInfo);
        return shippingInfo;
    }

    public ShippingInfo mergeShippingInfo(ShippingInfo shippingInfo) {
        return em.merge(shippingInfo);
    }

    public void removeShippingInfo(ShippingInfo shippingInfo) {
        shippingInfo = em.find(ShippingInfo.class, shippingInfo.getShippingId());
        em.remove(shippingInfo);
    }

    /** <code>select o from ShippingInfo o</code> */
    public List<ShippingInfo> getShippingInfoFindAll() {
        return em.createNamedQuery("ShippingInfo.findAll").getResultList();
    }

    /** <code>select o from ShippingInfo o where o.shippingId = :shippingId </code> */
    public List<ShippingInfo> getShippingInfoFindByShippingId(Long shippingId) {
        return em.createNamedQuery("ShippingInfo.findByShippingId").setParameter("shippingId", shippingId).getResultList();
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

    /** <code>select o from User o WHERE o.userId =:userId</code> */
    public List<User> getUserFindByUserId(String userId) {
        return em.createNamedQuery("User.findByUserId").setParameter("userId", userId).getResultList();
    }

    /** <code>select o from User o WHERE o.lastName LIKE :lastName</code> */
    public List<User> getUserFindByLastName(String lastName) {
        return em.createNamedQuery("User.findByLastName").setParameter("lastName", lastName).getResultList();
    }

    /** <code>select o from User o WHERE o.userBillingId = :userBillingId</code> */
    public List<User> getUserFindByUserBillingId(Long userBillingId) {
        return em.createNamedQuery("User.findByUserBillingId").setParameter("userBillingId", userBillingId).getResultList();
    }

    /** <code>select o from User o WHERE o.birthDate <= :birthDate</code> */
    public List<User> getUserFindByBirthDate(Timestamp birthDate) {
        return em.createNamedQuery("User.findByBirthDate").setParameter("birthDate", birthDate).getResultList();
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

    /** <code>SELECT b FROM Bid b  WHERE b.bidDate <= :bidDate ORDER BY  b.bidDate DESC</code> */
    public List<Bid> getBidGetBidsByDate(Date bidDate) {
        return em.createNamedQuery("Bid.getBidsByDate").setParameter("bidDate", bidDate).getResultList();
    }

    /** <code>SELECT b FROM Bid b WHERE b.bidId = :bidId </code> */
    public List<Bid> getBidGetBidsById(Long bidId) {
        return em.createNamedQuery("Bid.getBidsById").setParameter("bidId", bidId).getResultList();
    }

    /** <code>SELECT b FROM Bid b  WHERE b.bidStatus = :bidStatus ORDER BY  b.bidDate DESC</code> */
    public List<Bid> getBidGetBidsByStatus(BidStatus bidStatus) {
        return em.createNamedQuery("Bid.getBidsByStatus").setParameter("bidStatus", bidStatus).getResultList();
    }

    /** <code>SELECT b  FROM Bid b   JOIN FETCH b.item  WHERE b.bidDate <= :bidDate ORDER BY  b.bidDate DESC</code> */
    public List<Bid> getBidGetBidsItemByDate(Date bidDate) {
        return em.createNamedQuery("Bid.getBidsItemByDate").setParameter("bidDate", bidDate).getResultList();
    }

    /** <code>SELECT b FROM Bid b    JOIN FETCH b.bidBidder   WHERE b.bidDate <= :bidDate ORDER BY  b.bidDate DESC</code> */
    public List<Bid> getBidGetBidsBidderByDate(Date bidDate) {
        return em.createNamedQuery("Bid.getBidsBidderByDate").setParameter("bidDate", bidDate).getResultList();
    }
 // @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void  editContactInfo( String userId,
                                                       String email,
                                                       String phone,
                                                       String streetName1,
                                                       String streetName2,
                                                       String city, 
                                                       String zipCode,
                                                       String stateCode,
                                                       String country) {
        
        ContactInfo ci = em.find(ContactInfo.class, userId);
        ci.setEmail(email);
        ci.setPhone(phone);
        
        Address address = ci.getAddress();
        address.setStreetName1(streetName1);
        address.setStreetName2(streetName2);
        address.setCity(city);
        address.setZipCode(zipCode);
        address.setCountry(country);
        address.setState(stateCode);
        ci.setAddress(address);
        /*Exception Description: An attempt was made to update the object [actionbazaar.persistence.ContactInfo@b6d968c], 
         * but it has no version number in the identity map. \r\nIt may not have been read before the update was attempted
         * 
         * org.eclipse.persistence.exceptions.OptimisticLockException Exception Description: The object [actionbazaar.persistence.ContactInfo@a02c216] 
         * cannot be merged because it has changed or been deleted since it was last read. Class> actionbazaar.persistence.ContactInfo*/
        
        em.merge(ci);
        em.flush();
        em.refresh(em.merge(ci));
       
        if (logger.isDebugEnabled()) {
             logger.debug("Updated ContactInfo for user id: " + ci.getContactUserId());
             logger.debug("Updated ContactInfo with address : " + ci.getAddress().getStreetName1());
        }
        return ;//ci
    }
}

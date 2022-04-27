package actionbazaar.buslogic;


import actionbazaar.persistence.Address;
import actionbazaar.persistence.Bidder;
import actionbazaar.persistence.BillingInfo;
import actionbazaar.persistence.Category;
import actionbazaar.persistence.ContactInfo;
import actionbazaar.persistence.Item;
import actionbazaar.persistence.Order;
import actionbazaar.persistence.Seller;
import actionbazaar.persistence.ShippingInfo;
import actionbazaar.persistence.User;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Remote
@Local
@DeclareRoles(value = { "BIDDER", "SELLER","ADMINISTRATOR"})
@Stateless(name = "PlaceBillingInfo", mappedName = "ejb3inaction-Model-PlaceBillingInfo")
public class PlaceBillingInfoBean extends CustomBean implements PlaceBillingInfo, PlaceBillingInfoLocal   {
   
    
    public PlaceBillingInfoBean() {
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
  
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  @RolesAllowed(value = {"BIDDER", "SELLER", "ADMINISTRATOR"})    
  public List<BillingInfo> getBillingInfoFindByBilllingId(Long billingId) {
      return em.createNamedQuery("BillingInfo.findByBillingId").setParameter("billingId",billingId).getResultList();
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

    public void removeItem(Item item) {
        item = em.find(Item.class, item.getItemId());
        em.remove(item);
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
        shippingInfo = em.find(ShippingInfo.class, shippingInfo.getShippingId());
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

    public Long addBillingInfo(      String accountNo, 
                                               String cardType,
                                               String expiryDate,
                                               String secretCode,
                                               String streetName1,
                                               String streetName2, 
                                               String zipCode,
                                               String city,
                                               String state,
                                               String country) {
      
      BillingInfo bi = new BillingInfo();
      bi.setAccountNo(accountNo);
      bi.setCardType(cardType);
      bi.setExpiryDate(expiryDate);
      bi.setSecretCode(secretCode);  
      Address address = new Address();
      address.setStreetName1(streetName1);
      address.setStreetName2(streetName2);
      address.setZipCode(zipCode);
      address.setCity(city);
      address.setState(state);
      address.setCountry(country);
      bi.setAddress(address);
      em.persist(bi);
      if (logger.isDebugEnabled())
         logger.debug("Persisted new BillingInfo with id: " + bi.getBillingId());
      return bi.getBillingId();
    }
  @RolesAllowed(value = {"BIDDER", "SELLER"})    
  public void editBillingInfo(Long BillingId,
                                      String accountNo, 
                                      String cardType,
                                      String expiryDate,
                                      String secretCode,
                                      String streetName1,
                                      String streetName2, 
                                      String zipCode,
                                      String city,
                                      String state, 
                                      String country) {
      BillingInfo  bi = em.find(BillingInfo.class,BillingId);
      bi.setAccountNo(accountNo);
      bi.setCardType(cardType);
      bi.setExpiryDate(expiryDate);
      bi.setSecretCode(secretCode);
      
      Address address = bi.getAddress();
      address.setStreetName1(streetName1);
      address.setStreetName2(streetName2);
      address.setCity(city);
      address.setCountry(country);
      address.setState(state);
      address.setZipCode(zipCode);
      bi.setAddress(address);
      em.merge(bi);
      em.persist(bi);
      if (logger.isDebugEnabled()) {
           logger.debug("Persisted edited BillingInfo with id: " + bi.getBillingId());
           logger.debug("Persisted edited BillingInfo with address : " + bi.getAddress().getStreetName1());
      }
  }


    public List<BillingInfo> getBillingInfoFindByUserId(String userId) {
            String currentUserId = context.getCallerPrincipal().getName();
            if (logger.isDebugEnabled())
              logger.debug("IS CURRENT USER " + currentUserId + "  ADMIN? " + context.isCallerInRole("ADMINISTRATOR"));
                  
            if (context.isCallerInRole("ADMINISTRATOR")) {  
                     return em.createNamedQuery("BillingInfo.findByUserId").setParameter("userId",userId).getResultList();
            } else {
                    return em.createNamedQuery("BillingInfo.findByUserId").setParameter("userId", currentUserId).getResultList();
            }
    
    }
}

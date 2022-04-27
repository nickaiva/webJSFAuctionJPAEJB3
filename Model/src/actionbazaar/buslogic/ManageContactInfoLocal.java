package actionbazaar.buslogic;


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

import javax.ejb.Local;


@Local
public interface ManageContactInfoLocal {
  /**
   * @param ci
   * @param userId
   * @param email
   * @param phone
   * @param streetname1
   * @param streetname2
   * @param city
   * @param zipCode
   * @param stateCode
   * @param country
   * @return
   */
  void editContactInfo(String userId,  String email, String phone, 
                                        String streetname1, String streetname2,
                                        String city,  String zipCode, String stateCode ,String country);

  
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    ContactInfo persistContactInfo(ContactInfo contactInfo);

    ContactInfo mergeContactInfo(ContactInfo contactInfo);

    void removeContactInfo(ContactInfo contactInfo);

    List<ContactInfo> getContactInfoFindAll();

    List<ContactInfo> getContactInfoGetContactInfoByUser(String contactUserId);

    Seller persistSeller(Seller seller);

    Seller mergeSeller(Seller seller);

    void removeSeller(Seller seller);

    List<Seller> getSellerFindAll();

    BillingInfo persistBillingInfo(BillingInfo billingInfo);

    BillingInfo mergeBillingInfo(BillingInfo billingInfo);

    void removeBillingInfo(BillingInfo billingInfo);

    List<BillingInfo> getBillingInfoFindAll();

    List<BillingInfo> getBillingInfoFindByBillingId(Long billingId);

    Bidder persistBidder(Bidder bidder);

    Bidder mergeBidder(Bidder bidder);

    void removeBidder(Bidder bidder);

    List<Bidder> getBidderFindAll();

    List<Bidder> getBidderFindBidsByBidder(String userId);

    List<Bidder> getBidderFindBidderByStatus(BidderStatus bidderStatus);

    Order persistOrder(Order order);

    Order mergeOrder(Order order);

    void removeOrder(Order order);

    List<Order> getOrderFindAll();

    List<Order> getOrderFindByOrderId(Long orderId);

    List<Order> getOrderFindByBidder(Bidder bidder);

    List<Order> getOrderFindByStatus(OrderStatus orderStatus);

    List<Order> getOrderFindByBillingInfo(BillingInfo billingInfo);

    List<Order> getOrderFindByShippingInfo(ShippingInfo shippingInfo);

    Category persistCategory(Category category);

    Category mergeCategory(Category category);

    void removeCategory(Category category);

    List<Category> getCategoryFindAll();

    List<Category> getCategoryFindByCategoryId(Long categoryId);

    List<Category> getCategoryFindByCreatedBy(String createdBy);

    Item persistItem(Item item);

    Item mergeItem(Item item);

    void removeItem(Item item);

    List<Item> getItemFindAll();

    List<Item> getItemGetItemsBySeller(Seller seller);

    List<Item> getItemGetItemsByName(String itemName);

    List<Item> getItemGetItemsByEndDate(Timestamp bidEndDate);

    List<Item> getItemGetItemsByStartDate(Timestamp bidStartDate);

    List<Item> getItemGetItemsByDateInterval(Timestamp bidStartDate,
                                             Timestamp bidEndDate);

    ShippingInfo persistShippingInfo(ShippingInfo shippingInfo);

    ShippingInfo mergeShippingInfo(ShippingInfo shippingInfo);

    void removeShippingInfo(ShippingInfo shippingInfo);

    List<ShippingInfo> getShippingInfoFindAll();

    List<ShippingInfo> getShippingInfoFindByShippingId(Long shippingId);

    User persistUser(User user);

    User mergeUser(User user);

    void removeUser(User user);

    List<User> getUserFindAll();

    List<User> getUserFindByUserId(String userId);

    List<User> getUserFindByLastName(String lastName);

    List<User> getUserFindByUserBillingId(Long userBillingId);

    List<User> getUserFindByBirthDate(Timestamp birthDate);

    Bid persistBid(Bid bid);

    Bid mergeBid(Bid bid);

    void removeBid(Bid bid);

    List<Bid> getBidFindAll();

    List<Bid> getBidGetBidsByDate(Date bidDate);

    List<Bid> getBidGetBidsById(Long bidId);

    List<Bid> getBidGetBidsByStatus(BidStatus bidStatus);

    List<Bid> getBidGetBidsItemByDate(Date bidDate);

    List<Bid> getBidGetBidsBidderByDate(Date bidDate);
}

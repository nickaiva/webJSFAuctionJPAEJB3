package actionbazaar.buslogic;


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

import javax.ejb.Local;


@Local
public interface PlaceBillingInfoLocal {
    
  Long addBillingInfo(String accountNo, String cardType,String expiryDate, String secretCode,
                              String streetName1,String streetName2, String zipCode,String city, String state, String country);
  
  public void editBillingInfo(Long BillingId, String accountNo, String cardType,String expiryDate, String secretCode,
                                                String streetName1,String streetName2, String zipCode,String city, String state, String country);
  
  public List<BillingInfo> getBillingInfoFindByBilllingId(Long BillingId); 
  List<BillingInfo> getBillingInfoFindByUserId(String userId); 
  public List<BillingInfo> getBillingInfoFindAll();

    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    ContactInfo persistContactInfo(ContactInfo contactInfo);

    ContactInfo mergeContactInfo(ContactInfo contactInfo);

    void removeContactInfo(ContactInfo contactInfo);

    List<ContactInfo> getContactInfoFindAll();

    Seller persistSeller(Seller seller);

    Seller mergeSeller(Seller seller);

    void removeSeller(Seller seller);

    List<Seller> getSellerFindAll();

    BillingInfo persistBillingInfo(BillingInfo billingInfo);

    BillingInfo mergeBillingInfo(BillingInfo billingInfo);

    void removeBillingInfo(BillingInfo billingInfo);

    Bidder persistBidder(Bidder bidder);

    Bidder mergeBidder(Bidder bidder);

    void removeBidder(Bidder bidder);

    List<Bidder> getBidderFindAll();

    Order persistOrder(Order order);

    Order mergeOrder(Order order);

    void removeOrder(Order order);

    List<Order> getOrderFindAll();

    Category persistCategory(Category category);

    Category mergeCategory(Category category);

    void removeCategory(Category category);

    List<Category> getCategoryFindAll();

    Item persistItem(Item item);

    Item mergeItem(Item item);

    void removeItem(Item item);

    List<Item> getItemFindAll();

    ShippingInfo persistShippingInfo(ShippingInfo shippingInfo);

    ShippingInfo mergeShippingInfo(ShippingInfo shippingInfo);

    void removeShippingInfo(ShippingInfo shippingInfo);

    List<ShippingInfo> getShippingInfoFindAll();

    User persistUser(User user);

    User mergeUser(User user);

    void removeUser(User user);

    List<User> getUserFindAll();
    
}

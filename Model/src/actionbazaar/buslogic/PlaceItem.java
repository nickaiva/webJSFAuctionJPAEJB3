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

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface PlaceItem {

    public void deleteItem(Long itemId);

    public Item updateItem(Long itemId, String itemName, Double initialPrice,
                           String description, byte[] image);

    public Long placeItem(String itemName, String SellerId,
                          Double initialPrice, String description,
                          byte[] image);

    public Item updateItem(Item item);

    public Item undoItemChanges(Item item);

    public List<Item> getItemFindAll();

    byte[] getImageByItemId(Long itemId);

   

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

    List<BillingInfo> getBillingInfoFindAll();

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

    ShippingInfo persistShippingInfo(ShippingInfo shippingInfo);

    ShippingInfo mergeShippingInfo(ShippingInfo shippingInfo);

    void removeShippingInfo(ShippingInfo shippingInfo);

    List<ShippingInfo> getShippingInfoFindAll();

    User persistUser(User user);

    User mergeUser(User user);

    void removeUser(User user);

    List<User> getUserFindAll();

    Bid persistBid(Bid bid);

    Bid mergeBid(Bid bid);

    void removeBid(Bid bid);

    List<Bid> getBidFindAll();

    List<Bid> getBidsByDate(Date bidDate);

    List<Item> getItemGetItemsBySellerId(String sellerId);

    Item getItemGetItemById(Long itemId);


    List<Item> getItemGetItemsByName(String itemName, String userId);

    List<Item> getItemGetItemsByEndDate(Timestamp bidEndDate);

    List<Item> getItemGetItemsByStartDate(Timestamp bidStartDate);

    List<Item> getItemGetItemsByDateInterval(Timestamp bidStartDate,
                                             Timestamp bidEndDate);

    List<Item> getItemFindRemovedFromFurtherBiddingItems();

    List<Item> getItemFindWonItems(String userId);

    List<Item> getItemFindAvailableItems();

}

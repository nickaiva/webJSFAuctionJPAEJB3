package actionbazaar.buslogic.client;


import actionbazaar.buslogic.PlaceItem;

import actionbazaar.persistence.Address;
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

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;


public class PlaceItemClient {
  private static Logger logger = Logger.getLogger("PlaceItemClient");
  
    public static void main(String [] args) {
        try {
            final Context context =getInitialContext(args[0],args[1]);
            PlaceItem placeItem = (PlaceItem)context.lookup("ejb3inaction-Model-PlaceItem#actionbazaar.buslogic.PlaceItem");
            /*CREATE DATABASE SEQUENCE START WITH 100
             * AND  ADD TO KEY @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BID_SEQUENCE")*/
           
            long newitemId = placeItem.placeItem( "Ironing machine","KatieUttleySeller",  1d, "  String description" ,null); //args[0]
            if (logger.isDebugEnabled())
                 logger.debug(" Successful, seq number received is:" + newitemId);
            //newitem.setItemName("Washing & draining machine");
            //placeItem.updateItem(newitem);
           // placeItem.undoItemChanges(newitem);
/*        for (ContactInfo contactinfo : (List<ContactInfo>)placeItem.getContactInfoFindAll()) {
                printContactInfo(contactinfo);
            }
*/
           /*  for (Seller seller : (List<Seller>)placeItem.getSellerFindAll()) {
                printSeller(seller);
            }
           for (BillingInfo billinginfo : (List<BillingInfo>)placeItem.getBillingInfoFindAll()) {
                printBillingInfo(billinginfo);
            }
            for (Bidder bidder : (List<Bidder>)placeItem.getBidderFindAll()) {
                printBidder(bidder);
            }
            for (Order order : (List<Order>)placeItem.getOrderFindAll()) {
                printOrder(order);
            }
            for (Category category : (List<Category>)placeItem.getCategoryFindAll()) {
                printCategory(category);
            }
         */  
            for (Item item : (List<Item>)placeItem.getItemFindAll()) {
                printItem(item);
            }
           
         /*
          * for (Item item : (List<Item>)placeItem.getItemFindRemovedFromFurtherBiddingItems()) {
              printItem(item);
          }
*/
   /*       for (Item item : (List<Item>)placeItem.getItemFindWonItems("%")) { // or args[0] for non admin users
              printItem(item);
          }
   */ 
            /*for (ShippingInfo shippinginfo : (List<ShippingInfo>)placeItem.getShippingInfoFindAll()) {
                printShippingInfo(shippinginfo);
            }
            for (User user : (List<User>)placeItem.getUserFindAll()) {
                printUser(user);
            }
            for (Bid bid : (List<Bid>)placeItem.getBidFindAll()) {
                printBid(bid);
            }
          java.util.Date today =   new java.util.Date();
          java.sql.Date bidDate =   new java.sql.Date(today.getTime());
            for (Bid bid : (List<Bid>)placeItem.BidsByDate(bidDate )) {
                printBid(bid);
            }*/
           // placeItem.removeItem(newitem);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printContactInfo(ContactInfo contactinfo) {
        System.out.println( "contactUserId = " + contactinfo.getContactUserId() );
        System.out.println( "email = " + contactinfo.getEmail() );
        System.out.println( "phone = " + contactinfo.getPhone() );
        System.out.println( "address = " + contactinfo.getAddress() );
    }

    private static void printSeller(Seller seller) {
        System.out.println( "commissionRate = " + seller.getCommissionRate() );
        System.out.println( "maxItemsAllowed = " + seller.getMaxItemsAllowed() );
        System.out.println( "items = " + seller.getItems() );
        System.out.println( "birthDate = " + seller.getBirthDate() );
        System.out.println( "firstName = " + seller.getFirstName() );
        System.out.println( "lastName = " + seller.getLastName() );
        System.out.println( "userBillingId = " + seller.getUserBillingId() );
        System.out.println( "userId = " + seller.getUserId() );
        System.out.println( "userType = " + seller.getUserType() );
    }

    private static void printBillingInfo(BillingInfo billinginfo) {
        System.out.println( "accountNo = " + billinginfo.getAccountNo() );
        System.out.println( "billingId = " + billinginfo.getBillingId() );
        System.out.println( "cardType = " + billinginfo.getCardType() );
        System.out.println( "expiryDate = " + billinginfo.getExpiryDate() );
        System.out.println( "secretCode = " + billinginfo.getSecretCode() );
        System.out.println( "orderSet = " + billinginfo.getOrderSet() );
    }

    private static void printBidder(Bidder bidder) {
        System.out.println( "bidderStatus = " + bidder.getBidderStatus() );
        System.out.println( "creditRating = " + bidder.getCreditRating() );
        System.out.println( "bids = " + bidder.getBids() );
        System.out.println( "birthDate = " + bidder.getBirthDate() );
        System.out.println( "firstName = " + bidder.getFirstName() );
        System.out.println( "lastName = " + bidder.getLastName() );
        System.out.println( "userBillingId = " + bidder.getUserBillingId() );
        System.out.println( "userId = " + bidder.getUserId() );
        System.out.println( "userType = " + bidder.getUserType() );
    }

    private static void printOrder(Order order) {
        System.out.println( "orderId = " + order.getOrderId() );
        System.out.println( "orderStatus = " + order.getOrderStatus() );
        System.out.println( "billingInfo = " + order.getBillingInfo() );
        System.out.println( "shippingInfo = " + order.getShippingInfo() );
        System.out.println( "bidder = " + order.getBidder() );
        System.out.println( "items = " + order.getItems() );
    }

    private static void printCategory(Category category) {
        System.out.println( "categoryId = " + category.getCategoryId() );
        System.out.println( "categoryName = " + category.getCategoryName() );
        System.out.println( "createdBy = " + category.getCreatedBy() );
        System.out.println( "createDate = " + category.getCreateDate() );
        System.out.println( "parentId = " + category.getParentId() );
    }

    private static void printItem(Item item) {
        System.out.println( "bidEndDate = " + item.getBidEndDate() );
        System.out.println( "bidStartDate = " + item.getBidStartDate() );
        System.out.println( "createdDate = " + item.getCreatedDate() );
        System.out.println( "initialPrice = " + item.getInitialPrice() );
        System.out.println( "itemId = " + item.getItemId() );
        System.out.println( "itemName = " + item.getItemName() );
        //System.out.println( "optLock = " + item.getOptLock() );
        System.out.println( "bidSet = " + item.getBidSet() );
        System.out.println( "seller = " + item.getSeller() );
        System.out.println( "order = " + item.getOrder() );
    }

    private static void printShippingInfo(ShippingInfo shippinginfo) {
      
        Address address = new Address();
        System.out.println( "city = " + address.getCity() );
        System.out.println( "shippingId = " + shippinginfo.getShippingId() );
        System.out.println( "state = " + address.getState() );
        System.out.println( "street = " + address.getStreetName1() );
        System.out.println( "orderSet = " + shippinginfo.getOrderSet() );
    }

    private static void printUser(User user) {
        System.out.println( "birthDate = " + user.getBirthDate() );
        System.out.println( "firstName = " + user.getFirstName() );
        System.out.println( "lastName = " + user.getLastName() );
        System.out.println( "userBillingId = " + user.getUserBillingId() );
        System.out.println( "userId = " + user.getUserId() );
        System.out.println( "userType = " + user.getUserType() );
    }

    private static void printBid(Bid bid) {
        System.out.println( "bidBidder = " + bid.getBidBidder() );
        System.out.println( "bidDate = " + bid.getBidDate() );
        System.out.println( "bidId = " + bid.getBidId() );
        System.out.println( "bidPrice = " + bid.getBidPrice() );
        System.out.println( "bidStatus = " + bid.getBidStatus() );
        System.out.println( "item = " + bid.getItem() );
    }

    private static Context getInitialContext(String user, String password) throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
       /*standalone server*/
       // env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7001");
       env.put(Context.SECURITY_PRINCIPAL, user);
       env.put(Context.SECURITY_CREDENTIALS, password);
       /*integrated server only*/
       env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
       return new InitialContext( env );
    }
}

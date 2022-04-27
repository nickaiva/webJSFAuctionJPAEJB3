package actionbazaar.buslogic.client;


import actionbazaar.buslogic.ManageContactInfo;

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


/*You may need to delete -javaagent:../modules/org.eclipse.persistence_1.0.0.0_2-0.jar
 * from the edit run configuration java options within jdeveloper 11g  Manage Run configuration*/
public class ManageContactInfoClient {
    
    private static Logger logger = Logger.getLogger("ManageContactInfoClient");
    
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext(args[0], args[1]);
            ManageContactInfo manageContactInfo = (ManageContactInfo)context.lookup("ejb3inaction-Model-ManageContactInfo#actionbazaar.buslogic.ManageContactInfo");
          
          /*    java.util.Date today =  new java.util.Date();
              java.sql.Date currDate =   new java.sql.Date(today.getTime());
              java.sql.Timestamp  currentDateTimestamp =   new java.sql.Timestamp(today.getTime());//OR 1977-01-13 12:29:43.0
              BillingInfo bi = new BillingInfo();
              bi.setBillingId(70L);
              ShippingInfo si = new ShippingInfo();
              si.setShippingId(54L);
              Bidder bidder1 = new Bidder();
              bidder1.setUserId("Lila");
              Seller seller1 = new Seller();
              seller1.setUserId("viper");
          */
          
        /*
           for (ContactInfo contactinfo : (List<ContactInfo>)manageContactInfo.getContactInfoFindAll()) {
                printContactInfo(contactinfo);
            }
        */
            for (ContactInfo contactinfo : (List<ContactInfo>)manageContactInfo.getContactInfoGetContactInfoByUser("NickAivaBidder" )) {
                printContactInfo(contactinfo);
              
                if (logger.isDebugEnabled())
                  logger.debug("Updated data follow: ");
              manageContactInfo.editContactInfo( "NickAivaBidder", "nickaiva@yahoo.co.uk", "2112111003", "Davaki 1", 
                                                     "Cyprus 15","Kalithea", "11125", "Attica", "Greece");
                /*manageContactInfo.editContactInfo( "NickAivaBidder", "nickaiva@yahoo.co.uk", "2112111003", "Davaki 1", 
                                                           "Cyprus 15","Athen", "11125", "Attica", "Greece")
              printContactInfo( contactinfo  ); 
            */
                  
            }
          
                    
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printContactInfo(ContactInfo contactinfo) {
        System.out.println( "contactUserId = " + contactinfo.getContactUserId() );
        System.out.println( "email = " + contactinfo.getEmail() );
        System.out.println( "phone = " + contactinfo.getPhone() );
        System.out.println( "address = " + contactinfo.getAddress().getStreetName1() +
                            " " +  contactinfo.getAddress().getStreetName2() +
                            " " + contactinfo.getAddress().getCity() +
                            " " + contactinfo.getAddress().getZipCode() +
                            " " + contactinfo.getAddress().getCountry() + 
                            " " + contactinfo.getAddress().getState());
        
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
        System.out.println( "address = " + billinginfo.getAddress() );
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
       // System.out.println( "optLock = " + item.getOptLock() );
        System.out.println( "bidSet = " + item.getBidSet() );
        System.out.println( "seller = " + item.getSeller() );
        System.out.println( "order = " + item.getOrder() );
    }

    private static void printShippingInfo(ShippingInfo shippinginfo) {
        Address address = shippinginfo.getAddress();
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
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        env.put(Context.SECURITY_PRINCIPAL, user); //"NickAivaAdmin"
        env.put(Context.SECURITY_CREDENTIALS,password ); //"NickAivaAdmin1"
        return new InitialContext( env );
    }
}

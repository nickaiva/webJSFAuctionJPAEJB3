package actionbazaar.buslogic.clients;
/*alter table "USERS" disable  constraint "FK_USERS_USER_ID"*/
import actionbazaar.buslogic.BazaarAdmin;

import actionbazaar.persistence.Address;
import actionbazaar.persistence.Bidder;

import actionbazaar.persistence.BillingInfo;

import actionbazaar.persistence.Category;
import actionbazaar.persistence.ContactInfo;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BazaarAdminClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            BazaarAdmin bazaarAdmin = (BazaarAdmin)context.lookup("ejb3inaction-Chapter8-BazaarAdmin#actionbazaar.buslogic.BazaarAdmin");
            Address address = new Address();
            address.setStreetName1("Smart2streetName1");
        
                  Bidder bidder = new Bidder();
                  bidder.setUserId("sheilo");
                  bidder.setFirstName("Serious");
                  bidder.setLastName("Bidder");
                  bidder.setCreditRating(new Long(65));
          ContactInfo contactInfo = new ContactInfo(bidder.getUserId(),"smart@idiots.com","",address);
                  bidder.setContactInfo(contactInfo);
                  bidder = bazaarAdmin.createBidder(bidder);
                  System.out.println("Bidder :" + bidder.getUserId() + " BidderStatus:"
                          + bidder.getBidderStatus());
            //Category category = new Category(new Long(1),"Utensils",null,bidder,null);
          bazaarAdmin.createCategory("Utensils", bidder.getUserId());  
          //The following throws NPE
          bazaarAdmin.createItem("fork", bidder.getUserId(), new Long(5), new Double(9)) ; 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}

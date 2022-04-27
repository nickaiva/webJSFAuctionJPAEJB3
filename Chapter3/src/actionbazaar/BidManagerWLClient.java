package actionbazaar;

import actionbazaar.buslogic.BidManager;

import actionbazaar.persistence.Bid;
import actionbazaar.persistence.Bidder;
import actionbazaar.persistence.Item;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BidManagerWLClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            BidManager bidManager = (BidManager)context.lookup("ejb3inaction-Chapter3-BidManager#actionbazaar.buslogic.BidManager");
          Item item = new Item();
                     item.setItemId(new Long(2));

                     Bidder bidder = new Bidder();
                     bidder.setUserId("viper");

                     Bid bid = new Bid();
                     bid.setBidder(bidder);
                     bid.setItem(item);
                     bid.setBidPrice(10000.50);
                     System.out.println("Bid Successful, BidId Received is:"
                             + bidManager.addBid(bid));
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

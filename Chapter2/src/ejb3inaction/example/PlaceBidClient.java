package ejb3inaction.example;

import ejb3inaction.example.buslogic.PlaceBid;

import ejb3inaction.example.persistence.Bid;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PlaceBidClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            PlaceBid placeBid = (PlaceBid)context.lookup("ejb3inaction-Chapter2-PlaceBid#ejb3inaction.example.buslogic.PlaceBid");
            Bid bid = new Bid();
           bid.setBidBidder("npanda");
           bid.setBidItemId(Long.valueOf(100));
           bid.setBidPrice(20000.40);
            
          System.out.println("Bid Successful, BidId Received is:" +placeBid.addBid(bid));
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

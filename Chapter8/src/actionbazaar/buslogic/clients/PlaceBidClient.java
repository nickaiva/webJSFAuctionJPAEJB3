package actionbazaar.buslogic.clients;

import actionbazaar.buslogic.PlaceBid;

import actionbazaar.persistence.Bid;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PlaceBidClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            PlaceBid placeBid = (PlaceBid)context.lookup("ejb3inaction-Chapter8-PlaceBid#actionbazaar.buslogic.PlaceBid");
            Bid bid = placeBid.addBid("idiot", Long.valueOf(100), 2000.50);
            System.out.println("Bid Successful, BidId Received is:"
                             + bid.getBidId());

        
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

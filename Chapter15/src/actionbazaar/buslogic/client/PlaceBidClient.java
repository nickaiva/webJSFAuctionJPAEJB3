package actionbazaar.buslogic.client;

import actionbazaar.buslogic.PlaceBid;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PlaceBidClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            PlaceBid placeBid = (PlaceBid)context.lookup("ejb3inaction-Chapter15-PlaceBid#actionbazaar.buslogic.PlaceBid");
            //placeBid.addBid("idiot", new Long(1), 20000.0);
            System.out.println("Bid Price Sent to the bean is:"+200000.5);
            System.out.println("Bid Successful, BidId Received is:" +placeBid.addBid("idiot",  Long.valueOf(1),  2000005.50 ));
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

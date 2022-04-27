package actionbazaar.buslogic;

import java.util.Hashtable;

import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PlaceBidClient {
  
    public static void main(String [] args) {
      Logger logger= Logger.getAnonymousLogger();      
        try {
            final Context context = getInitialContext();
            PlaceBid placeBid = (PlaceBid)context.lookup("ejb3inaction-Chapter5-PlaceBid#actionbazaar.buslogic.PlaceBid");
            logger.info("Bid Price Sent to the bean is:" + 20000.5);
            logger.info("Bid Successful, BidId Received is:"
                             + placeBid.addBid("nickaiva", Long.valueOf(1), 20000.50));
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

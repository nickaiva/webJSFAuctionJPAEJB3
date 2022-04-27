package actionbazaar.client;

import actionbazaar.buslogic.PlaceBid;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PlaceBidClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            PlaceBid placeBid = (PlaceBid)context.lookup("ejb3inaction-Chapter16-PlaceBid#actionbazaar.buslogic.PlaceBid");
          Long itemId=Long.valueOf(2);
          String userId = "idiot";
          Double bidPrice = new Double(2000.09);
          System.out.println("itemId:" + itemId);
          System.out.println("userId:" + userId);
          System.out.println("bidPrice:" + bidPrice);

          Long bidId = placeBid.addBid(userId, itemId, bidPrice);  
          System.out.println("Bid Persisted successfully .."+bidId);
          
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

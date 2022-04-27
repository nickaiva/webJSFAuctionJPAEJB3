package actionbazaar.buslogic.client;

import actionbazaar.buslogic.PlaceBid;

import actionbazaar.persistence.Bid;

import java.util.Date;
import java.util.Hashtable;

import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PlaceBidClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            PlaceBid placeBid = (PlaceBid)context.lookup("ejb3inaction-Chapter11-PlaceBid#actionbazaar.buslogic.PlaceBid");
            Bid bid = placeBid.addBid("idiot2",  Long.valueOf(100),  2001.50);
            System.out.println("Bid Successful, BidId Received is:" +bid.getBidId());
            System.out.println("Finding Items by date..");
                     Date currDate = new Date();
                     List bids = placeBid.getBidsByDate(currDate);
                     Iterator  k = bids.iterator();
                     while (k.hasNext())
                     {
                        bid = (Bid) k.next();
                       System.out.println("Id:"+bid.getBidId()+" Bidder userId: "+bid.getBidder().getUserId());
                     }
          
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

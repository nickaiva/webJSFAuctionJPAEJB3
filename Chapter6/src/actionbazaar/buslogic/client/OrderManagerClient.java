package actionbazaar.buslogic.client;

import actionbazaar.buslogic.OrderManager;

import actionbazaar.persistance.Bidder;
import actionbazaar.persistance.Item;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class OrderManagerClient {

    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            OrderManager orderManager = (OrderManager)context.lookup("ejb3inaction-Chapter6-OrderManager#actionbazaar.buslogic.OrderManager");
            Item item= new Item();
            item.setItemId(new Long(3));
            //item.setItemName("Snaged product");
            item.setInitialPrice(new Double(2000));
            Bidder bidder = new Bidder();
            bidder.setCreditCardType("MASTERCARD");
            bidder.setUsername("snagger");
            
            orderManager.placeSnagItOrder(item, bidder);
            System.out.println("client called placeSnagItOrder!");
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

package actionbazaar.client;

import actionbazaar.buslogic.PlaceOrder;

import actionbazaar.persistence.BillingInfo;
import actionbazaar.persistence.ShippingInfo;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PlaceOrderClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            PlaceOrder placeOrder = (PlaceOrder)context.lookup("ejb3inaction-Chapter13-PlaceOrder#actionbazaar.buslogic.PlaceOrder");
            System.out.println("Exercising PlaceOrder EJB...");
            placeOrder.setBidder("idiot");
            placeOrder.addItem(new Long(1));
            placeOrder.addItem(new Long(2));
            placeOrder.setShippingInfo(new ShippingInfo("123 My Sweet Home","MyCity","MyState"));
            placeOrder.setBillingInfo(new BillingInfo("123456789","VISA","0708"));
            Long orderId = placeOrder.confirmOrder();
            System.out.println("Order confirmation number: " + orderId);

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

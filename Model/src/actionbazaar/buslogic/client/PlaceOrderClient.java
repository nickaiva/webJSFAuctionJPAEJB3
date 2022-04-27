package actionbazaar.buslogic.client;


import actionbazaar.buslogic.PlaceOrder;

import actionbazaar.persistence.Order;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;


public class PlaceOrderClient {
  static Logger logger =Logger.getLogger("PlaceOrderClient");
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext(args[0],args[1]);
            PlaceOrder placeOrder = (PlaceOrder)context.lookup("ejb3inaction-Model-PlaceOrder#actionbazaar.buslogic.PlaceOrder");
           
            String bidderId ="KatieUttleyasBidder";
            Long shippingId = new Long(54);
            Long billingId= new Long(24);
            //Long itemId = new Long(1);
            placeOrder.setBidder(bidderId);
            placeOrder.setBillingInfo(billingId);
            placeOrder.setShippingInfo(shippingId);
            //placeOrder.addItem(itemId);
            
            ArrayList<Long> itemsId = new ArrayList<Long>();
            //itemsId.add(new Long(152));
            itemsId.add(new Long(1));
            
            Long newOrderId = placeOrder.addOrder(bidderId,itemsId,shippingId,billingId);
            if (logger.isDebugEnabled())
              logger.debug("OrderId sequence number: " + newOrderId);
         
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
  private static Context getInitialContext(String user, String password) throws NamingException {
      Hashtable env = new Hashtable();
      // WebLogic Server 10.x connection details
      env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
     /*standalone server*/
     // env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7001");
     env.put(Context.SECURITY_PRINCIPAL, user);
     env.put(Context.SECURITY_CREDENTIALS, password);
     /*integrated server only*/
     env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
     return new InitialContext( env );
  }
   
}

package actionbazaar.buslogic.client;


import actionbazaar.buslogic.PlaceOrder;

import actionbazaar.persistence.Address;
import actionbazaar.persistence.BillingInfo;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;


public class PlaceOrderOldClient {
    static Logger logger =Logger.getLogger("PlaceOrderOldClient");
    
    public static void main(String [] args) {
        try {
                final Context context = getInitialContext(args[0],args[1]);
                PlaceOrder placeOrder = (PlaceOrder)context.lookup("ejb3inaction-Model-PlaceOrder#actionbazaar.buslogic.PlaceOrder");
                placeOrder.setBidder("Lila");
                placeOrder.addItem(new Long(351));
                placeOrder.addItem(new Long(551));
                placeOrder.setShippingInfo(1L);
                Address address = new Address();
                address.setStreetName1("123 My Sweet Home");
                address.setStreetName2("");
                address.setZipCode("12458");
                address.setCity("MyCity");
                address.setState("MyState");
                address.setCountry("MyCountry");
                placeOrder.setBillingInfo(new BillingInfo("123456789","VISA","0708","secre",address));
                Long orderId = placeOrder.confirmOrder();
                if (logger.isDebugEnabled())
                    logger.debug("Order confirmation number: " + orderId);

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

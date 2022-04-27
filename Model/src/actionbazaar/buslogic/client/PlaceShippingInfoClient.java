package actionbazaar.buslogic.client;


import actionbazaar.buslogic.PlaceShippingInfo;

import actionbazaar.persistence.Address;
import actionbazaar.persistence.ShippingInfo;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;


public class PlaceShippingInfoClient {
    static Logger logger = Logger.getLogger("PlaceShippingInfoClient");
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext(args[0],args[1]);
            PlaceShippingInfo placeShippingInfo = (PlaceShippingInfo)context.lookup("ejb3inaction-Model-PlaceShippingInfo#actionbazaar.buslogic.PlaceShippingInfo");
            /*Long siId = placeShippingInfo.addShippingInfo("Anam 18 st.", "1 Peace Ave.", "15824","Athens","Attica","Greece",null);
            if (logger.isDebugEnabled())
                logger.debug("Querying ShippingInfo with shippingId: " + siId);
            for (ShippingInfo shippinginfo : (List<ShippingInfo>)placeShippingInfo.getShippingInfoFindAll()) {
                printShippingInfo(shippinginfo);
            }
*/
            for (ShippingInfo shippinginfo : (List<ShippingInfo>)placeShippingInfo.getShippingInfofindByShippingAddressStreetName1("B%")) {
                printShippingInfo(shippinginfo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printShippingInfo(ShippingInfo shippinginfo) {
        Address address =shippinginfo.getAddress();
        System.out.println( "city = " + address.getCity() );
        System.out.println( "shippingId = " + shippinginfo.getShippingId() );
        System.out.println( "state = " + address.getState() );
        System.out.println( "street1 = " + address.getStreetName1() );
        System.out.println( "street2  = " + address.getStreetName2() );
        System.out.println( "orderSet = " + shippinginfo.getOrderSet() );
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

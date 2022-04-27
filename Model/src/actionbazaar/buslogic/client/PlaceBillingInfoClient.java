package actionbazaar.buslogic.client;


import actionbazaar.buslogic.PlaceBillingInfo;

import actionbazaar.persistence.BillingInfo;
import actionbazaar.persistence.Order;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;


/*Remove -javaagent:../modules/org.eclipse.persistence_1.0.0.0_2-0.jar  from java options if necessary*/
public class PlaceBillingInfoClient {
    
  private static Logger logger = Logger.getLogger("PlaceBillingInfoClient");
  
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext("NickAivaBidder","NickAivaBidder1" );//args[0] args[1]
            PlaceBillingInfo placeBillingInfo = (PlaceBillingInfo)context.lookup("ejb3inaction-Model-PlaceBillingInfo#actionbazaar.buslogic.PlaceBillingInfo");
         
            Long bi = placeBillingInfo.addBillingInfo("4305892106915006", "VISA", "0213", "594","Galini 1", "Anam 18", "12589","Athens","Attica","Greece");
            if (logger.isDebugEnabled())
                logger.debug("querying for new billing InfoId " + bi);
            /*     
            for (BillingInfo billinginfo : (List<BillingInfo>)placeBillingInfo.getBillingInfoFindAll()) {
                printBillingInfo(billinginfo);
            }
           */
          for (BillingInfo billinginfo : (List<BillingInfo>)placeBillingInfo.getBillingInfoFindByUserId("NickAivaBidder")) {
              printBillingInfo(billinginfo);
          }
     /*
          placeBillingInfo.editBillingInfo( bi.getBillingId(),"987654321", "Maestro", "1113", "secre","Galinis 1", "Anam 18", "15896","Athens","Attica","Greece");
           if (logger.isDebugEnabled())
                    logger.debug("querying for edit billing InfoId " + bi.getBillingId());
    */  
          for (BillingInfo billinginfo : (List<BillingInfo>)placeBillingInfo.getBillingInfoFindByBilllingId(bi)) {
              printBillingInfo(billinginfo);
          }
            for (Order order : (List<Order>)placeBillingInfo.getOrderFindAll()) {
                printOrder(order);
            }
        
         
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }



    private static void printBillingInfo(BillingInfo billinginfo) {
        System.out.println( "accountNo = " + billinginfo.getAccountNo() );
        System.out.println( "billingId = " + billinginfo.getBillingId() );
        System.out.println( "cardType = " + billinginfo.getCardType() );
        System.out.println( "expiryDate = " + billinginfo.getExpiryDate() );
        System.out.println( "secretCode = " + billinginfo.getSecretCode() );
        //System.out.println( "orderSet = " + billinginfo.getOrderSet() );
        System.out.println("address = " + billinginfo.getAddress().getStreetName1());
        System.out.println("address = " + billinginfo.getAddress().getStreetName2());
        System.out.println("zip = " + billinginfo.getAddress().getZipCode());
        System.out.println("city = " + billinginfo.getAddress().getCity());
        System.out.println("state = " + billinginfo.getAddress().getState());
        System.out.println("country = " + billinginfo.getAddress().getCountry());
    }


    private static void printOrder(Order order) {
        System.out.println( "orderId = " + order.getOrderId() );
        System.out.println( "orderStatus = " + order.getOrderStatus() );
        System.out.println( "billingInfo = " + order.getBillingInfo() );
        System.out.println( "shippingInfo = " + order.getShippingInfo() );
        System.out.println( "bidder = " + order.getBidder() );
        System.out.println( "items = " + order.getItems() );
        
    }

    private static Context getInitialContext(String user, String password) throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        env.put(Context.SECURITY_PRINCIPAL, user); //"NickAivaAdmin"
        env.put(Context.SECURITY_CREDENTIALS,password ); //"NickAivaAdmin1"
        return new InitialContext( env );
    }
}

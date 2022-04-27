package ejb3inaction.example;

import ejb3inaction.example.buslogic.PlaceOrder;

import ejb3inaction.example.persistence.Bid;

import ejb3inaction.example.persistence.BillingInfo;
import ejb3inaction.example.persistence.ShippingInfo;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PlaceOrderClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            PlaceOrder placeOrder = (PlaceOrder)context.lookup("PlaceOrder#ejb3inaction.example.buslogic.PlaceOrder");
            for (Bid bid : (List<Bid>)placeOrder.getBidFindAll()) {
                printBid(bid);
            }
          System.out.println("Exercising PlaceOrder EJB...");
                  placeOrder.setBidderId(new Long(100));
                  placeOrder.addItem(new Long(2));
                  placeOrder.addItem(new Long(201));
                  placeOrder.setShippingInfo(new ShippingInfo("123 My Sweet Home",(long)1,"MyCity","MyState"));
                  placeOrder.setBillingInfo(new BillingInfo("123456789",(long)1,"YourCity","0708","VISA","country","secretCode","Address","Address","14122"));
                  Long orderId = placeOrder.confirmOrder();
                  System.out.println("Order confirmation number: " + orderId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printBid(Bid bid) {
        System.out.println( "bidBidder = " + bid.getBidBidder() );
        System.out.println( "bidDate = " + bid.getBidDate() );
        System.out.println( "bidId = " + bid.getBidId() );
        System.out.println( "bidItemId = " + bid.getBidItemId() );
        System.out.println( "bidPrice = " + bid.getBidPrice() );
        System.out.println( "bidStatus = " + bid.getBidStatus() );
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}

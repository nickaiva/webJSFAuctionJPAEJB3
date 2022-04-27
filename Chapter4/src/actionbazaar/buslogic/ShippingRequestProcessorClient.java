package actionbazaar.buslogic;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class ShippingRequestProcessorClient {
    
    public static void main(String [] args) throws JMSException, NamingException {
        
        long item = 10102;
        String address = "101 In Heaven ";
        String method = "turtleMail";
        double amount = 101.00; 
                                        
        final Context ic = getInitialContext();
        final QueueConnectionFactory qcf = (QueueConnectionFactory)ic.lookup("jms.ShippingRequestCF");	
            // jms.ConnectionFactory
        // Lookup should specify the queue name that is mentioned as "mappedName" in MessageDriven Bean.
        final Queue destQueue = (Queue)ic.lookup("jms/ShippingRequestQueue");
        ic.close();
        final QueueConnection connection = qcf.createQueueConnection();
        try {
            final QueueSession session = connection.createQueueSession(false, 0);
            final QueueSender sender = session.createSender(destQueue);
            /*
	final TextMessage msg = session.createTextMessage("Hello World from Message Driven Bean");
            	sender.send(msg);
           */
           ShippingRequest shippingRequest = new ShippingRequest();
           shippingRequest.setItem(item);
           shippingRequest.setShippingAddress(address);
           shippingRequest.setShippingMethod(method);
           shippingRequest.setInsuranceAmount(amount);
          ObjectMessage message = session.createObjectMessage(shippingRequest);
          message.setObject(shippingRequest);
          sender.send(message);
          System.out.println("Shipping Request Message Sent ..");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            connection.close();
        }
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // Add InitialContext property assignments here.
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        // Note that by default WebLogic server is not created with security, so credentials are not needed.
        // TODO: Verify the server address and port number
        env.put(Context.PROVIDER_URL, "t3://localhost:7101");
        return new InitialContext( env );
    }
}

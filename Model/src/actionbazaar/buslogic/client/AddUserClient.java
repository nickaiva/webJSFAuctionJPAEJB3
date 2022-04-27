package actionbazaar.buslogic.client;


import actionbazaar.buslogic.AddUser;

import actionbazaar.persistence.BidderStatus;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;


public class AddUserClient {
    static Logger logger =Logger.getLogger("AddUserClient");
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext(args[0],args[1]);
            AddUser addUser = (AddUser)context.lookup("ejb3inaction-Model-AddUser#actionbazaar.buslogic.AddUser");
           /* String userId = "NickAivaSeller";
            String firstName ="Katie";
            String lastName="Uttley";
            String userType=null;
            String city="Leeds";
            String country="UK";
            String   email="katie@Uttley.com";
            String   phone="440251180";
            String  stateCode="EN";
            String  streetname1="2 Nevile st";
            String streetname2="Yorkshire";
            String zipCode="25896";
            Calendar endDate =Calendar.getInstance();
             endDate.add(Calendar.YEAR, -35);
            Timestamp birthDate = new java.sql.Timestamp(endDate.getTimeInMillis());
            addUser.addContactInfo(  userId,  phone,email,streetname1,  streetname2,  zipCode, city, stateCode, country);
            addUser.addSeller(userId, firstName, lastName, userType,birthDate, 5L,10d);
            userId = "NickAivaBidder";
        
            addUser.addContactInfo(  userId,    phone,email, streetname1,  streetname2,  zipCode, city,  stateCode,  country);
            addUser.addBidder(userId, firstName, lastName, userType, 1000L,  birthDate, 1L);
            */
          addUser.changeBidderStatus("KatieUttleyasBidder", BidderStatus.INACTIVE);
          if (logger.isDebugEnabled())
            logger.debug("AddUserClient ended successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Context getInitialContext(String user, String password) throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        env.put(Context.SECURITY_PRINCIPAL, user);
        env.put(Context.SECURITY_CREDENTIALS,password);
        return new InitialContext( env );
    }
}

package actionbazaar;

import actionbazaar.buslogic.BidderAccountCreator;

import actionbazaar.persistence.BillingInfo;
import actionbazaar.persistence.BiographicalInfo;

import actionbazaar.persistence.LoginInfo;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

public class BidderAccountCreatorWLClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            BidderAccountCreator bidderAccountCreator = (BidderAccountCreator)context.lookup("ejb3inaction-Chapter3-BidderAccountCreator#actionbazaar.buslogic.BidderAccountCreator");
          LoginInfo login = new LoginInfo();
                login.setUsername("pLila");
                login.setPassword("welcome");

                bidderAccountCreator.addLoginInfo(login);

                BiographicalInfo bio = new BiographicalInfo();
                bio.setFirstName("Pipi");
                bio.setLastName("Lila");

                bidderAccountCreator.addBiographicalInfo(bio);

                BillingInfo billing = new BillingInfo();
                billing.setCreditCardType("VISA");
                billing.setAccountNumber("0123456789");

                bidderAccountCreator.addBillingInfo(billing);

                // Create account
                bidderAccountCreator.createAccount();
            
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

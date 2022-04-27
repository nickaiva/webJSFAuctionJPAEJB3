package actionbazaar.buslogic.client;

import actionbazaar.buslogic.BazaarAdmin;

import actionbazaar.persistence.Category;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BazaarAdminClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            BazaarAdmin bazaarAdmin =
                (BazaarAdmin)context.lookup("ejb3inaction-Chapter11-BazaarAdmin#actionbazaar.buslogic.BazaarAdmin");
          Category category = bazaarAdmin.createCategory("test category","idiot2");
          System.out.println("Category created with Id:"+category.getCategoryId());
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

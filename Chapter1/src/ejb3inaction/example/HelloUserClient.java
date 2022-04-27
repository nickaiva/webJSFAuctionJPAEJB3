package ejb3inaction.example;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HelloUserClient {
    public static void main(String [] args) {
        System.out.println("Invoking EJB");     
        try {
            final Context context = getInitialContext();
            HelloUser helloUser = (HelloUser)context.lookup("ejb3inaction-Chapter1-HelloUserBean#ejb3inaction.example.HelloUser");
            helloUser.sayHello("Curious George");
            System.out.println("Invoked EJB successfully .. see server console for output");
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

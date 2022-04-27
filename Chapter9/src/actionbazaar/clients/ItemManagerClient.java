package actionbazaar.clients;

import actionbazaar.buslogic.ItemManager;

import actionbazaar.persistence.Item;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ItemManagerClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            ItemManager itemManager = (ItemManager)context.lookup("ejb3inaction-Chapter9-ItemManager#actionbazaar.buslogic.ItemManager");
            Item item = itemManager.addItem("Vintage Car from Junk Yard",
                         "description goes here", new Double(120.00), "idiot");

                 System.out.println("Item created with Item Id:" + item.getItemId());
                 item.setItemName("New Title:those  Cars  met accident");
                 itemManager.updateItem(item);
                 System.out.println("Title for item Id " + item.getItemId()
                         + " updated successfully");

        
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

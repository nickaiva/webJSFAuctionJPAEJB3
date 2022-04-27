package actionbazaar.client;

import actionbazaar.buslogic.BazaarAdmin;

import actionbazaar.persistence.Category;
import actionbazaar.persistence.Item;
import actionbazaar.persistence.User;

import java.util.Date;
import java.util.Hashtable;

import java.util.Iterator;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BazaarAdminClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            BazaarAdmin bazaarAdmin = (BazaarAdmin)context.lookup("ejb3inaction-Chapter10-BazaarAdmin#actionbazaar.buslogic.BazaarAdmin");
            
            System.out.println("Finding categories By name..");
            List categories; 
            categories = bazaarAdmin.findByCategoryName("Dumpster") ;
            Iterator  i = categories.iterator();
            while (i.hasNext())
            {
                 Category cat = (Category) i.next();
                 System.out.println("Id:"+cat.getCategoryId()+" Name:"+cat.getCategoryName());
            }
                      
           System.out.println("Finding categories By Viper Admin..");
           categories = bazaarAdmin.findByUser("viper");
           Iterator  j = categories.iterator();
            while (j.hasNext())
                      {
                        Category cat = (Category) j.next();
                        System.out.println("Id:"+cat.getCategoryId()+" Name:"+cat.getCategoryName()+" User "+cat.getUser().getUserId());
                      }
                   
           System.out.println("Finding Items by date..");
           Date currDate = new Date();
           List items = bazaarAdmin.getItemByDate(currDate);
          Iterator  k = items.iterator();
          while (k.hasNext())
                      {
                        Item item = (Item) k.next();
                        System.out.println("Id:"+item.getItemId()+" Initial Price:"+item.getInitialPrice());
                      }
           
          System.out.println("Finding Items by Price..");
          items = bazaarAdmin.getItemByPriceRange(100.0,145.0);
          Iterator  l = items.iterator();
          while (l.hasNext())
                      {
                        Item item = (Item) l.next();
                        System.out.println("Id:"+item.getItemId()+" Initial Price:"+item.getInitialPrice());
                      }
                       
          System.out.println("Finding Users that have created items");
          List users = bazaarAdmin.getUserWithItems();
          Iterator  m = users.iterator();
          while (m.hasNext())
             {
              User user = (User) m.next();
               System.out.println("Id:"+user.getUserId()+" First Name:"+user.getFirstName());
                      }

            System.out.println("Finding Users with no items");
            users = bazaarAdmin.getUserWithNoItems();
            Iterator  n = users.iterator();
            while (n.hasNext())
               {
                User user = (User) n.next();
                System.out.println("Id:"+user.getUserId()+" First Name:"+user.getFirstName());
                }

            System.out.println("Finding Users with more than 1 items");
            users = bazaarAdmin.getUserWithItemsWithNativeQuery();
            Iterator  o = users.iterator();
             while (o.hasNext())
                      {
                      User user = (User) o.next();
                        System.out.println("Id:"+user.getUserId()+" First Name:"+user.getFirstName());
                      }


        
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

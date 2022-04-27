package actionbazaar.buslogic.client;


import actionbazaar.buslogic.PlaceCategory;

import actionbazaar.persistence.Category;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;


public class PlaceCategoryClient {
    static Logger logger =Logger.getLogger("PlaceCategoryClient");
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            PlaceCategory placeCategory = (PlaceCategory)context.lookup("ejb3inaction-Model-PlaceCategory#actionbazaar.buslogic.PlaceCategory");
          
            java.util.Date today =   new java.util.Date();
            java.sql.Date createDate =   new java.sql.Date(today.getTime());
            logger.debug("createDate is " + createDate.toString() );
            Long catId = placeCategory.addCategory("Refurbished windows", createDate, "nick", Long.valueOf(0));
            if (logger.isDebugEnabled())
                logger.debug("Querying for new category with categoryId: " + catId );
           for (Category category : (List<Category>)placeCategory.getCategoryFindByCategoryId(catId)) {//getCategoryFindAll
                printCategory(category);
            } 
          for (Category category : (List<Category>)placeCategory.getCategoryFindByParentId(0L)) {
              printCategory(category);
          }
          for (Category category : (List<Category>)placeCategory.getCategoryFindByCategoryName("%")) {
              printCategory(category);
          } 
            
          
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printCategory(Category category) {
      logger.debug( "categoryId = " + category.getCategoryId() );
      logger.debug( "categoryName = " + category.getCategoryName() );
      logger.debug( "createdBy = " + category.getCreatedBy() );
      logger.debug( "createDate = " + category.getCreateDate() );
      logger.debug( "parentId = " + category.getParentId() );
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}

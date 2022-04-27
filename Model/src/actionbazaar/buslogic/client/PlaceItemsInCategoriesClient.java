package actionbazaar.buslogic.client;


import actionbazaar.buslogic.PlaceItemsInCategories;

import actionbazaar.persistence.CategoriesItems;
import actionbazaar.persistence.Category;
import actionbazaar.persistence.Item;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;


public class PlaceItemsInCategoriesClient {
    
    static Logger logger =Logger.getLogger("PlaceItemsInCategoriesClient");
    
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            Long ciCategoryId = 72L;
            Long ciItemId = 152L;
          
            PlaceItemsInCategories placeCategoriesItems = (PlaceItemsInCategories)context.lookup("ejb3inaction-Model-PlaceItemsInCategories#actionbazaar.buslogic.PlaceItemsInCategories");
            placeCategoriesItems.placeItemInCategories(ciCategoryId, ciItemId); 
           
/*
            for (Category category : (List<Category>)placeCategoriesItems.getCategoryFindAll()) {
                printCategory(category);
            }

            for (Category category : (List<Category>)placeCategoriesItems.getCategoryFindByCategoryId( 1000 )) {
                printCategory(category);
            }
            for (Category category : (List<Category>)placeCategoriesItems.getCategoryFindByCreatedBy( "%" )) {
                printCategory(category);
            }
            for (Category category : (List<Category>)placeCategoriesItems.getCategoryFindByCategoryName( "%" )) {
                printCategory(category);
            }
            for (Category category : (List<Category>)placeCategoriesItems.getCategoryFindByParentId( 0 )) {
                printCategory(category);
            }
            for (Item item : (List<Item>)placeCategoriesItems.getItemFindAll()) {
                printItem(item);
            }
            for (Item item : (List<Item>)placeCategoriesItems.getItemFindRemovedFromFurtherBiddingItems()) {
                printItem(item);
            }
            for (Item item : (List<Item>)placeCategoriesItems.getItemFindAvailableItems()) {
                printItem(item);
            }
            for (Item item : (List<Item>)placeCategoriesItems.getItemFindWonItems( "%" )) {
                printItem(item);
            }
            for (Item item : (List<Item>)placeCategoriesItems.getItemGetItemsBySellerId( "%" )) {
                printItem(item);
            }
            for (Item item : (List<Item>)placeCategoriesItems.getItemGetItemsByName( "%" )) {
                printItem(item);
            }
            for (Item item : (List<Item>)placeCategoriesItems.getItemGetItemsByEndDate(  FIXME: Pass parameters here  )) {
                printItem(item);
            }
            for (Item item : (List<Item>)placeCategoriesItems.getItemGetItemsByStartDate(  FIXME: Pass parameters here  )) {
                printItem(item);
            }
            for (Item item : (List<Item>)placeCategoriesItems.getItemGetItemsByDateInterval(  FIXME: Pass parameters here  )) {
                printItem(item);
            }
            for (CategoriesItems categoriesitems : (List<CategoriesItems>)placeCategoriesItems.getCategoriesItemsFindAll()) {
                printCategoriesItems(categoriesitems);
            }
    */
        
          if (logger.isDebugEnabled())
            logger.debug("PlaceItemsInCategoriesClient ended successfully");
          
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printCategory(Category category) {
        System.out.println( "categoryId = " + category.getCategoryId() );
        System.out.println( "categoryName = " + category.getCategoryName() );
        System.out.println( "createdBy = " + category.getCreatedBy() );
        System.out.println( "createDate = " + category.getCreateDate() );
        System.out.println( "parentId = " + category.getParentId() );
    }

    private static void printItem(Item item) {
        System.out.println( "bidEndDate = " + item.getBidEndDate() );
        System.out.println( "bidStartDate = " + item.getBidStartDate() );
        System.out.println( "createdDate = " + item.getCreatedDate() );
        System.out.println( "initialPrice = " + item.getInitialPrice() );
        System.out.println( "itemId = " + item.getItemId() );
        System.out.println( "itemName = " + item.getItemName() );
        //System.out.println( "optLock = " + item.getOptLock() );
        System.out.println( "bidSet = " + item.getBidSet() );
        System.out.println( "seller = " + item.getSeller() );
        System.out.println( "order = " + item.getOrder() );
    }

    private static void printCategoriesItems(CategoriesItems categoriesitems) {
        System.out.println( "ciCategoryId = " + categoriesitems.getCiCategoryId() );
        System.out.println( "ciItemId = " + categoriesitems.getCiItemId() );
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}

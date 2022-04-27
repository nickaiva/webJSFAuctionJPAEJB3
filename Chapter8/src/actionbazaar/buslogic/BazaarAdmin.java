package actionbazaar.buslogic;

import actionbazaar.persistence.Bidder;
import actionbazaar.persistence.Category;
import actionbazaar.persistence.Item;

import javax.ejb.Remote;

@Remote
public interface BazaarAdmin {

    Category mergeCategory(Category category) ;

    Category refreshCategory(Category category);
   void removeCategory(Category category);

    Category createCategory(String name, String userId);
   public Bidder createBidder(Bidder bidder);

    Item createItem(String name, 
                    String userId,
                    Long categoryId,
                    Double initialPrice);
 }

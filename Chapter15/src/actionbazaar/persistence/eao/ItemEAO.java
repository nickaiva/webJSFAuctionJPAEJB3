package actionbazaar.persistence.eao;

import actionbazaar.persistence.Bid;
import actionbazaar.persistence.Item;

public interface ItemEAO {
   public Item addItem(Item item);
   public Item findByItemId(Long itemId);
   public Bid findHighestBidForItem(Item item); 

}

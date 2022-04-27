package actionbazaar.buslogic;

import actionbazaar.persistence.Item;

import javax.ejb.Remote;

@Remote
public interface ItemManager {
  public Item addItem(String itemName,
                      String description, 
                      Double initialPrice,
                      String sellerId);
  public Item updateItem(Item item);
  public Item undoItemChanges(Item item);
  public void deleteItem(Item item);
}

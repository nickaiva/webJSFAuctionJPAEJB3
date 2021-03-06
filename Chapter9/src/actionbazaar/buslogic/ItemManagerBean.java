package actionbazaar.buslogic;

import actionbazaar.persistence.Item;

import actionbazaar.persistence.Seller;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "ItemManager", mappedName = "ejb3inaction-Chapter9-ItemManager")
@Remote
public class ItemManagerBean implements ItemManager {
  @PersistenceContext(unitName = "Chapter9")
     private EntityManager entityManager;

    public ItemManagerBean() {
    }

    public Item addItem(String itemName, 
                        String description,
                        Double initialPrice,
                        String sellerId) {
             Item item = new Item();
             item.setItemName(itemName);
             item.setInitialPrice(initialPrice);
             Seller seller = entityManager.find(Seller.class, sellerId);
             item.setSeller(seller);
             entityManager.persist(item);
             return item;
    }

    public Item updateItem(Item item) {
      entityManager.merge(item);
      return item;
    }

    public Item undoItemChanges(Item item) {
      entityManager.refresh(entityManager.merge(item));
      return item;
    }

    public void deleteItem(Item item) {
      entityManager.remove(entityManager.merge(item));
    }
}

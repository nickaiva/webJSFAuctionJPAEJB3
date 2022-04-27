package actionbazaar.buslogic;

import actionbazaar.persistence.Category;
import actionbazaar.persistence.Item;

import actionbazaar.persistence.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "BazaarAdmin", mappedName = "ejb3inaction-Chapter11-BazaarAdmin")
@Remote
public class BazaarAdminBean implements BazaarAdmin {
  @PersistenceContext(name="Chapter11") 
   private EntityManager em; 
   
    public BazaarAdminBean() {
    }

    public Category mergeCategory(Category category) {
      em.merge(category);
      return category;
    }

    public Category refreshCategory(Category category) {
      em.refresh(category);
      return category;
    }

    public void removeCategory(Category category) {
      em.remove(em.merge(category));
    }

    public Category createCategory(String name, String userId) {
              Category category = new Category();
              category.setCategoryName(name);
              User user = em.find(User.class, userId);
              System.out.println(user.getFirstName());
              category.setUser(user);
              user.addCategory(category);
              return category;
    }

    public Item createItem(String name, String userId, Long categoryId,
                           Double initialPrice) {
            Item item = new Item();
            item.setItemName(name);
            item.setInitialPrice(initialPrice);
            Category category = em.find(Category.class,categoryId);
            item.addCategory(category);
            return item;
    }
}

package actionbazaar.buslogic;


import actionbazaar.persistence.CategoriesItems;
import actionbazaar.persistence.Category;
import actionbazaar.persistence.Item;

import java.sql.Timestamp;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface PlaceItemsInCategories {
   
        
    void placeItemInCategories(Long ciCategoryId, Long ciItemId);
   
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Category persistCategory(Category category);

    Category mergeCategory(Category category);

    void removeCategory(Category category);

    List<Category> getCategoryFindAll();

    List<Category> getCategoryFindByCategoryId(Long categoryId);

    List<Category> getCategoryFindByCreatedBy(String createdBy);

    List<Category> getCategoryFindByCategoryName(String categoryName);

    List<Category> getCategoryFindByParentId(Long parentId);

    Item persistItem(Item item);

    Item mergeItem(Item item);

    void removeItem(Item item);

    List<Item> getItemFindAll();

    List<Item> getItemFindRemovedFromFurtherBiddingItems();

    List<Item> getItemFindAvailableItems();

    List<Item> getItemFindWonItems(Object userId);

    List<Item> getItemGetItemsBySellerId(Object sellerId);

    List<Item> getItemGetItemsByName(Object userId, String itemName);

    List<Item> getItemGetItemsByEndDate(Timestamp bidEndDate);

    List<Item> getItemGetItemsByStartDate(Timestamp bidStartDate);

    List<Item> getItemGetItemsByDateInterval(Timestamp bidStartDate,
                                             Timestamp bidEndDate);

    CategoriesItems persistCategoriesItems(CategoriesItems categoriesItems);

    CategoriesItems mergeCategoriesItems(CategoriesItems categoriesItems);

    void removeCategoriesItems(CategoriesItems categoriesItems);

    List<CategoriesItems> getCategoriesItemsFindAll();
}

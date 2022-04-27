package actionbazaar.buslogic;


import actionbazaar.persistence.CategoriesItems;
import actionbazaar.persistence.CategoriesItemsPK;
import actionbazaar.persistence.Category;
import actionbazaar.persistence.Item;

import java.sql.Timestamp;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;

import javax.ejb.Stateless;


@DeclareRoles(value = { "SELLER", "ADMINISTRATOR"})
@Stateless(name = "PlaceItemsInCategories", mappedName = "ejb3inaction-Model-PlaceItemsInCategories")
public class PlaceItemsInCategoriesBean extends CustomBean implements PlaceItemsInCategories,
                                                 PlaceItemsInCategoriesLocal {
   

    public PlaceItemsInCategoriesBean() {
    }

    @RolesAllowed(value = { "SELLER", "ADMINISTRATOR"})
    public void placeItemInCategories( Long CategoryId,
                                                      Long ItemId ) {
    
         CategoriesItems ci = new CategoriesItems();
         ci.setCiCategoryId(CategoryId);
         ci.setCiItemId(ItemId);
        
          em.persist(ci);
     
    }


    public Category persistCategory(Category category) {
        em.persist(category);
        return category;
    }

    public Category mergeCategory(Category category) {
        return em.merge(category);
    }

    public void removeCategory(Category category) {
        category = em.find(Category.class, category.getCategoryId());
        em.remove(category);
    }

    /** <code>select o from Category o</code> */
    public List<Category> getCategoryFindAll() {
        return em.createNamedQuery("Category.findAll").getResultList();
    }

    /** <code>select o from Category o where o.categoryId = :categoryId</code> */
    public List<Category> getCategoryFindByCategoryId(Long categoryId) {
        return em.createNamedQuery("Category.findByCategoryId").setParameter("categoryId", categoryId).getResultList();
    }

    /** <code>select o from Category o where o.createdBy = :createdBy</code> */
    public List<Category> getCategoryFindByCreatedBy(String createdBy) {
        return em.createNamedQuery("Category.findByCreatedBy").setParameter("createdBy", createdBy).getResultList();
    }

    /** <code>select o from Category o where o.categoryName LIKE :categoryName</code> */
    public List<Category> getCategoryFindByCategoryName(String categoryName) {
        return em.createNamedQuery("Category.findByCategoryName").setParameter("categoryName", categoryName).getResultList();
    }

    /** <code>select o from Category o where o.parentId = :parentId</code> */
    public List<Category> getCategoryFindByParentId(Long parentId) {
        return em.createNamedQuery("Category.findByParentId").setParameter("parentId", parentId).getResultList();
    }

    public Item persistItem(Item item) {
        em.persist(item);
        return item;
    }

    public Item mergeItem(Item item) {
        return em.merge(item);
    }

    public void removeItem(Item item) {
        item = em.find(Item.class, item.getItemId());
        em.remove(item);
    }

    /** <code>select o from Item o ORDER BY o.itemName</code> */
    public List<Item> getItemFindAll() {
        return em.createNamedQuery("Item.findAll").getResultList();
    }

    /** <code>select o from Item o WHERE o.bidEndDate <= CURRENT_TIMESTAMP ORDER BY o.itemName</code> */
    public List<Item> getItemFindRemovedFromFurtherBiddingItems() {
        return em.createNamedQuery("Item.findRemovedFromFurtherBiddingItems").getResultList();
    }

    /** <code>select o from Item o WHERE o.bidEndDate > CURRENT_TIMESTAMP ORDER BY o.itemName</code> */
    public List<Item> getItemFindAvailableItems() {
        return em.createNamedQuery("Item.findAvailableItems").getResultList();
    }

    /** <code>select o from Item o, Bid b  WHERE o.bidEndDate <= CURRENT_TIMESTAMP  AND b.bidStatus LIKE 'WINNER'  AND o.itemId =b.item.itemId  AND   b.bidBidder.userId LIKE :userId ORDER BY o.itemName</code> */
    public List<Item> getItemFindWonItems(Object userId) {
        return em.createNamedQuery("Item.findWonItems").setParameter("userId", userId).getResultList();
    }

    /** <code>SELECT b FROM Item b WHERE b.seller.userId LIKE :sellerId ORDER BY  b.createdDate DESC</code> */
    public List<Item> getItemGetItemsBySellerId(Object sellerId) {
        return em.createNamedQuery("Item.getItemsBySellerId").setParameter("sellerId", sellerId).getResultList();
    }

    /** <code>SELECT b FROM Item b WHERE b.seller.userId LIKE :userId  AND   b.itemName LIKE :itemName  ORDER BY  b.createdDate DESC</code> */
    public List<Item> getItemGetItemsByName(Object userId, String itemName) {
        return em.createNamedQuery("Item.getItemsByName").setParameter("userId", userId).setParameter("itemName", itemName).getResultList();
    }

    /** <code>SELECT b FROM Item b WHERE b.bidEndDate <= :bidEndDate ORDER BY  b.createdDate DESC</code> */
    public List<Item> getItemGetItemsByEndDate(Timestamp bidEndDate) {
        return em.createNamedQuery("Item.getItemsByEndDate").setParameter("bidEndDate", bidEndDate).getResultList();
    }

    /** <code>SELECT b FROM Item b WHERE b.bidStartDate <= :bidStartDate ORDER BY  b.createdDate DESC</code> */
    public List<Item> getItemGetItemsByStartDate(Timestamp bidStartDate) {
        return em.createNamedQuery("Item.getItemsByStartDate").setParameter("bidStartDate", bidStartDate).getResultList();
    }

    /** <code>SELECT b FROM Item b WHERE b.bidStartDate BETWEEN :bidStartDate AND  :bidEndDate ORDER BY  b.createdDate DESC</code> */
    public List<Item> getItemGetItemsByDateInterval(Timestamp bidStartDate,
                                                    Timestamp bidEndDate) {
        return em.createNamedQuery("Item.getItemsByDateInterval").setParameter("bidStartDate", bidStartDate).setParameter("bidEndDate", bidEndDate).getResultList();
    }

    public CategoriesItems persistCategoriesItems(CategoriesItems categoriesItems) {
        em.persist(categoriesItems);
        return categoriesItems;
    }

    public CategoriesItems mergeCategoriesItems(CategoriesItems categoriesItems) {
        return em.merge(categoriesItems);
    }

    public void removeCategoriesItems(CategoriesItems categoriesItems) {
        categoriesItems = em.find(CategoriesItems.class, new CategoriesItemsPK(categoriesItems.getCiCategoryId(), categoriesItems.getCiItemId()));
        em.remove(categoriesItems);
    }

    /** <code>select o from CategoriesItems o</code> */
    public List<CategoriesItems> getCategoriesItemsFindAll() {
        return em.createNamedQuery("CategoriesItems.findAll").getResultList();
    }
}

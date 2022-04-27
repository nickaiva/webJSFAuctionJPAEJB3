package actionbazaar.buslogic;


import actionbazaar.persistence.Category;

import java.sql.Date;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface PlaceCategory {
    
    Long addCategory( String categoryName,
                              Date createDate,
                              String createdBy,
                              Long parentId);

    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Category persistCategory(Category category);

    Category mergeCategory(Category category);

    void deleteCategory(Long categoryId);
   
    void removeCategory(Category category);

    List<Category> getCategoryFindAll();
    
    List<Category> getCategoryFindByCategoryId(Long categoryId);
    
  List<Category> getCategoryFindByParentId(Long parentId);
  
  List<Category> getCategoryFindByCategoryName(String categoryName);

    List<Category> getCategoryFindByCreatedBy(String createdBy);
}

package actionbazaar.buslogic;


import actionbazaar.persistence.Category;

import java.sql.Date;

import java.util.List;

import javax.ejb.Local;


@Local
public interface PlaceCategoryLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

   Long addCategory( String categoryName, 
                             Date createDate,
                            String createdBy,
                            Long parentId);

    Category persistCategory(Category category);

    Category mergeCategory(Category category);

    void removeCategory(Category category);
    
    void deleteCategory(Long categoryId);
    
    List<Category> getCategoryFindAll();
    
  List<Category> getCategoryFindByCategoryId(Long categoryId);
  
  List<Category> getCategoryFindByParentId(Long parentId);
  
  List<Category> getCategoryFindByCategoryName(String categoryName);

    List<Category> getCategoryFindByCreatedBy(String createdBy);
}

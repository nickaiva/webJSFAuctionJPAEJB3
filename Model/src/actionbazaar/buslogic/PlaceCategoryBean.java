package actionbazaar.buslogic;


import actionbazaar.persistence.Category;

import java.sql.Date;

import java.util.List;

import javax.annotation.security.DeclareRoles;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.Query;


@Remote
@Stateless(name = "PlaceCategory", mappedName = "ejb3inaction-Model-PlaceCategory")
@DeclareRoles(value = { "SELLER", "ADMINISTRATOR"})
public class PlaceCategoryBean extends CustomBean implements PlaceCategory, PlaceCategoryLocal {

    
    public PlaceCategoryBean() {
    }

   public void deleteCategory(Long categoryId) {
            Category category = em.find(Category.class, categoryId);
            em.remove(category);
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

    public Long addCategory(String categoryName,
                                        Date createDate,
                                        String createdBy, 
                                        Long parentId) {
      Category category = new Category();
      category.setCategoryName(categoryName);
     
      /*In case no user date is given, default to today*/
      if (createDate == null) {
                         
              java.util.Date today =   new java.util.Date();
              createDate =   new java.sql.Date(today.getTime());
              logger.debug("createDate is " + createDate.toString() );
      }
      category.setCreateDate(createDate);
      category.setCreatedBy(createdBy);
      category.setParentId(parentId);
      em.persist(category);
      if (logger.isDebugEnabled())
         logger.debug("\n Created new categoryId: " + category.getCategoryId());
      return category.getCategoryId();
    }

    public List<Category> getCategoryFindByCategoryId(Long categoryId) {
       return em.createNamedQuery("Category.findByCategoryId").setParameter("categoryId", categoryId).getResultList();
    }
  public List<Category> getCategoryFindByParentId(Long parentId) {
      return em.createNamedQuery("Category.findByParentId").setParameter("parentId", parentId).getResultList();
  }

  /**
   * @param categoryName
   * @return
   */
   @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public List<Category> getCategoryFindByCategoryName(String categoryName) {
      Query q = em.createNamedQuery("Category.findByCategoryName").setParameter("categoryName", categoryName);
      q.setFirstResult(0);
      q.setMaxResults(10);
      return q.getResultList();
  }

    /** <code>select o from Category o where o.createdBy = :createdBy</code> */
    public List<Category> getCategoryFindByCreatedBy(String createdBy) {
        return em.createNamedQuery("Category.findByCreatedBy").setParameter("createdBy", createdBy).getResultList();
    }
}

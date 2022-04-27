package actionbazaar.persistence;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Set;

import javax.annotation.Generated;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries({
  @NamedQuery(name = "Category.findAll", query = "select o from Category o")
})
@Table(name = "CATEGORIES")
public class Category implements Serializable {
    private Long categoryId;
    private Timestamp categoryName;
    private Timestamp  createDate;
    private Set<Item> items;
    private User user;
    private Category parentCategory;
    private Set<Category> subCategorySet;

  
    public Category() {
    }

    public Category(Long categoryId, Timestamp categoryName, Timestamp createDate) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.createDate = createDate;
       
    }
  
  @TableGenerator(name="CATEGORY_TABLE_GEN",
       table="TABLE_SEQ_GEN",
       pkColumnName="SEQ_NAME",
       valueColumnName="SEQ_COUNT",
       pkColumnValue="CATEGORY_SEQ")


    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CATEGORY_TABLE_GEN")
    @Column(name="CATEGORY_ID", nullable = false)
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name="CATEGORY_NAME")
    public Timestamp getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Timestamp categoryName) {
        this.categoryName = categoryName;
    }

 
    @Column(name="CREATE_DATE")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }


    public void setItems(Set<Item> items) {
        this.items = items;
    }
@ManyToMany(cascade=CascadeType.PERSIST)
@JoinTable(name = "CATEGORIES_ITEMS", 
joinColumns= @JoinColumn( name="CI_CATEGORY_ID", referencedColumnName = "CATEGORY_ID") ,
 inverseJoinColumns = @JoinColumn(name="CI_ITEM_ID", referencedColumnName = "ITEM_ID")
    )
    public Set<Item> getItems() {
        return items;
    }

    public void setUser(User user) {
        this.user = user;
    }
@ManyToOne
@JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
@ManyToOne
@JoinColumn(name = "PARENT_ID", referencedColumnName = "CATEGORY_ID")
    public Category getParentCategory() {
        return parentCategory;
    }

    public void setSubCategorySet(Set<Category> subCategorySet) {
        this.subCategorySet = subCategorySet;
    }
@OneToMany(mappedBy = "parentCategory")
    public Set<Category> getSubCategorySet() {
        return subCategorySet;
    }
  public Category addCategory(Category category) {
       getSubCategorySet().add(category);
      // category.setCategory(this);
       return category;
   }

   public Category removeCategory(Category category) {
       getSubCategorySet().remove(category);
       category.setParentCategory(null);
       return category;
   }

}

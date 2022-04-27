package actionbazaar.persistence;

import java.io.Serializable;

import java.util.Set;

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
import javax.persistence.Table;
/*Do not use name ="Category.findCategoryByName" in @NamedQuery */
@Entity
@NamedQueries({
  @NamedQuery(name = "Category.findAll", query = "select o from Category o"),
  @NamedQuery(name ="findCategoryByName",
              query="SELECT c FROM Category c " +
              "WHERE  c.categoryName LIKE :categoryName" +
              " ORDER BY c.categoryId"),
  @NamedQuery(name="findCategoryByUser" , query="SELECT c FROM Category AS c JOIN c.user AS u WHERE u.userId = ?1")
})
@Table(name = "CATEGORIES")
public class Category implements Serializable {
    private Long categoryId;
    private String categoryName;
    
    private String createDate;
    private Set<Item> items;
    private User user;
    private Category category;
    private Set<Category> categorySet;

  
    public Category() {
    }

      

    public Category(Long categoryId, String categoryName, String createDate
                     ) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.createDate = createDate;
        
    }

    @Id
    @Column(name="CATEGORY_ID", nullable = false)
  @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="CATEGORY_SEQ_GEN")
      
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name="CATEGORY_NAME")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    
  
    @Column(name="CREATE_DATE")
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

   
    public void setItems(Set<Item> items) {
        this.items = items;
    }
  @ManyToMany(cascade=CascadeType.PERSIST)
      @JoinTable(name="CATEGORIES_ITEMS",
      joinColumns=
      @JoinColumn(name="CATEGORY_ID", referencedColumnName="CATEGORY_ID"),
      inverseJoinColumns=
       @JoinColumn(name="ITEM_ID", referencedColumnName="ITEM_ID"))
      
    public Set<Item> getItems() {
        return items;
    }
  public Item addItem(Item item) {
         getItems().add(item);
         item.addCategory(this);
         return item;
     }

     public Item removeItem(Item item) {
         getItems().remove(item);
         item.setCategory(null);
         return item;
     }

    public void setUser(User user) {
        this.user = user;
    }
  @ManyToOne
  @JoinColumn(name="CREATED_BY", referencedColumnName="USER_ID")
      
    public User getUser() {
        return user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
  @ManyToOne
  @JoinColumn(name="PARENT_ID", referencedColumnName="CATEGORY_ID")
      
    public Category getCategory() {
        return category;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }
  @OneToMany(mappedBy="category")
    public Set<Category> getCategorySet() {
        return categorySet;
    }
  public Category addCategory(Category category) {
          getCategorySet().add(category);
         // category.setCategory(this);
          return category;
      }

      public Category removeCategory(Category category) {
          getCategorySet().remove(category);
          category.setCategory(null);
          return category;
      }

}

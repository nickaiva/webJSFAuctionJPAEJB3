package actionbazaar.persistence;

import java.io.Serializable;

import java.sql.Timestamp;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "Category.findAll", query = "select o from Category o")
})
@Table(name = "CATEGORIES")
public class Category implements Serializable {
    private Long categoryId;
    private String categoryName;
    private Timestamp createDate;
    private Set<Item> items;
    private User user;
    private Category category;
    private Set<Category> categorySet;

    public Category() {
    }

    public Category(Long categoryId, String categoryName, Timestamp createDate) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.createDate = createDate;
        
    }

  @SequenceGenerator(name="CATEGORY_SEQ_GEN", 
      sequenceName="CATEGORY_SEQ",initialValue=1, allocationSize=1)
  
    @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="CATEGORY_SEQ_GEN")
    @Column(name="CATEGORY_ID", nullable = false)
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
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

  public Item addItem(Item item) {
        getItems().add(item);
        item.addCategory(this);
        return item;
    }

    public Item removeItem(Item item) {
        getItems().remove(item);
        item.setCategorySet(null);
        return item;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
  @ManyToMany(cascade=CascadeType.PERSIST)
  @JoinTable(name="CATEGORIES_ITEMS",
      joinColumns=
   @JoinColumn(name="CI_CATEGORY_ID", referencedColumnName="CATEGORY_ID"),
      inverseJoinColumns=
   @JoinColumn(name="CI_ITEM_ID", referencedColumnName="ITEM_ID"))
      
    public Set<Item> getItems() {
        return items;
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

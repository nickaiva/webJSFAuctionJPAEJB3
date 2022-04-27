package actionbazaar.persistence;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@NamedQueries({
@NamedQuery(
name="findUserWithItems",
query="SELECT distinct u FROM User u WHERE  exists (SELECT i FROM Item i WHERE i=u.items)"),
@NamedQuery(
name="findUserWithNoItems",
query="SELECT distinct u FROM User u WHERE  u.items is EMPTY"
)
})
@NamedNativeQuery(
name="findUserWithMoreItems",
query="select user_id , first_name , last_name, birth_date from users where user_id in (select seller_id from items group by seller_id having count(*) >?1)",
resultClass=actionbazaar.persistence.User.class
)
@Table(name = "USERS")
public class User implements Serializable {
   
    private Date birthDate;
    private String firstName;
    private String lastName;
    
    private String userId;
    
    private Set<Category> categories;
    private Set<Item> items;
    private BillingInfo billingInfo;

    
    public User() {
    }

    public User(String bidderStatus, Timestamp birthDate, Double commRate,
                Long creditRating, String firstName, String lastName,
                Long maxItems, Long userBillingId, String userId,
                String userType) {
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
      
    }

  
    @Column(name="BIRTH_DATE")
  @Temporal(javax.persistence.TemporalType.DATE) 
      
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

  
    @Column(name="FIRST_NAME", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="LAST_NAME", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Id
    @Column(name="USER_ID", nullable = false)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

  
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
  @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    public Set<Category> getCategories() {
        return categories;
    }
  public Category addCategory(Category category) {
         getCategories().add(category);
         category.setUser(this);
         return category;
     }

     public Category removeCategory(Category category) {
         getCategories().remove(category);
         category.setUser(null);
         return category;
     }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
  @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    public Set<Item> getItems() {
        return items;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }
@OneToOne(cascade=CascadeType.ALL)
@JoinColumn(name="USER_BILLING_ID", referencedColumnName="BILLING_ID")
      
    public BillingInfo getBillingInfo() {
        return billingInfo;
    }
  public Item addItem(Item item) {
        getItems().add(item);
        item.setUser(this);
        return item;
    }

    public Item removeItem(Item item) {
        getItems().remove(item);
        item.setUser(null);
        return item;
    }

}

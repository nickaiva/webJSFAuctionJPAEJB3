package actionbazaar.persistence;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="USER_TYPE",discriminatorType=DiscriminatorType.STRING, length=1)

@NamedQueries({
  @NamedQuery(name = "User.findAll", query = "select o from User o")
})
@Table(name = "USERS")
public class User implements Serializable {
    private Timestamp birthDate;
    private String firstName;
    private String lastName;
    private Long maxItems;
    private String userId;
    private BillingInfo billingInfo;
    private ContactInfo contactInfo;
    private Set<Category> categories;

    
    public User() {
    }

    public User( Timestamp birthDate,  String firstName, String lastName,
                Long maxItems,  String userId
          ) {
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maxItems = maxItems;
        this.userId = userId;
       
    }

  
    @Column(name="BIRTH_DATE")
    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
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


    public void setMaxItems(Long maxItems) {
        this.maxItems = maxItems;
    }


  
    @Id
    @Column(name="USER_ID", nullable = false)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }
  @OneToOne(cascade=CascadeType.ALL)  
  @JoinColumn(name="USER_BILLING_ID", referencedColumnName="BILLING_ID")                           
    
    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
  
 @OneToOne(cascade=CascadeType.ALL)  
@PrimaryKeyJoinColumn(name="USER_ID",referencedColumnName="CONTACT_USER_ID")                           
    
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
  @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
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

}

package actionbazaar.persistence;

import java.io.Serializable;

import java.util.Date;

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
import javax.persistence.Temporal;

@Entity
@NamedQueries({
  @NamedQuery(name = "User.findAll", query = "select o from User o")
})
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING, length = 1)

public class User implements Serializable {
    
    private Date birthDate;
    private String firstName;
    private String lastName;
    private String userId;
    private Set<Category> categorySet;
    private BillingInfo billingInfo;
    private ContactInfo contactInfo;

    public User() {
    }

    public User( Date birthDate,  String firstName, String lastName,
                 BillingInfo billingInfo,
                ContactInfo contactInfo) {
        
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.billingInfo = billingInfo;
        this.contactInfo = contactInfo;
        
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
  @Column(name = "USER_ID", nullable = false)
      public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

   
@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }

    public Category addCategory(Category category) {
        getCategorySet().add(category);
        category.setUser(this);
        return category;
    }

    public Category removeCategory(Category category) {
        getCategorySet().remove(category);
        category.setUser(null);
        return category;
    }

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "USER_BILLING_ID", referencedColumnName = "BILLING_ID")
      public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

   
   
  @OneToOne( optional=false, cascade = CascadeType.ALL )
  @PrimaryKeyJoinColumn(name = "USER_ID", referencedColumnName = "CONTACT_USER_ID"  )
  public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
        if (contactInfo != null) {
            this.userId = contactInfo.getUserId();
        }
    }
}

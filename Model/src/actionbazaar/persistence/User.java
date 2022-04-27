package actionbazaar.persistence;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING, length = 1)
@Entity(name = "User")
@NamedQueries({
  @NamedQuery(name = "User.findAll", query = "select o from User o"),
  @NamedQuery(name = "User.findByUserId", query = "select o from User o WHERE o.userId LIKE :userId" ),
   @NamedQuery(name = "User.findByLastName", query = "select o from User o WHERE o.lastName LIKE :lastName"),
   @NamedQuery(name = "User.findByUserBillingId", query = "select o from User o WHERE o.userBillingId = :userBillingId"),
   @NamedQuery(name = "User.findByBirthDate", query = "select o from User o WHERE o.birthDate <= :birthDate")
})
@Table(name = "Users")
public class User implements Serializable {

    @SuppressWarnings("compatibility:2435201103470264087")
    private static final long serialVersionUID = 241912114777541791L;
    @Column(name="BIRTH_DATE")
    private Timestamp birthDate;
    @Column(name="FIRST_NAME", nullable = false, length=40)
    private String firstName;
    @Column(name="LAST_NAME", nullable = false, length=40)
    private String lastName;
   
    @Column(name="USER_BILLING_ID")
    private Long userBillingId;
    @Id
    @Column(name="USER_ID", nullable = false, length = 40)
    private String userId;
    @Column(name="USER_TYPE", length = 10)
    private String userType;

    public User() {
    }

    public User(Timestamp birthDate, Double commRate,
                 Long creditRating, String firstName, String lastName,
                 Long maxItems, Long userBillingId, String userId,
                 String userType) {
        
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userBillingId = userBillingId;
        this.userId = userId;
        this.userType = userType;
    }

  

    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

  
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toUpperCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase();
    }

  
    public Long getUserBillingId() {
        return userBillingId;
    }

    public void setUserBillingId(Long userBillingId) {
        this.userBillingId = userBillingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

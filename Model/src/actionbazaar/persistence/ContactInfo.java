package actionbazaar.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries({
  @NamedQuery(name = "ContactInfo.findAll", query = "select o from ContactInfo o"),
  @NamedQuery(name = "ContactInfo.getContactInfoByUser", query = "SELECT b FROM ContactInfo b " +
                                    "WHERE b.contactUserId LIKE :contactUserId ")
})
@Table(name = "CONTACT_DETAILS")
public class ContactInfo implements Serializable {

    @SuppressWarnings("compatibility:-6664897460772365808")
    private static final long serialVersionUID = -8929228581272936371L;
    @Id
    @Column(name="CONTACT_USER_ID", nullable = false)
    private String contactUserId;
    @Column
    private String phone; 
    @Column
    private String email;
    
    @Embedded
    private Address address;
 /* 
    @Version
    @Column(name = "OPT_LOCK")
    private Long optLock;
*/
    public ContactInfo() {
    }

    public ContactInfo( String contactUserId, 
                                String phone,
                                String email,
                                Address address) {
       
        this.contactUserId = contactUserId;
        this.email = email;
        this.phone = phone;
        this.address= address;
    }

    public String getContactUserId() {
        return contactUserId;
    }

    public void setContactUserId(String contactUserId) {
        this.contactUserId = contactUserId;
    }

  public String getPhone() {
      return phone;
  }

  public void setPhone(String phone) {
      this.phone = phone;
  } 

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toUpperCase();
    }

     public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
/*
    public void setOptLock(Long optLock) {
        this.optLock = optLock;
    }

    public Long getOptLock() {
        return optLock;
    }
*/
}

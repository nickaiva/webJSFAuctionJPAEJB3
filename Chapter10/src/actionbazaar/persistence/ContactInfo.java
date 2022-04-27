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
  @NamedQuery(name = "ContactInfo.findAll", query = "select o from ContactInfo o")
})
@Table(name = "CONTACT_DETAILS")
public class ContactInfo implements Serializable {
    private String contactUserId;
    private String email;
    private String phone;
    private Address address;
    
    public ContactInfo() {
    }

    public ContactInfo( String contactUserId,
                       String email, String phone  ) {
        
        this.contactUserId = contactUserId;
        this.email = email;
        this.phone = phone;
    }

    @Id
    @Column(name="CONTACT_USER_ID", nullable = false)
    public String getContactUserId() {
        return contactUserId;
    }

    public void setContactUserId(String contactUserId) {
        this.contactUserId = contactUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 10)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

  @Embedded
    public Address getAddress() {
        return address;
    }
}

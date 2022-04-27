package actionbazaar.persistence;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "ContactInfo.findAll", query = "select o from ContactInfo o")
})
@Table(name = "CONTACT_DETAILS")
public class ContactInfo implements Serializable {
    protected String userId;
    protected String email;
    protected String phone;
    protected Address address;
    
    public ContactInfo() {
    }

    public ContactInfo( String userId, 
                       String email,
                        String phone,
                        Address address) {
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.address = address;
           }

   
    @Id
    @Column(name="CONTACT_USER_ID", nullable = false)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

  @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
  @Column(name = "PHONE")
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

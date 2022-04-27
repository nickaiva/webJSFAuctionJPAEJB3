package actionbazaar.persistance;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "Bidder.findAll", query = "select o from Bidder o")
})
@Table(name = "BIDDERS")
public class Bidder implements Serializable {
    @Column(name="CREDIT_CARD_TYPE", length = 20)
    private String creditCardType;
    @Column(name="FIRST_NAME", length = 30)
    private String firstName;
    @Id
    @Column(nullable = false, length = 10)
    private String username;

    public Bidder() {
    }

    public Bidder(String creditCardType, String firstName, String username) {
        this.creditCardType = creditCardType;
        this.firstName = firstName;
        this.username = username;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

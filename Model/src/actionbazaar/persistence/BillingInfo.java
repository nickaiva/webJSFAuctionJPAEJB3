package actionbazaar.persistence;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "BillingInfo.findAll", query = "select o from BillingInfo o"),
        @NamedQuery(name = "BillingInfo.findByBillingId", query = "select o from BillingInfo o where o.billingId = :billingId"),
        @NamedQuery(name = "BillingInfo.findByUserId", query = "select o from BillingInfo o,  User u where o.billingId = u.userBillingId AND u.userId=:userId ") })
@Table(name = "BILLING_DETAILS")
public class BillingInfo implements Serializable {

    @SuppressWarnings("compatibility:3382792038286117159")
    private static final long serialVersionUID = -6720515676206991010L;
    @SequenceGenerator(name="BILLING_SEQ_GEN", 
      sequenceName="BILLING_SEQUENCE", 
                           initialValue=100, allocationSize=1)

    @Column(name="ACCOUNT_NO", length = 20)
    private String accountNo;
    @Id
    @Column(name="BILLING_ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="BILLING_SEQ_GEN")      
    private Long billingId;
    @Column(name="CARD_TYPE", length = 10)
    private String cardType;
 
    @Column(name="EXPIRY_DATE", length = 4)
    private String expiryDate;
    @Column(name="SECRET_CODE", length = 5)
    private String secretCode;
  
    @Embedded
    @AttributeOverrides(
     {@AttributeOverride(name="state",column=@Column(name="STATE_CD")),
      @AttributeOverride(name="zipCode",column=@Column(name="ZIP_CD"))
      })
     private Address address;
    @OneToMany(mappedBy = "billingInfo")
    private Set<Order> orderSet;
  /*
    @Version
    @Column(name="OPT_LOCK")
    private Long optLock;
*/
    public BillingInfo() {
    }

    public BillingInfo(String accountNo,
                            String cardType,
                            String expiryDate,  
                            String secretCode,
                            Address address) {
        this.accountNo = accountNo;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        this.secretCode = secretCode;
        this.address = address;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Long getBillingId() {
        return billingId;
    }

    public void setBillingId(Long billingId) {
        this.billingId = billingId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType.toUpperCase();
    }

   

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode.toUpperCase();
    }

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

    public Order addOrder(Order order) {
        getOrderSet().add(order);
        order.setBillingInfo(this);
        return order;
    }

    public Order removeOrder(Order order) {
        getOrderSet().remove(order);
        order.setBillingInfo(null);
        return order;
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

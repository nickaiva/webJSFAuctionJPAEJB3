package actionbazaar.persistence;

import java.io.Serializable;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "BillingInfo.findAll", query = "select o from BillingInfo o")
})
@Table(name = "BILLING_DETAILS")
public class BillingInfo implements Serializable {
    private String accountNo;
    private Long billingId;
    private String cardType;
    private String expiryDate;
    private String secretCode;
    protected Address address;
  
       
    public BillingInfo() {
    }
    
  public BillingInfo(String accountNumber, String creditCardType,String expiryDate ) {
    this.accountNo = accountNumber;
    this.cardType = creditCardType;
    this.expiryDate = expiryDate;
   }

    
    public BillingInfo(String accountNo, Long billingId, String cardType,
                        String expiryDate,
                       String secretCode
                       ) {
        this.accountNo = accountNo;
        this.billingId = billingId;
        this.cardType = cardType;
        this.expiryDate = expiryDate;
        this.secretCode = secretCode;
        
    }

    @Column(name="ACCOUNT_NO", length = 20)
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
  @SequenceGenerator(name="BILLING_SEQ_GEN", 
     sequenceName="BILLING_SEQUENCE", 
                          initialValue=1, allocationSize=1)
  
@Id
@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="BILLING_SEQ_GEN")
@Column(name="BILLING_ID", nullable = false)
    public Long getBillingId() {
        return billingId;
    }

    public void setBillingId(Long billingId) {
        this.billingId = billingId;
    }

    @Column(name="CARD_TYPE", length = 10)
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Column(name="EXPIRY_DATE", length = 4)
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Column(name="SECRET_CODE", length = 5)
    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
  @Embedded
  @AttributeOverrides(
         {@AttributeOverride(name="state",column=@Column(name="STATE_CD")),
          @AttributeOverride(name="zipCode",column=@Column(name="ZIP_CD"))
          })

    public Address getAddress() {
        return address;
    }
}

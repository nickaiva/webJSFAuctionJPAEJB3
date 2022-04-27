package actionbazaar.persistence;

import java.io.Serializable;

import javax.persistence.Column;
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
    private String expiryDate;
    
    public BillingInfo() {
    }

    public BillingInfo(String accountNo, Long billingId,  String expiryDate) {
        this.accountNo = accountNo;
        this.billingId = billingId;
        this.expiryDate = expiryDate;
            }

    @Column(name="ACCOUNT_NO")
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @Id
    @Column(name="BILLING_ID", nullable = false)
  @SequenceGenerator(name="BILLING_SEQ_GEN", sequenceName="BILLING_SEQ",initialValue=1,allocationSize=1)
   @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="BILLING_SEQ_GEN")

    public Long getBillingId() {
        return billingId;
    }

    public void setBillingId(Long billingId) {
        this.billingId = billingId;
    }

@Column(name="EXPIRY_DATE")
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

}

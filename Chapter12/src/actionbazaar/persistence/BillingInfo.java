package actionbazaar.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "BillingInfo.findAll", query = "select o from BillingInfo o")
})
@Table(name = "BILLING_DETAILS")
public class BillingInfo implements Serializable {
    private String accountNo;
    private Long billingId;
    private String city;
    private String country;
    private String expiryDate;
    private String secretCode;
    private String stateCd;
    private String streetname1;
    private String streetname2;
    private String zipCd;

    public BillingInfo() {
    }

    public BillingInfo(String accountNo, Long billingId, String city,
                       String country, String expiryDate, String secretCode,
                       String stateCd, String streetname1, String streetname2,
                       String zipCd) {
        this.accountNo = accountNo;
        this.billingId = billingId;
        this.city = city;
        this.country = country;
        this.expiryDate = expiryDate;
        this.secretCode = secretCode;
        this.stateCd = stateCd;
        this.streetname1 = streetname1;
        this.streetname2 = streetname2;
        this.zipCd = zipCd;
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
    public Long getBillingId() {
        return billingId;
    }

    public void setBillingId(Long billingId) {
        this.billingId = billingId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name="EXPIRY_DATE")
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Column(name="SECRET_CODE")
    public String getSecretCode() {
        return secretCode;
    }

    public void setSecretCode(String secretCode) {
        this.secretCode = secretCode;
    }

    @Column(name="STATE_CD")
    public String getStateCd() {
        return stateCd;
    }

    public void setStateCd(String stateCd) {
        this.stateCd = stateCd;
    }

    public String getStreetname1() {
        return streetname1;
    }

    public void setStreetname1(String streetname1) {
        this.streetname1 = streetname1;
    }

    public String getStreetname2() {
        return streetname2;
    }

    public void setStreetname2(String streetname2) {
        this.streetname2 = streetname2;
    }

    @Column(name="ZIP_CD")
    public String getZipCd() {
        return zipCd;
    }

    public void setZipCd(String zipCd) {
        this.zipCd = zipCd;
    }
}

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
    private String cardType;
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

    public BillingInfo(String accountNo, Long billingId, String cardType,
                       String city, String country, String expiryDate,
                       String secretCode, String stateCd, String streetname1,
                       String streetname2, String zipCd) {
        this.accountNo = accountNo;
        this.billingId = billingId;
        this.cardType = cardType;
        this.city = city;
        this.country = country;
        this.expiryDate = expiryDate;
        this.secretCode = secretCode;
        this.stateCd = stateCd;
        this.streetname1 = streetname1;
        this.streetname2 = streetname2;
        this.zipCd = zipCd;
    }

    @Column(name="ACCOUNT_NO", length = 20)
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

    @Column(name="CARD_TYPE", length = 10)
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Column(length = 50)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(length = 50)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    @Column(name="STATE_CD", length = 10)
    public String getStateCd() {
        return stateCd;
    }

    public void setStateCd(String stateCd) {
        this.stateCd = stateCd;
    }

    @Column(length = 50)
    public String getStreetname1() {
        return streetname1;
    }

    public void setStreetname1(String streetname1) {
        this.streetname1 = streetname1;
    }

    @Column(length = 50)
    public String getStreetname2() {
        return streetname2;
    }

    public void setStreetname2(String streetname2) {
        this.streetname2 = streetname2;
    }

    @Column(name="ZIP_CD", length = 10)
    public String getZipCd() {
        return zipCd;
    }

    public void setZipCd(String zipCd) {
        this.zipCd = zipCd;
    }
}

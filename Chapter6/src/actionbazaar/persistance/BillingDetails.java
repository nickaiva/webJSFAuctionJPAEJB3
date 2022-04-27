package actionbazaar.persistance;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "BillingDetails.findAll", query = "select o from BillingDetails o")
})
@Table(name = "BILLING_DETAILS")
public class BillingDetails implements Serializable {
    @Column(name="ACCOUNT_NO")
    private String accountNo;
    @Id
    @Column(name="BILLING_ID", nullable = false)
    private Long billingId;
    private String city;
    private String country;
    @Column(name="EXPIRY_DATE")
    private String expiryDate;
    @Column(name="SECRET_CODE")
    private String secretCode;
    @Column(name="STATE_CD")
    private String stateCd;
    private String streetname1;
    private String streetname2;
    @Column(name="ZIP_CD")
    private String zipCd;
    @OneToMany(mappedBy = "billingDetails")
    private List<Order> orderList;

    public BillingDetails() {
    }

    public BillingDetails(String accountNo, Long billingId, String city,
                          String country, String expiryDate, String secretCode,
                          String stateCd, String streetname1,
                          String streetname2, String zipCd) {
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
        this.secretCode = secretCode;
    }

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

    public String getZipCd() {
        return zipCd;
    }

    public void setZipCd(String zipCd) {
        this.zipCd = zipCd;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Order addOrder(Order order) {
        getOrderList().add(order);
        order.setBillingDetails(this);
        return order;
    }

    public Order removeOrder(Order order) {
        getOrderList().remove(order);
        order.setBillingDetails(null);
        return order;
    }
}

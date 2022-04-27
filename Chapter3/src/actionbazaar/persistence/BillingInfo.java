package actionbazaar.persistence;

import java.io.Serializable;

public class BillingInfo implements Serializable {
  protected String accountNumber;

     protected String creditCardType;

     protected String expiryDate;
    public BillingInfo() {
        super();
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
}

package actionbazaar.buslogic;

import java.io.Serializable;

public class ShippingRequest implements Serializable {
  protected long item;
  protected String shippingAddress;
  protected String shippingMethod;
  protected double insuranceAmount;

    public ShippingRequest() {
        super();
    }

    public void setItem(long item) {
        this.item = item;
    }

    public long getItem() {
        return item;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setInsuranceAmount(double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public double getInsuranceAmount() {
        return insuranceAmount;
    }
}

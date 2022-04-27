package actionbazaar.buslogic;

import actionbazaar.persistence.BillingInfo;
import actionbazaar.persistence.ShippingInfo;

import javax.ejb.Remote;

@Remote
public interface PlaceOrder {
      void setBidder(String bidderId);
      void addItem(Long itemId);
      void setShippingInfo(ShippingInfo shippingInfo);
      void setBillingInfo(BillingInfo billingInfo);
      Long confirmOrder();
}

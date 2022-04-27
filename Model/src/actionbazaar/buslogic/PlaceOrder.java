package actionbazaar.buslogic;


import actionbazaar.persistence.BillingInfo;
import actionbazaar.persistence.Order;
import actionbazaar.persistence.ShippingInfo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;


@Remote
public interface PlaceOrder {
    
     void deleteOrder(Long orderId);

    Order mergeOrder(Order order);

    void setBidder(String bidderId);

    void addItem(Long itemId);

    void setShippingInfo(ShippingInfo shippingInfo);

    void setBillingInfo(BillingInfo billingInfo);

    Long confirmOrder();

    Long addOrder(String bidderId, ArrayList<Long> itemsId, Long shippingId,
                  Long billingId);

    public void setShippingInfo(Long shippingId);

    public void setBillingInfo(Long billingId);

    List<Order> getOrderFindByBidder(String bidderId);

    Order persistOrder(Order order);

    void removeOrder(Order order);
}

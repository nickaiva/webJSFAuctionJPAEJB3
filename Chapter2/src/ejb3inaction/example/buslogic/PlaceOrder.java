package ejb3inaction.example.buslogic;

import ejb3inaction.example.persistence.Bid;

import ejb3inaction.example.persistence.BillingInfo;
import ejb3inaction.example.persistence.ShippingInfo;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface PlaceOrder {
  void setBidderId(Long bidderId);


      void addItem(Long itemId);


      void setShippingInfo(ShippingInfo shippingInfo);


      void setBillingInfo(BillingInfo billingInfo);


      Long confirmOrder();

    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    Bid persistBid(Bid bid);

    Bid mergeBid(Bid bid);

    void removeBid(Bid bid);

    List<Bid> getBidFindAll();
}

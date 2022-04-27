package actionbazaar.buslogic;

import actionbazaar.persistence.Bid;

import javax.ejb.Remote;

@Remote
public interface PlaceBid {
  public Bid addBid(String bidderId, Long itemId, Double bidPrice);
}

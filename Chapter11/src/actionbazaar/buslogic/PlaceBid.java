package actionbazaar.buslogic;

import actionbazaar.persistence.Bid;

import java.util.Date;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface PlaceBid {
  public Bid addBid(String bidderId, Long itemId, Double bidPrice); 
  public List getBidsByDate(Date bidDate);
}

package ejb3inaction.example.buslogic;

import ejb3inaction.example.persistence.BidSDO;

import javax.ejb.Local;
import ejb3inaction.example.persistence.Bid;

import javax.ejb.Remote;

@Local
@Remote
public interface PlaceBid {
  public Bid addBid(Bid bid);

    BidSDO addBidSDO(BidSDO bidSDO) throws RuntimeException;
}

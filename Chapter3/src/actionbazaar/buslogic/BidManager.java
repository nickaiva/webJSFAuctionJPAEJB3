package actionbazaar.buslogic;

import actionbazaar.persistence.Bid;

import actionbazaar.persistence.Item;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface BidManager {
  Long addBid(Bid bid);


      void cancelBid(Bid bid);


      List<Bid> getBids(Item item);
}

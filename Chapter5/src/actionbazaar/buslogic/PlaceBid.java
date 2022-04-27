package actionbazaar.buslogic;

import javax.ejb.*;


@Remote
public interface PlaceBid {
  Long addBid(String userId, Long itemId, Double bidPrice);
}

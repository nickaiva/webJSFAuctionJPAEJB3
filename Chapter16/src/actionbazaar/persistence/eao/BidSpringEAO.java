package actionbazaar.persistence.eao;

import actionbazaar.buslogic.BidException;

import actionbazaar.persistence.Bid;
import actionbazaar.persistence.BidStatus;
import actionbazaar.persistence.Bidder;
import actionbazaar.persistence.Item;

public class BidSpringEAO extends BasicSpringEAO implements BidEAO {
    public BidSpringEAO() {
        super();
      System.out.println("Bid EAO Initialized ..");
    }

    public Bid addBid(Long itemId, String bidderId, double bidPrice) {
      System.out.println("Finding item:"+itemId);
            Bid bid = new Bid();
            Item item =  (Item)this.getJpaTemplate().find(Item.class,itemId);
            bid.setItem(item);
            bid.setBidPrice(bidPrice);
            bid.setBidStatus(BidStatus.NEW);
            System.out.println("Finding Bidder:"+bidderId);
            Bidder bidder =  (Bidder)getJpaTemplate().find(Bidder.class,bidderId);
            if (bidder == null)
             {
               throw new BidException("No such user with Id:"+bidderId);
              }

            System.out.println("Found Bidder:"+bidder.getFirstName());
            bid.setBidBidder(bidder);
            this.getJpaTemplate().persist(bid);
            System.out.println("Persisted Bid:");
            return bid;

    }

    public Bid cancelBid(Long bidId) {
        return null;
    }
}

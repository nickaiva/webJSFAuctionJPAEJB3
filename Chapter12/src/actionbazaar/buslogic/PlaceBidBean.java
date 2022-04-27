package actionbazaar.buslogic;

import actionbazaar.persistence.Bid;

import actionbazaar.persistence.Item;
import actionbazaar.persistence.eao.BidEAO;

import actionbazaar.persistence.eao.EAOFactory;
import actionbazaar.persistence.eao.ItemEAO;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.PersistenceContext;

@Stateless(name = "PlaceBid", mappedName = "ejb3inaction-Chapter12-PlaceBid")
@Remote
@PersistenceContext(unitName="Chapter12", name="actionBazaar")

public class PlaceBidBean implements PlaceBid {
    public PlaceBidBean() {
    }

    public Long addBid(String userId, Long itemId, Double bidPrice) {
              System.out.println("Bid for "+itemId+ " received with price"+bidPrice);
              ItemEAO itemEAO = EAOFactory.JPA.getItemEAO();
              Item item = itemEAO.findByItemId(itemId);
              
              if (item == null)
               {
                throw new BidException("Invalid Item Id:"+ itemId);
               }
              Bid highBid = itemEAO.findHighestBidForItem(item);
              
              if (highBid !=null)
              {
                System.out.println("Highest Bid Id:"+highBid.getBidId()+" Price:"+highBid.getBidPrice());
                System.out.println("Current Bid Price:"+bidPrice);
                if (bidPrice <= highBid.getBidPrice())
                    throw new BidException("Bid Price is lower than the current bid price"); 
              }
              
              BidEAO bidEAO = EAOFactory.JPA.getBidEAO();
              Bid bid = bidEAO.addBid(item, userId, bidPrice);
              return bid.getBidId();

              }

    
}

package actionbazaar.buslogic;

import actionbazaar.persistence.Bid;

import actionbazaar.persistence.Bidder;
import actionbazaar.persistence.Item;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

@Stateless(name = "PlaceBid", mappedName = "ejb3inaction-Chapter11-PlaceBid")
@Remote
public class PlaceBidBean implements PlaceBid {
  @PersistenceContext(unitName="Chapter11") 
     private EntityManager em;

    public PlaceBidBean() {
    }

    public Bid addBid(String bidderId, Long itemId, Double bidPrice) {
      //Find Bidder
              Bidder bidder = (Bidder) em.find(Bidder.class,bidderId);
           
              // Find Item
              Item item = (Item) em.find(Item.class,itemId);

              // Create new instance of Bid
              Bid newBid = new Bid();
              
               newBid.setBidPrice(bidPrice);
              
              // Set RelationShip
              newBid.setBidder(bidder);
              newBid.setItem(item); 

              //Persist Bid
              em.persist(newBid); 
              return newBid;     

    }

    public List getBidsByDate(Date bidDate) {
        Query query = em.createNamedQuery("Bid.getBidsByDate");
        query.setParameter("bidDate", bidDate, TemporalType.DATE);
        return query.getResultList();
    }
}

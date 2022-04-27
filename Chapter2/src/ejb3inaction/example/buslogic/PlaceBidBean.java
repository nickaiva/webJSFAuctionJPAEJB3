package ejb3inaction.example.buslogic;

import commonj.sdo.helper.DataFactory;

import commonj.sdo.helper.XSDHelper;

import ejb3inaction.example.persistence.Bid;

import ejb3inaction.example.persistence.BidSDO;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "PlaceBid", mappedName = "ejb3inaction-Chapter2-PlaceBid")
@Local
public class PlaceBidBean implements PlaceBid {
  @PersistenceContext(unitName = "actionBazaar")
    private EntityManager em;
    public PlaceBidBean() {
    }
  public Bid addBid(Bid bid) {
    System.out.println("Adding bid, bidder ID=" +
                        bid.getBidBidder()
                        + ", item ID=" + bid.getBidItemId() + ",bid amount="
                        + bid.getBidPrice() + ".");
    return save(bid);
    }
  private Bid save(Bid bid) {
       
        em.persist(bid);
          System.out.println("Your bid your item id:" + bid.getBidItemId()
                  + "was successful");
          System.out.println("Your bid id is: " + bid.getBidId());
          return bid;
      }

    private Bid unMarshallBid(BidSDO bidSDO) {
        if (bidSDO == null)
            return null;
        Bid bid = new Bid();
        bid.setBidBidder(bidSDO.getBidBidder());
        bid.setBidDate(bidSDO.getBidDate());
        bid.setBidId(bidSDO.getBidId());
        bid.setBidItemId(bidSDO.getBidItemId());
        bid.setBidPrice(bidSDO.getBidPrice());
        bid.setBidStatus(bidSDO.getBidStatus());
        return bid;
    }

    private BidSDO marshallBid(Bid bid) {
        if (bid == null)
            return null;
        BidSDO bidSDO = ( BidSDO )DataFactory.INSTANCE.create(BidSDO.class);
        bidSDO.setBidBidder(bid.getBidBidder());
        bidSDO.setBidDate(bid.getBidDate());
        bidSDO.setBidId(bid.getBidId());
        bidSDO.setBidItemId(bid.getBidItemId());
        bidSDO.setBidPrice(bid.getBidPrice());
        bidSDO.setBidStatus(bid.getBidStatus());
        return bidSDO;
    }

    public BidSDO addBidSDO(BidSDO bidSDO) throws RuntimeException {
        Bid bid = unMarshallBid(bidSDO);
        return marshallBid(addBid(bid));
    }
    static {
        synchronized (PlaceBidBean.class) {
            try {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                XSDHelper.INSTANCE.define(loader.getResourceAsStream("ejb3inaction/example/persistence/BidSDO.xsd"), "ejb3inaction/example/persistence/");
                XSDHelper.INSTANCE.define(loader.getResourceAsStream("ejb3inaction/example/buslogic/PlaceBidBeanWS.xsd"), "ejb3inaction/example/buslogic/");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

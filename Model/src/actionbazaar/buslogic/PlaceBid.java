package actionbazaar.buslogic;


import actionbazaar.buslogic.exceptions.CustomException;

import actionbazaar.persistence.Bid;
import actionbazaar.persistence.BidStatus;

import java.sql.Date;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface PlaceBid {
  
  public List<Bid> getBidsByBidderId(String userId);

  public Long addBid(String bidderId, Long itemId, Double bidPrice) throws CustomException; 

   public List<Bid> BidsByDate(Date bidDate);

    Bid mergeBid(Bid bid);
  
    void cancelBid(Long bidId);
    
    public void withdrawlBid( Long bidId);
    
    public List<Bid> getBidFindAll();
  
    public void deleteBid(Long bidId);
    
     Bid determineWinnerBidforItem(Long itemId) throws CustomException;

    Bid persistBid(Bid bid);

    void removeBid(Bid bid);

    List<Bid> getBidGetBidsByDate(Date bidDate);

    List<Bid> getBidGetBidsById(Long bidId);

    List<Bid> getBidGetBidsByStatus(BidStatus bidStatus);

    List<Bid> getBidGetBidsItemByDate(Date bidDate);

    List<Bid> getBidGetWinnerBidByItemId(Long itemId);

    List<Bid> getBidGetBidsBidderByDate(Date bidDate);
}

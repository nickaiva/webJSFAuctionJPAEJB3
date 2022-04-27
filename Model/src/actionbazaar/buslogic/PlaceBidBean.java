package actionbazaar.buslogic;


import actionbazaar.buslogic.exceptions.CustomException;

import actionbazaar.persistence.Bid;
import actionbazaar.persistence.BidStatus;
import actionbazaar.persistence.Bidder;
import actionbazaar.persistence.Item;

import java.sql.Date;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.Query;


@Stateless(name = "PlaceBid", mappedName = "ejb3inaction-Model-PlaceBid")
@Remote
@Local
@DeclareRoles(value = { "BIDDER", "ADMINISTRATOR"})
public class PlaceBidBean extends CustomBean implements PlaceBid, PlaceBidLocal {
 
  @Resource 
  TimerService timerService;
 
  

  public PlaceBidBean() {   }
    
  @Timeout
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
  public void monitorBid(Timer timer) throws ClassCastException {
      try {
          Bid bid = (Bid) timer.getInfo();
          int   size =  em.createNamedQuery("Bid.getWinnerBidByItemId").setParameter("itemId",bid.getItem().getItemId()).getResultList().size();
          if (logger.isDebugEnabled())
                logger.debug("Top price bids  resultSet size: " + size);
        
         if (bid.getBidStatus() == BidStatus.NEW      && size == 1 ) {
                    if (logger.isDebugEnabled())
                        logger.debug("Winner  bid: " + bid.getBidId() );
                   
                   bid.setBidStatus(BidStatus.WINNER); 
                   em.merge(bid);
                   //Not em.persist(bid); which is only for inserting new records!
                   /*Remove item from further bidding*/
                   Item item = bid.getItem();
                   Calendar endDate =Calendar.getInstance()   ;
                   java.sql.Timestamp  bidEndDate =   new java.sql.Timestamp(endDate.getTimeInMillis());
                   item.setBidEndDate(bidEndDate);
                   em.merge(item);
                  // em.flush();
                   //timer.cancel();
         } else {
                      if (logger.isDebugEnabled())
                            logger.debug("Cancelled  bidId: " + bid.getBidId() );
                    bid.setBidStatus(BidStatus.CANCELLED);    
                    em.merge(bid);
                    //timer.cancel();
            }
            
         
         if (bid.getBidStatus() == BidStatus.CANCELLED || bid.getBidStatus() == BidStatus.WITHDRAWN ) {
               if (logger.isDebugEnabled())
                   logger.debug("Deleting  bid: " + bid.getBidId());
                em.remove( em.merge(bid));
                //em.flush();
                //timer.cancel();
            }
      }  catch (Exception e){
           logger.info(" Caught exception" + e.getMessage());
           context.setRollbackOnly();
          }
  }
  
  @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public Long addBid(String bidderId, Long itemId, Double bidPrice) throws CustomException  {
                    
                    if (logger.isDebugEnabled())
                          logger.debug("bidderId " + bidderId);
                    //Find Bidder
                    Bidder bidder = em.find(Bidder.class,bidderId);

                    // Find Item
                    Item item = em.find(Item.class,itemId);
                    Calendar curentTimestamp =Calendar.getInstance();
                    java.sql.Timestamp  currentDate =   new java.sql.Timestamp(curentTimestamp.getTimeInMillis());
                    
                     // Check whether there is at least a bid first 
                    int   size =  em.createNamedQuery("Bid.getWinnerBidByItemId").setParameter("itemId",itemId).getResultList().size();               
                    Bid bid = new Bid() ;
                    /*Typical current date for DB inserts */
                    java.util.Date today =   new java.util.Date();
                    java.sql.Date bidDate =   new java.sql.Date(today.getTime());
                    bid.setBidStatus(BidStatus.NEW);
                    // Set RelationShip
                    bid.setBidBidder(bidder);
                    bid.setItem(item);
                    // check if item date has expired or it has already been won 
                   if (item.getBidEndDate().compareTo(currentDate) > 0 ) {
                          bid.setBidDate(bidDate);
                           switch ( size ) {
                          //This is the first bid ever,thus the initial price is compared to the bid price
                              case 0:
                                   if( item.getInitialPrice().compareTo(bidPrice) < 0) {
                                          bid.setBidPrice(bidPrice);
                         /*to avoid PK seq generation exceptions increase sequence cache size to 1000 for production DB*/
                                     em.persist(bid); 
                                } else {
                                          if (logger.isDebugEnabled())
                                                   logger.debug("New bid price: "+ bidPrice +" is equal or lower to initial: " + item.getInitialPrice() );
                                         context.setRollbackOnly();
                                        // throw new BidValidationException("New bid price: "+ bidPrice +" is equal or lower to initial: " + item.getInitialPrice());
                                        throw new CustomException("00003",
                                                                  new String[] { "FifthParameter", "SixthParameter" });
                           }  // End of check whether new price is equal or lower than the initial
                                 
                         break;
                     //Next the current bid price is compared to the new bid price
                          case 1://NPE is thrown below when no previous bid exists
                                Bid CurrentWinningBid =
                                         (Bid)em.createNamedQuery("Bid.getWinnerBidByItemId").setParameter("itemId",itemId).getResultList().get(0);
                               // check whether new price is equal or lower than the current
                               Double currentBidPrice = CurrentWinningBid.getBidPrice() !=null ?
                                                                 CurrentWinningBid.getBidPrice() : item.getInitialPrice();
                               if (logger.isDebugEnabled())
                                   logger.debug("currentBidPrice:" + currentBidPrice);     
                               if( currentBidPrice.compareTo(bidPrice) < 0) {
                                              bid.setBidPrice(bidPrice);
                                               em.persist(bid); 
                                                //em.flush();
                               } else {
                                     if (logger.isDebugEnabled())
                                           logger.debug("New price: "+ bidPrice +" is equal or lower to current: " + currentBidPrice );
                                       context.setRollbackOnly();
                                       //throw new BidValidationException("New price: "+ bidPrice +" is equal or lower to current: " + currentBidPrice);
                                       throw new CustomException("00003",
                                                                 new String[] { "FifthParameter", "SixthParameter" });
                                   }  // End of check whether new price is equal or lower than the current               
                                break;
                      
                        default:
                         // more than 1 wiinning  bids, at present is out of the question!
                        break;
                     }// end of switch
        } else {
                  if (logger.isDebugEnabled())
                        logger.debug("Item " + item.getItemId() +" not available for further bids!");
                    context.setRollbackOnly();
                    //throw new BidValidationException("Item " + item.getItemName() +" not available for further bids!"); 
                    throw new CustomException("00004",
                                              new String[] { "FirstParameter", "" });
        }  //End of  check if item has already been won 
                   
                            
        return  bid.getBidId();
      }


    /** <code>SELECT b FROM Bid b  WHERE b.bidDate <= :bidDate</code> */
    public List<Bid> BidsByDate(Date bidDate) {
        return em.createNamedQuery("Bid.getBidsByDate").setParameter("bidDate", bidDate).getResultList();
    }

    public Bid mergeBid(Bid bid) {
        return em.merge(bid);
    }

    /** <code>select o from Bid o</code> */
    public List<Bid> getBidFindAll() {
        return em.createNamedQuery("Bid.findAll").getResultList();
    }

  /*Called by bid admin only to end biddding*/
    @RolesAllowed(value = {"CSR", "ADMINISTRATOR"})
   public  Bid determineWinnerBidforItem(Long itemId) throws CustomException {
      
        List<Bid>  bids =  em.createNamedQuery("Bid.getWinnerBidByItemId").setParameter("itemId",itemId).getResultList();
        if (bids.size() > 0) {
                    for (Bid bid : bids) {// 2* 60 seconds
                        timerService.createTimer(1*60*1000,bid); 
                    }
                     return bids.get(0);
        } else {
            if (logger.isDebugEnabled())
                logger.debug("No bids exists for this item yet!");
                throw new CustomException("00001", new String[] { "FirstParameter", "SecondParameter" });//"No bids exist yet for this item!"
            }
    }

    
  /*Called by  admin only*/
    @RolesAllowed(value = {"ADMINISTRATOR"})
    public void cancelBid( Long bidId) {
        Bid  bid = em.find(Bid.class,bidId);
         if (logger.isDebugEnabled())
            logger.debug("Cancelling bidId: " + bid.getBidId());
        if (bid != null && bid.getBidStatus() != BidStatus.CANCELLED){
          //Setup timer to fire   after 1 minutes
             timerService.createTimer(1*60*1000, bid);//miliseconds
             bid.setBidStatus(BidStatus.CANCELLED);
             em.merge(bid);
             em.persist(bid);
             //em.flush();
        }
    }
/*Called by bidder*/
    @RolesAllowed(value = {"BIDDER"})
  public void withdrawlBid( Long bidId) {
      Bid  bid = em.find(Bid.class,bidId);
      if (logger.isDebugEnabled())
          logger.debug("Withdrawing bidId: " + bid.getBidId());
      if (bid != null && bid.getBidStatus() != BidStatus.WITHDRAWN) {
          //Setup timer to fire  after 1 minutes
          timerService.createTimer(1*60*1000,  bid);
          bid.setBidStatus(BidStatus.WITHDRAWN);
          em.merge(bid);
          em.persist(bid);
          //em.flush();
      } 
  }


    public Bid persistBid(Bid bid) {
        em.persist(bid);
        return bid;
    }

    /**
     * @param bidId
     */
    @RolesAllowed(value = {"ADMINISTRATOR"})
    public void deleteBid(Long bidId) {
        Bid bid = em.find(Bid.class,bidId);
        em.remove(bid);
    }
    
    @RolesAllowed(value = {"ADMINISTRATOR"})
    public void removeBid(Bid bid) {
        bid = em.find(Bid.class, bid.getBidId());
        em.remove(bid);
    }

    /** <code>SELECT b FROM Bid b  WHERE b.bidDate <= :bidDate ORDER BY  b.bidDate DESC</code> */
     @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Bid> getBidGetBidsByDate(Date bidDate) {
        Query q =  em.createNamedQuery("Bid.getBidsByDate").setParameter("bidDate", bidDate);
        q.setFirstResult(0);
        q.setMaxResults(10);
        return q.getResultList();
    }

    /** <code>SELECT b FROM Bid b WHERE b.bidId = :bidId </code> */
    public List<Bid> getBidGetBidsById(Long bidId) {
        return em.createNamedQuery("Bid.getBidsById").setParameter("bidId", bidId).getResultList();
    }

    /** <code>SELECT b FROM Bid b  WHERE b.bidStatus = :bidStatus ORDER BY  b.bidDate DESC</code> */
    public List<Bid> getBidGetBidsByStatus(BidStatus bidStatus) {
        return em.createNamedQuery("Bid.getBidsByStatus").setParameter("bidStatus", bidStatus).getResultList();
    }

    /** <code>SELECT b  FROM Bid b   JOIN FETCH b.item  WHERE b.bidDate <= :bidDate ORDER BY  b.bidDate DESC</code> */
    public List<Bid> getBidGetBidsItemByDate(Date bidDate) {
        return em.createNamedQuery("Bid.getBidsItemByDate").setParameter("bidDate", bidDate).getResultList();
    }

    /** <code>SELECT b  FROM Bid b   JOIN FETCH b.item  WHERE b.bidPrice =( SELECT MAX(c.bidPrice)  FROM Bid c  WHERE c.item.itemId = :itemId  AND  c.item.itemId = b.item.itemId )  ORDER BY  b.bidDate ASC</code> */
    public List<Bid> getBidGetWinnerBidByItemId(Long itemId) {
        return em.createNamedQuery("Bid.getWinnerBidByItemId").setParameter("itemId", itemId).getResultList();
    }

    /** <code>SELECT b FROM Bid b    JOIN FETCH b.bidBidder   WHERE b.bidDate <= :bidDate ORDER BY  b.bidDate DESC</code> */
    public List<Bid> getBidGetBidsBidderByDate(Date bidDate) {
        Query q = em.createNamedQuery("Bid.getBidsBidderByDate").setParameter("bidDate", bidDate);
        q.setFirstResult(0);
        q.setMaxResults(10);
        return q.getResultList();
    }
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Bid> getBidsByBidderId(String userId) {
              String currentUserId = context.getCallerPrincipal().getName();
              if (logger.isDebugEnabled())
                logger.debug("IS CURRENT USER " + currentUserId + "  ADMIN? " + context.isCallerInRole("ADMINISTRATOR"));
                    
              if (context.isCallerInRole("ADMINISTRATOR") ) {
              /* context.isCallerInRole("ADMINISTRATOR") returns false if roles  not declared in application.xml*/
                return em.createNamedQuery("Bid.getBidsByBidderId").setParameter("userId", userId).getResultList();
               
              } else{
                 return em.createNamedQuery("Bid.getBidsByBidderId").setParameter("userId", currentUserId).getResultList();
                      
            }
    }  
}

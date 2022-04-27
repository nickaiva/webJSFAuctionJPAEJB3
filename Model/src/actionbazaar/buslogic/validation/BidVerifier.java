package actionbazaar.buslogic.validation;


import actionbazaar.buslogic.exceptions.CustomException;

import actionbazaar.persistence.Bid;
import actionbazaar.persistence.BidderStatus;

import javax.persistence.PrePersist;

import org.apache.log4j.Logger;


public class BidVerifier {
    
    private Logger logger =  Logger.getLogger(this.getClass().getName());

    public BidVerifier() {
        super();
    }
    
  @PrePersist
  public void newBidVerifier(Bid bid)  throws CustomException {

       if (bid.getBidBidder().getBidderStatus() == BidderStatus.INACTIVE) {
           if (logger.isDebugEnabled())
               logger.debug("Bidder " + bid.getBidBidder().getUserId() +
                            " status is INACTIVE!");
           /*throw new BidValidationException("00002",
                                     new String[] {  bid.getBidBidder().getUserId(),  BidderStatus.INACTIVE.toString() });*/
           throw new CustomException("00002",
                                     new String[] { "ThirdParameter", "FourthParameter" });

       }
                 
            if (bid.getBidPrice() <= bid.getItem().getInitialPrice()) {
                if (logger.isDebugEnabled())
                    logger.debug("Bid price " + bid.getBidPrice() +
                                 "  is equal to or lower than initial price " +
                                 bid.getItem().getInitialPrice());
                throw new CustomException("00003",
                                          new String[] { "FifthParameter", "SixthParameter" });
                /*throw new BidValidationException("Bid price: " +
                                                 bid.getBidPrice() +
                                                 "  is equal to or lower than initial price: " +
                                                 bid.getItem().getInitialPrice());*/

            }

          
        
        
   }
}

package actionbazaar.buslogic.client;


import actionbazaar.buslogic.PlaceBid;

import actionbazaar.persistence.Bid;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;


public class PlaceBidClient {

    private static Logger logger = Logger.getLogger("PlaceBidClient");

    public static void main(String [] args) {
        try {
                    final Context context = getInitialContext(args[0],args[1]);
                    PlaceBid placeBid = (PlaceBid)context.lookup("ejb3inaction-Model-PlaceBid#actionbazaar.buslogic.PlaceBid");
                   /* create new bid*/
                    Long bidId = placeBid.addBid("DonaldSmithBidder",  13002L,  505d);//NickAivaBidder
                        if (logger.isDebugEnabled())
                              logger.debug("Bid Successful, BidId Received is:" + bidId); 
                  String userId ="NickAivaBidder";
                       //placeBid.cancelBid(bid.getBidId());
                     /*Either cancel or withdraw a bid, not both! */
                     // placeBid.withdrawlBid(bid.getBidId());
                    //assert(bid.getBidId() == 502):"BidId Received is "+bid.getBidId();
          /*           if (logger.isDebugEnabled())
                        logger.debug("Finding previous bids by  date..");
                     java.util.Date today =  new java.util.Date();
                     java.sql.Date currDate =   new java.sql.Date(today.getTime());
                    if (logger.isDebugEnabled())
                            logger.debug("currDate "+ currDate);
            */
                  /*finalise Auction For Item*/
            /*       Bid winner =  placeBid.determineWinnerBidforItem(351L);
                 logger.debug("Winning bid for item " + winner.getItem().getItemId() + " is " + winner.getBidId());
            */
              /*        for (Bid bid1 :( List <Bid>)    placeBid.determineWinnerBidforItem(1L)) {
                           logger.debug("Id:"+bid1.getBidId()+" Bidder userId: "+bid1.getBidBidder().getUserId() + " status: " + bid1.getBidStatus()) ;
                    }*/
               
           /*     for (Bid bid1 :( List <Bid>)  placeBid.BidsByDate(currDate)) {
                        logger.debug("Id:"+bid1.getBidId()+" Bidder userId: "+bid1.getBidBidder().getUserId() + " status: " + bid1.getBidStatus()) ;
                     }
            */
                                 
           
           for (Bid bid2 : (List<Bid>)placeBid.getBidsByBidderId(userId)) {
                 printBid(bid2);
                }
                 
        } catch (Exception ex) {
            logger.debug("Error: " +ex.getMessage());
            ex.printStackTrace();
        }
    }
 
  private static void printBid(Bid bid) {
      System.out.println( "bidBidder = " + bid.getBidBidder().getUserId() );
      System.out.println( "bidDate = " + bid.getBidDate() );
      System.out.println( "bidId = " + bid.getBidId() );
      System.out.println( "bidPrice = " + bid.getBidPrice() );
      System.out.println( "bidStatus = " + bid.getBidStatus() );
      System.out.println( "item = " + bid.getItem().getItemName() );
  }
  private static Context getInitialContext(String user, String password) throws NamingException {
      Hashtable env = new Hashtable();
      // WebLogic Server 10.x connection details
      env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
      env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
      env.put(Context.SECURITY_PRINCIPAL, user);
      env.put(Context.SECURITY_CREDENTIALS,password);
      return new InitialContext( env );
  }
 

}

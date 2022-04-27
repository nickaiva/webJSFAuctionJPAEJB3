package actionbazaar.buslogic;

import java.sql.Connection;
import java.sql.SQLException;

import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;

import javax.ejb.TimerService;

import javax.sql.DataSource;
/*===============================================
JarScan
written by Geoff Yaworski
gyaworski@hotmail.com
Found: Interceptors
Class: javax.interceptor.Interceptors
Package: javax.interceptor
Library Name: javax.interceptor_1.0.jar
Library Path: C:\Oracle\Middleware\jdev_11gR1\modules\javax.interceptor_1.0.jar
===============================================
*/
import javax.interceptor.Interceptors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless(name = "PlaceBid", mappedName = "ejb3inaction-Chapter5-PlaceBid")
@Interceptors( { actionbazaar.buslogic.ActionBazaarProfilingInterceptor.class })
@Remote
public class PlaceBidBean implements PlaceBid {
    
  private DataSource ds;
  private Connection con;
  @Resource
  private SessionContext sc;
  private Logger logger;

      //@Resource(name = "jdbc/ActionBazaarDS", mappedName="ActionBazaarDS")
      public void setDs(DataSource ds) {
          this.ds = ds;   
      }

      public DataSource getDs() {
          return this.ds;
      }

      @PostConstruct
      public void initialize() {
          try {
            Context ctx =new InitialContext();
            ds=(DataSource)ctx.lookup("jdbc/ActionBazaarDS");
            con = ds.getConnection();
            logger= Logger.getAnonymousLogger();
            logger.setLevel(Level.ALL);  
          } 
          catch (SQLException e) {
              e.printStackTrace();
         } catch (NamingException e) {
            e.printStackTrace(); 
        }
    }

      @Interceptors(actionbazaar.buslogic.DiscountVerifierInterceptor.class)
      public Long addBid(String userId, Long itemId, Double bidPrice) {
          logger.info("Bid for " + itemId + " received with price"
                  + bidPrice);
          Long bidId = getBidId();
          createBid(userId, itemId, bidPrice, bidId);
          return bidId;
      }

      private void createBid(String userId, Long itemId, Double bidPrice,
              Long bidId) {
          try {
              Statement stmt = con.createStatement();
              stmt.execute("INSERT INTO BIDS(BID_ID,BID_BIDDER,BID_ITEM_ID,BID_PRICE) VALUES("
                              + getBidId()
                              + ",\'"
                              + userId
                              + "\',"
                              + itemId
                              + "," + bidPrice + ")");
              TimerService ts = sc.getTimerService();
              // Created a single event timer that expires after half hour
              //Timer timer = ts.createTimer(1800000, bidId);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }


      private Long getBidId() {
          // Add Code for generating a unique key
          Long bidId = Long.valueOf(1008);
          return bidId;
      }


      @PreDestroy
      public void cleanup() {
          try {
              con.close();
              con = null;
          } catch (SQLException e) {
              e.printStackTrace();
          }
      }


      @Timeout
      public void sendBidInfo(Timer timer) {
          logger.info("BidManager EJB: Monitor status of BidId:"
                  + timer.getInfo());
          // Implement Your Business Logic Here to monitor the status for bidId
          // and send email to the bidder
          return;
      }

}

package actionbazaar.buslogic;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import actionbazaar.persistence.Bid;
import actionbazaar.persistence.Item;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.sql.DataSource;

@Stateless(name = "BidManager", mappedName = "ejb3inaction-Chapter3-BidManager")
@Remote
public class BidManagerBean implements BidManager {
    public BidManagerBean() {
    }
  private Connection connection;
 // @Resource(name = "jdbc/ActionBazaarDS", mappedName="ActionBazaarDS")
    private DataSource dataSource;


    @PostConstruct
    public void initialize() {
        try {
          Context context = new InitialContext();
          DataSource dataSource =
          (DataSource)context.lookup("jdbc/ActionBazaarDS");// java:comp/env/jdbc/ActionBazaarDS
            connection = dataSource.getConnection();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }


    public Long addBid(Bid bid) {

        Long bidId = getBidId();
        try {

            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO BIDS(BID_ID,BID_BIDDER,BID_ITEM_ID,BID_PRICE) VALUES("
                            + bidId
                            + ",\'"
                            + bid.getBidder().getUserId()
                            + "\',"
                            + bid.getItem().getItemId()
                            + ","
                            + bid.getBidPrice() + ")");
            
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
        return bidId;
    }


    private Long getBidId() {
        // Add Code for generating a unique key
        return Long.valueOf(1003);
    }


    public void cancelBid(Bid bid) {
    }


    public List<Bid> getBids(Item item) {
        return item.getBids();
    }


    @PreDestroy
    public void cleanup() {
        try {
            //connection.commit();
            connection.close();
            connection = null;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}

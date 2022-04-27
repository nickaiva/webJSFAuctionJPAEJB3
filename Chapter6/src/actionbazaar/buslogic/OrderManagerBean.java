package actionbazaar.buslogic;

import actionbazaar.buslogic.exceptions.CreditProcessingException;
import actionbazaar.buslogic.exceptions.CreditValidationException;

import actionbazaar.buslogic.exceptions.DatabaseException;

import actionbazaar.persistance.Bidder;
import actionbazaar.persistance.Item;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

@Stateless(name = "OrderManager", mappedName = "ejb3inaction-Chapter6-OrderManager")
@Remote
public class OrderManagerBean implements OrderManager {
   private DataSource ds;
   private Connection con;
  
  @Resource
  private SessionContext context;
   
    public OrderManagerBean() {
    }
    
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
      } 
      catch (SQLException e) {
          e.printStackTrace();
     } catch (NamingException e) {
        e.printStackTrace(); 
    }
  }

    
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void placeSnagItOrder(Item item, Bidder bidder) {
      
      try {
            if (!bidsExisting(item)) {
                validateCredit(bidder);
                chargeBidder(bidder, item);
                removeItemFromBidding(item);
            } else{
             System.out.println("Previous bid was found, snagging not allowed!");
             return;   
             }
        } catch (CreditValidationException cve) {
          context.setRollbackOnly();
      } catch (CreditProcessingException cpe){
          context.setRollbackOnly();
      } catch (DatabaseException de) {
         context.setRollbackOnly();
      }
      catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    
    }

    public boolean bidsExisting(Item item) throws CreditProcessingException {
        
      Statement stmt;
      long itemId =item.getItemId();
      int count = 0 ;
        try {
            stmt = con.createStatement();
            String query= "SELECT COUNT(*) FROM BIDS WHERE BID_ITEM_ID = " +itemId;
            System.out.println(query);
            ResultSet res = stmt.executeQuery(query);
          while (res.next()){
                    count = res.getInt(1);
                  }
          System.out.println("Number of bids: "+ count);
            if (count != 0){
                 return true;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void validateCredit(Bidder bidder) throws CreditValidationException{
    //check expiry date instead
      String cctype =   bidder.getCreditCardType();
      if ( !cctype.equalsIgnoreCase("MASTERCARD")){
            try {
                throw new CreditValidationException();
                } catch (Exception e) {
              e.printStackTrace();
              System.out.println("validateCredit failed!");
            }
        } 
    }

    public void chargeBidder(Bidder bidder, Item item) {
      /*Shipping info and billing info records must be inserted beforehand*/
      Statement statement;
        try {
            statement = con.createStatement();
            String billingDetailsCommand ="INSERT INTO BILLING_DETAILS (BILLING_ID,ACCOUNT_NO,SECRET_CODE,EXPIRY_DATE,STREETNAME1,STREETNAME2,COUNTRY,CITY,STATE_CD,ZIP_CD) VALUES("+
                                          item.getItemId()+
                                          ",\'"+""+"\'"+
                                          ",\'"+""+"\'"+
                                          ",\'"+""+"\'"+
                                          ",\'"+""+"\'"+
                                          ",\'"+""+"\'"+
                                          ",\'"+""+"\'"+
                                          ",\'"+""+"\'"+
                                          ",\'"+""+"\'"+
                                          ",\'"+""+"\'"
                                          +")";
          System.out.println(billingDetailsCommand);
          statement.execute(billingDetailsCommand);
            String shippingInfoCommand="INSERT INTO SHIPPING_INFO(SHIPPING_ID,STREET,CITY,STATE) VALUES("+
                                       item.getItemId()+
                                      ",\'"+""+"\'"+
                                      ",\'"+""+"\'"+
                                      ",\'"+""+"\'"+
                                       ")";
          System.out.println(shippingInfoCommand);
          statement.execute(shippingInfoCommand);
            
            String command = "INSERT INTO ORDERS(ORDER_ID,ORDER_STATUS,ORDER_BIDDER_ID,ORDER_BILLING_ID,ORDER_SHIPPING_ID) VALUES("
                        + item.getItemId()
                        + ",\'"
                        +"BILLED"
                        + "\',"
                        +"\'"     
                        + bidder.getUsername()
                        + "\',"
                        + item.getItemId()+","
                        + item.getItemId()
                        + ")";
            System.out.println(command);
            statement.execute(command);
          
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("chargeBidder failed!");
        }
        
      
    }

    public void removeItemFromBidding(Item item) {
     long itemId =item.getItemId();
     // delete from bids where BID_ITEM_ID = id instead
     Statement stmt;
        try {
            stmt = con.createStatement();
            stmt.execute("DELETE FROM ITEMS WHERE ITEM_ID = "+
                        itemId
                        );
            System.out.println("removeItemFromBidding succeeded!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("removeItemFromBidding failed!");
        }
        
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

}

package actionbazaar.buslogic;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.sql.DataSource;
import actionbazaar.persistence.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateful(name = "BidderAccountCreator", mappedName = "ejb3inaction-Chapter3-BidderAccountCreator")
@Remote
public class BidderAccountCreatorBean implements BidderAccountCreator {
  //@Resource(name = "jdbc/ActionBazaarDS", mappedName="ActionBazaarDS")
     private DataSource dataSource;

     private LoginInfo loginInfo;

     private BiographicalInfo biographicalInfo;

     private BillingInfo billingInfo;

     private Connection connection;

    public BidderAccountCreatorBean() {
    }
    
  @PostConstruct
     @PostActivate
     public void openConnection()   {
         try {
           Context context = new InitialContext();
           DataSource dataSource =
           (DataSource)context.lookup("jdbc/ActionBazaarDS");//	java:comp/env/jdbc/ActionBazaarDS
             connection = dataSource.getConnection();
             System.out.println("Succedded connection");
         } catch (SQLException sqle) {
             sqle.printStackTrace();
             } catch(NamingException NE){
               NE.printStackTrace();
             }
     }


     public void addLoginInfo(LoginInfo loginInfo) {
         this.loginInfo = loginInfo;
     }


     public void addBiographicalInfo(BiographicalInfo biographicalInfo)
             throws WorkflowOrderViolationException {
         if (loginInfo == null) {
             throw new WorkflowOrderViolationException(
                     "Login info must be set before biographical info");
         }

         this.biographicalInfo = biographicalInfo;
     }


     public void addBillingInfo(BillingInfo billingInfo)
             throws WorkflowOrderViolationException {
         if (biographicalInfo == null) {
             throw new WorkflowOrderViolationException(
                     "Biographical info must be set before billing info");
         }

         this.billingInfo = billingInfo;
     }


     @PrePassivate
     @PreDestroy
     public void cleanup() {
         try {
             connection.close();
             connection = null;
         } catch (SQLException sqle) {
             sqle.printStackTrace();
         }
     }


     @Remove
     public void cancelAccountCreation() {
         loginInfo = null;
         biographicalInfo = null;
         billingInfo = null;
     }


     @Remove
     public void createAccount() {
         try {
             Statement statement = connection.createStatement();
             String sql = "INSERT INTO BIDDERS(" + "username, " + "first_name, "
                     + "credit_card_type" + ") VALUES (" + "'"
                     + loginInfo.getUsername() + "', " +

                     "'" + biographicalInfo.getFirstName() + "', " +

                     "'" + billingInfo.getCreditCardType() + "'" +

                     ")";
             statement.execute(sql);
             statement.close();
             System.out.println("Bidder successfully created ..");
         } catch (SQLException sqle) {
             sqle.printStackTrace();
         }
     }
}

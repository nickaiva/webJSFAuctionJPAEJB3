package actionbazaar.buslogic;

import actionbazaar.persistence.BillingInfo;
import actionbazaar.persistence.BiographicalInfo;
import actionbazaar.persistence.LoginInfo;

import javax.ejb.Remote;

@Remote
public interface BidderAccountCreator {

  void addLoginInfo(LoginInfo loginInfo);


     void addBiographicalInfo(BiographicalInfo biographicalInfo)
             throws WorkflowOrderViolationException;


     void addBillingInfo(BillingInfo billingInfo)
             throws WorkflowOrderViolationException;


     void cancelAccountCreation();


     void createAccount();
}

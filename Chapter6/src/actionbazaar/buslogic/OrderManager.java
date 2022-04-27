package actionbazaar.buslogic;

import actionbazaar.buslogic.exceptions.CreditProcessingException;

import actionbazaar.buslogic.exceptions.CreditValidationException;

import actionbazaar.persistance.Bidder;
import actionbazaar.persistance.Item;

import javax.ejb.Remote;

@Remote
public interface OrderManager {

  public void placeSnagItOrder(Item item, Bidder bidder);
  public boolean bidsExisting( Item item) throws CreditProcessingException ;
  public void validateCredit(Bidder bidder) throws CreditValidationException;
  public void chargeBidder(Bidder bidder,Item item);
  public void removeItemFromBidding(Item item);
  
}

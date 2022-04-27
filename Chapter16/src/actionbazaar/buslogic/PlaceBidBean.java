package actionbazaar.buslogic;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import javax.persistence.PersistenceContext;

import org.springframework.ejb.support.AbstractStatelessSessionBean;

@PersistenceContext(unitName="Chapter16",name="actionBazaar")

@Stateless(name = "PlaceBid", mappedName = "ejb3inaction-Chapter16-PlaceBid")
@Remote
public class PlaceBidBean extends AbstractStatelessSessionBean implements PlaceBid {
    
  BidServiceBean bidService;

      protected void onEjbCreate() {
         bidService = (BidServiceBean)  getBeanFactory().getBean("bidService");
       }

  public Long addBid(String userId, Long itemId, Double bidPrice) {
                 return bidService.addBid(userId, itemId, bidPrice);

         }

    
    public PlaceBidBean() {
    }
}

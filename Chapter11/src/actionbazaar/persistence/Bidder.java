package actionbazaar.persistence;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
  @NamedQuery(name = "Bidder.findAll", query = "select o from Bidder o")
})
@Inheritance
@DiscriminatorValue(value="B")
public class Bidder extends User implements Serializable {
    
  private BidderStatus bidderStatus;
  private Long creditRating;
  private Set<Bid> bids; 
    
    public Bidder() {
    }

    public void setBidderStatus(BidderStatus bidderStatus) {
        this.bidderStatus = bidderStatus;
    }
  @Column(name="BIDDER_STATUS")
  @Enumerated(EnumType.STRING)
    public BidderStatus getBidderStatus() {
        return bidderStatus;
    }

    public void setCreditRating(Long creditRating) {
        this.creditRating = creditRating;
    }
  @Column(name="CREDIT_RATING")
    public Long getCreditRating() {
        return creditRating;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }
  @OneToMany(mappedBy="bidder")
    public Set<Bid> getBids() {
        return bids;
    }
}

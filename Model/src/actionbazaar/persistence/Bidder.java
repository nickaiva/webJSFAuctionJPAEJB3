package actionbazaar.persistence;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({
    @NamedQuery(name = "Bidder.findAll", query = "select o from Bidder o"),
    @NamedQuery(name = "Bidder.findBidsByBidder", query = "select o  from Bidder o JOIN FETCH o.bids WHERE o.userId LIKE :userId"),
    @NamedQuery(name = "Bidder.findBidderByStatus", query = "select o from Bidder o  WHERE o.bidderStatus = :bidderStatus")
})
@Inheritance
@DiscriminatorValue(value="B")
public class Bidder extends User implements Serializable {
      @SuppressWarnings("compatibility:-6882944651234381209")
      private static final long serialVersionUID = 8383726154799552353L;
      @Column(name = "BIDDER_STATUS")
      @Enumerated(EnumType.STRING)
      private BidderStatus bidderStatus;
      @Column(name = "CREDIT_RATING")
     /*Bottom creditRate is 0*/
      private Long creditRating;
      @OneToMany(mappedBy = "bidBidder",  fetch=FetchType.EAGER)
      private Set<Bid> bids; 
    
    public Bidder() {
    }

    public void setBidderStatus(BidderStatus bidderStatus) {
        this.bidderStatus = bidderStatus;
    }

    public BidderStatus getBidderStatus() {
        return bidderStatus;
    }

    public void setCreditRating(Long creditRating) {
        this.creditRating = creditRating;
    }

    public Long getCreditRating() {
        return creditRating;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public Set<Bid> getBids() {
        return bids;
    }
}

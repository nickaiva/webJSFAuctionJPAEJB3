package actionbazaar.persistence;

import java.util.Set;

public class Bidder extends User {
  private Long creditRating;

      private Set<Bid> bids;

    public Bidder() {
        super();
    }

    public void setCreditRating(Long creditRating) {
        this.creditRating = creditRating;
    }

    public Long getCreditRating() {
        return creditRating;
    }

    public void setBids(Set bids) {
        this.bids = bids;
    }

    public Set getBids() {
        return bids;
    }
}

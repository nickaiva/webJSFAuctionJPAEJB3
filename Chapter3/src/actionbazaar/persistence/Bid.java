package actionbazaar.persistence;

import java.io.Serializable;

import java.sql.Date;

public class Bid implements Serializable {
  private Date bidDate;

    private Long bidId;

    private Double bidPrice;

    private Item item;

    private Bidder bidder;
    public Bid() {
        super();
    }

    public void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }

    public Date getBidDate() {
        return bidDate;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }

    public Bidder getBidder() {
        return bidder;
    }
}

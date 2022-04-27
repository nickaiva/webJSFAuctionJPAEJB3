package actionbazaar.persistence;

import java.io.Serializable;

import java.sql.Timestamp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "Bid.findAll", query = "select o from Bid o")
})
@Table(name = "BIDS")
public class Bid implements Serializable {
    private String bidBidder;
    private Date bidDate;
    private Long bidId;
    private Long bidItemId;
    private Double bidPrice;
    private String bidStatus;

    public Bid() {
    }

    public Bid(String bidBidder, Date bidDate, Long bidId, Long bidItemId,
               Double bidPrice, String bidStatus) {
        this.bidBidder = bidBidder;
        this.bidDate = bidDate;
        this.bidId = bidId;
        this.bidItemId = bidItemId;
        this.bidPrice = bidPrice;
        this.bidStatus = bidStatus;
    }

    @Column(name="BID_BIDDER")
    public String getBidBidder() {
        return bidBidder;
    }

    public void setBidBidder(String bidBidder) {
        this.bidBidder = bidBidder;
    }

    @Column(name="BID_DATE")
    public Date getBidDate() {
        return bidDate;
    }

    public void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }

    @Id
    @Column(name="BID_ID", nullable = false)
    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    @Column(name="BID_ITEM_ID")
    public Long getBidItemId() {
        return bidItemId;
    }

    public void setBidItemId(Long bidItemId) {
        this.bidItemId = bidItemId;
    }

    @Column(name="BID_PRICE")
    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    @Column(name="BID_STATUS")
    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }
}

package ejb3inaction.example.persistence;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @Column(name="BID_BIDDER")
    private String bidBidder;
    @Column(name="BID_DATE")
    private Timestamp bidDate;
    @Id
    @Column(name="BID_ID", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bidId;
    @Column(name="BID_ITEM_ID")
    private Long bidItemId;
    @Column(name="BID_PRICE")
    private Double bidPrice;
    @Column(name="BID_STATUS")
    private String bidStatus;

    public Bid() {
    }

    public Bid(String bidBidder, Timestamp bidDate, Long bidId, Long bidItemId,
               Double bidPrice, String bidStatus) {
        this.bidBidder = bidBidder;
        this.bidDate = bidDate;
        this.bidId = bidId;
        this.bidItemId = bidItemId;
        this.bidPrice = bidPrice;
        this.bidStatus = bidStatus;
    }

    public String getBidBidder() {
        return bidBidder;
    }

    public void setBidBidder(String bidBidder) {
        this.bidBidder = bidBidder;
    }

    public Timestamp getBidDate() {
        return bidDate;
    }

    public void setBidDate(Timestamp bidDate) {
        this.bidDate = bidDate;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public Long getBidItemId() {
        return bidItemId;
    }

    public void setBidItemId(Long bidItemId) {
        this.bidItemId = bidItemId;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public String getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(String bidStatus) {
        this.bidStatus = bidStatus;
    }
}

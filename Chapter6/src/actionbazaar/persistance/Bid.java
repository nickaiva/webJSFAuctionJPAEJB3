package actionbazaar.persistance;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private Long bidId;
    @Column(name="BID_PRICE")
    private Double bidPrice;
    @Column(name="BID_STATUS")
    private String bidStatus;
    @ManyToOne
    @JoinColumn(name = "BID_ITEM_ID")
    private Item item;

    public Bid() {
    }

    public Bid(String bidBidder, Timestamp bidDate, Long bidId, Item item,
               Double bidPrice, String bidStatus) {
        this.bidBidder = bidBidder;
        this.bidDate = bidDate;
        this.bidId = bidId;
        this.item = item;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

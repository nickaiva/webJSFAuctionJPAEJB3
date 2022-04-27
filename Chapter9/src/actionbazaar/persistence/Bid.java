package actionbazaar.persistence;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "Bid.findAll", query = "select o from Bid o")
})
@Table(name = "BIDS")
public class Bid implements Serializable {
    private Bidder bidBidder;
    private Timestamp bidDate;
    private Long bidId;
    //private Long bidItemId;
    private Double bidPrice;
    private String bidStatus;
    private Item item;
    
    public Bid() {
    }

    public Bid(Bidder bidBidder, Timestamp bidDate, Long bidId, Long bidItemId,
               Double bidPrice, String bidStatus) {
        this.bidBidder = bidBidder;
        this.bidDate = bidDate;
        this.bidId = bidId;
        
        this.bidPrice = bidPrice;
        this.bidStatus = bidStatus;
    }

  @ManyToOne
  @JoinColumn(name = "BID_BIDDER", referencedColumnName = "USER_ID")
      
    public Bidder getBidBidder() {
        return bidBidder;
    }

    public void setBidBidder(Bidder bidBidder) {
        this.bidBidder = bidBidder;
    }

    @Column(name="BID_DATE")
    public Timestamp getBidDate() {
        return bidDate;
    }

    public void setBidDate(Timestamp bidDate) {
        this.bidDate = bidDate;
    }

    
  @SequenceGenerator(name = "BID_SEQ_GEN", sequenceName = "BID_SEQUENCE", initialValue = 1, allocationSize = 1)
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BID_SEQ_GEN")
  @Column(name = "BID_ID")
      
    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
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

    public void setItem(Item item) {
        this.item = item;
    }
  @ManyToOne
  @JoinColumn(name = "BID_ITEM_ID", referencedColumnName = "ITEM_ID")
      
    public Item getItem() {
        return item;
    }
}

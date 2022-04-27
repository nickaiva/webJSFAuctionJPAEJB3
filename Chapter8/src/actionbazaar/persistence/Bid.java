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
    private Timestamp bidDate;
    private Long bidId;
    private Double bidPrice;
    private Bidder bidder;
    private Item item;

    public Bid() {
    }

    public Bid(Bidder bidder, Timestamp bidDate, Long bidId, Item item,
               Double bidPrice) {
        this.bidder = bidder;
        this.bidDate = bidDate;
        this.bidId = bidId;
        this.item = item;
        this.bidPrice = bidPrice;
    }


    @Column(name="BID_DATE")
    public Timestamp getBidDate() {
        return bidDate;
    }

    public void setBidDate(Timestamp bidDate) {
        this.bidDate = bidDate;
    }

    @Id
    @Column(name="BID_ID", nullable = false)
  @SequenceGenerator(name = "BID_SEQ_GEN", sequenceName = "BID_SEQUENCE", initialValue = 1, allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BID_SEQ_GEN")
    
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

  @ManyToOne
   @JoinColumn(name = "BID_BIDDER", referencedColumnName = "USER_ID")
   
    public Bidder getBidder() {
        return bidder;
    }

    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }

  @ManyToOne
  @JoinColumn(name = "BID_ITEM_ID", referencedColumnName = "ITEM_ID")
      
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

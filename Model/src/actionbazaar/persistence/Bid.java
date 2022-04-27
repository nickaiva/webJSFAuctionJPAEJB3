package actionbazaar.persistence;


import java.io.Serializable;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@EntityListeners(actionbazaar.buslogic.validation.BidVerifier.class)
@NamedQueries( { @NamedQuery(name = "Bid.findAll",
                             query = "select o from Bid o"),
                 @NamedQuery(name = "Bid.getBidsByDate",
                             query = "SELECT b FROM Bid b WHERE b.bidDate <= :bidDate  ORDER BY  b.bidDate DESC"),
                 @NamedQuery(name = "Bid.getBidsById",
                             query = "SELECT b FROM Bid b WHERE b.bidId = :bidId "),
                 @NamedQuery(name = "Bid.getBidsByStatus",
                             query = "SELECT b FROM Bid b  WHERE b.bidStatus = :bidStatus  ORDER BY  b.bidDate DESC"),
                 @NamedQuery(name = "Bid.getBidsItemByDate",
                             query = "SELECT b  FROM Bid b   JOIN FETCH b.item   WHERE b.bidDate <= :bidDate  ORDER BY  b.bidDate DESC"),
                 @NamedQuery(name = "Bid.getWinnerBidByItemId",
                             query = "SELECT b  FROM Bid b   JOIN FETCH b.item  WHERE b.bidPrice =( SELECT MAX(c.bidPrice)  FROM Bid c  WHERE c.item.itemId = :itemId  AND  c.item.itemId = b.item.itemId ) ORDER BY  b.bidDate ASC"),
                 @NamedQuery(name = "Bid.getBidsBidderByDate",
                             query = "SELECT b FROM Bid b    JOIN FETCH b.bidBidder   WHERE b.bidDate <= :bidDate ORDER BY  b.bidDate DESC"),
                 @NamedQuery(name = "Bid.getBidsByBidderId",
                             query = "SELECT b FROM Bid b    JOIN FETCH b.bidBidder WHERE b.bidBidder.userId LIKE :userId ORDER BY  b.bidDate DESC"),
                 @NamedQuery(name = "Bid.getBidsByBidderIdByBidStatus",
                             query = "SELECT b FROM Bid b    JOIN FETCH b.bidBidder  WHERE  b.bidStatus = :bidStatus AND b.bidBidder.userId LIKE :userId") })
@Table(name = "BIDS")
public class Bid implements Serializable {
    @SuppressWarnings("compatibility:-3024299717423687045")
    private static final long serialVersionUID = -731560569321078329L;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BID_BIDDER", referencedColumnName = "USER_ID")
    private Bidder bidBidder;
    @Column(name = "BID_DATE")
    private Date bidDate;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "BID_SEQUENCE")
    @Column(name = "BID_ID", nullable = false)
    private Long bidId;
    @Column(name = "BID_PRICE")
    private Double bidPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "BID_STATUS", length = 10)
    private BidStatus bidStatus;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BID_ITEM_ID", referencedColumnName = "ITEM_ID")
    private Item item;
    @Version
    @Column(name = "OPT_LOCK")
    private Long optLock;

    public Bid() {
    }

    public Bid(Bidder bidBidder, Date bidDate, Long bidId, Item item,
               Double bidPrice, BidStatus bidStatus) {
        this.bidBidder = bidBidder;
        this.bidDate = bidDate;
        this.bidId = bidId;
        this.item = item;
        this.bidPrice = bidPrice;
        this.bidStatus = bidStatus;

    }

    public Bidder getBidBidder() {
        return bidBidder;
    }

    public void setBidBidder(Bidder bidBidder) {
        this.bidBidder = bidBidder;
    }

    public Date getBidDate() {
        return bidDate;
    }

    public void setBidDate(Date bidDate) {
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

    public BidStatus getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(BidStatus bidStatus) {
        this.bidStatus = bidStatus;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setOptLock(Long optLock) {
        this.optLock = optLock;
    }

    public Long getOptLock() {
        return optLock;
    }

}

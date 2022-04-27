package actionbazaar.persistance;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "Item.findAll", query = "select o from Item o")
})
@Table(name = "ITEMS")
public class Item implements Serializable {
    @Column(name="BID_END_DATE")
    private Timestamp bidEndDate;
    @Column(name="BID_START_DATE")
    private Timestamp bidStartDate;
    @Column(name="CREATED_DATE")
    private Timestamp createdDate;
    @Column(name="INITIAL_PRICE")
    private Double initialPrice;
    @Id
    @Column(name="ITEM_ID", nullable = false)
    private Long itemId;
    @Column(name="ITEM_NAME")
    private String itemName;
    @Column(name="SELLER_ID")
    private String sellerId;
    @OneToMany(mappedBy = "item")
    private List<Bid> bidList;

    public Item() {
    }

    public Item(Timestamp bidEndDate, Timestamp bidStartDate,
                Timestamp createdDate, Double initialPrice, Long itemId,
                String itemName, String sellerId) {
        this.bidEndDate = bidEndDate;
        this.bidStartDate = bidStartDate;
        this.createdDate = createdDate;
        this.initialPrice = initialPrice;
        this.itemId = itemId;
        this.itemName = itemName;
        this.sellerId = sellerId;
    }

    public Timestamp getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(Timestamp bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    public Timestamp getBidStartDate() {
        return bidStartDate;
    }

    public void setBidStartDate(Timestamp bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }

    public Bid addBid(Bid bid) {
        getBidList().add(bid);
        bid.setItem(this);
        return bid;
    }

    public Bid removeBid(Bid bid) {
        getBidList().remove(bid);
        bid.setItem(null);
        return bid;
    }
}

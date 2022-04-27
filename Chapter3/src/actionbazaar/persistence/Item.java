package actionbazaar.persistence;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

public class Item implements Serializable {
    
  private String itemName;

     private Timestamp bidEndDate;

     private Timestamp bidStartDate;

     private Timestamp createdDate;

     private Double initialPrice;

     private Long itemId;

     private List<Bid> bids;

    public Item() {
        super();
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setBidEndDate(Timestamp bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    public Timestamp getBidEndDate() {
        return bidEndDate;
    }

    public void setBidStartDate(Timestamp bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    public Timestamp getBidStartDate() {
        return bidStartDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Double getInitialPrice() {
        return initialPrice;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setBids(List bids) {
        this.bids = bids;
    }

    public List getBids() {
        return bids;
    }
}

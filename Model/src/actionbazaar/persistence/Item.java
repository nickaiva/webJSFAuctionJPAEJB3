package actionbazaar.persistence;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@NamedQueries({
      @NamedQuery(name = "Item.findAll", query = "select o from Item o ORDER BY o.itemName"),
       @NamedQuery(name = "Item.findRemovedFromFurtherBiddingItems", query = "select o from Item o " +
                                          "WHERE o.bidEndDate <= CURRENT_TIMESTAMP ORDER BY o.itemName"),
             @NamedQuery(name = "Item.findAvailableItems", query = "select o from Item o " +
                                          "WHERE o.bidEndDate > CURRENT_TIMESTAMP ORDER BY o.itemName"),
             @NamedQuery(name = "Item.findWonItems", query = "select o from Item o, Bid b  " +
                                          "WHERE o.bidEndDate <= CURRENT_TIMESTAMP " +
                                          " AND b.bidStatus LIKE 'WINNER' " +
                                          " AND o.itemId =b.item.itemId " +
                                          " AND   b.bidBidder.userId LIKE :userId " +
                                          "ORDER BY o.itemName"),
            @NamedQuery(name = "Item.getItemById", query = "SELECT b FROM Item b " +
                                        " WHERE b.itemId = :itemId "),
      @NamedQuery(name = "Item.getItemsBySellerId", query = "SELECT b FROM Item b " +
                                        "WHERE b.seller.userId LIKE :sellerId ORDER BY  b.createdDate DESC"),
        
      @NamedQuery(name = "Item.getImageByItemId", query = "SELECT b.image FROM Item b   " +
                                        "WHERE b.itemId = :itemId " ),
         
      @NamedQuery(name = "Item.getItemsByName", query = "SELECT b FROM Item b " +
                                        "WHERE b.seller.userId LIKE :userId " +
                                        " AND   b.itemName LIKE UPPER(:itemName) " +
                                        " ORDER BY  b.createdDate DESC"),
      @NamedQuery(name = "Item.getItemsByEndDate", query = "SELECT b FROM Item b " +
                                        "WHERE b.bidEndDate <= :bidEndDate ORDER BY  b.createdDate DESC"),
      @NamedQuery(name = "Item.getItemsByStartDate", query = "SELECT b FROM Item b " +
                                        "WHERE b.bidStartDate <= :bidStartDate ORDER BY  b.createdDate DESC"),
     @NamedQuery(name = "Item.getItemsByDateInterval", query = "SELECT b FROM Item b " +
                                        "WHERE b.bidStartDate BETWEEN :bidStartDate AND  :bidEndDate " +
                                        "ORDER BY  b.createdDate DESC")
      
})
@Table(name = "ITEMS")
public class Item implements Serializable {
    @SuppressWarnings("compatibility:-1055490688889828393")
    private static final long serialVersionUID = 1L;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQUENCE")// start with 100
    private Long itemId;
    @Column(name="ITEM_NAME" , length = 50)
    private String itemName;
    @Column(name="DESCRIPTION", length = 100, nullable = true)
    private String description;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    protected byte[] image;
    
    /*to store extra images of an item*/
 /* 
  * @OneToMany(mappedBy = "item")
    private Set<Image > imageSet;
*/
     /*
   @Column(name="OPT_LOCK")
    private Long optLock;
   */
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="ORDER_ID")
    private Order order;
    @JoinColumn(name="SELLER_ID")
    private Seller seller;
    @OneToMany(mappedBy = "item")
    private Set<Bid> bidSet;

    public Item() {
    }

    public Item(Timestamp bidEndDate,
                    Timestamp bidStartDate,
                    Timestamp createdDate, 
                    Double initialPrice,
                    Long itemId,
                    String itemName) {
        this.bidEndDate = bidEndDate;
        this.bidStartDate = bidStartDate;
        this.createdDate = createdDate;
        this.initialPrice = initialPrice;
        this.itemId = itemId;
        this.itemName = itemName;
       
    }
    
    public Item( String itemName,
                    Double initialPrice,
                    String description,
                  byte[] content) {
    
        this.itemName = itemName;
        this.initialPrice = initialPrice;
        this.image = content;
        this.description = description;
      
        
       
    }
    
    public Item( String itemName, byte[] content ){
        
        this.itemName=itemName;
        this.image=content;
        
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
        this.itemName = itemName.toUpperCase();
    }
/*
    public Long getOptLock() {
        return optLock;
    }

    public void setOptLock(Long optLock) {
        this.optLock = optLock;
    }
*/
    public Set<Bid> getBidSet() {
        return bidSet;
    }

    public void setBidSet(Set<Bid> bidSet) {
        this.bidSet = bidSet;
    }

    public Bid addBid(Bid bid) {
        getBidSet().add(bid);
        bid.setItem(this);
        return bid;
    }

    public Bid removeBid(Bid bid) {
        getBidSet().remove(bid);
        bid.setItem(null);
        return bid;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setDescription(String description) {
        this.description = description.toUpperCase();
    }

    public String getDescription() {
        return description;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

/*
    public void setImageSet(Set<Image> imageSet) {
        this.imageSet = imageSet;
    }

    public Set<Image> getImageSet() {
        return imageSet;
    }
*/
}

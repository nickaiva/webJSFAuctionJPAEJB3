package actionbazaar.persistence;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@NamedQueries({
  @NamedQuery(name = "Item.findAll", query = "select o from Item o")
})
@Table(name = "ITEMS")
public class Item implements Serializable {
    private Timestamp bidEndDate;
    private Timestamp bidStartDate;
    private Timestamp createdDate;
    private Double initialPrice;
    private Long itemId;
    private String itemName;
    private Set<Category> categorySet;
    private Set<Bid> bids;
    private Seller seller;
      
    public Item() {
    }

    public Item(Timestamp bidEndDate, Timestamp bidStartDate,
                Timestamp createdDate, Double initialPrice, Long itemId,
                String itemName) {
        this.bidEndDate = bidEndDate;
        this.bidStartDate = bidStartDate;
        this.createdDate = createdDate;
        this.initialPrice = initialPrice;
        this.itemId = itemId;
        this.itemName = itemName;
        
    }

    @Column(name="BID_END_DATE")
    public Timestamp getBidEndDate() {
        return bidEndDate;
    }

    public void setBidEndDate(Timestamp bidEndDate) {
        this.bidEndDate = bidEndDate;
    }

    @Column(name="BID_START_DATE")
    public Timestamp getBidStartDate() {
        return bidStartDate;
    }

    public void setBidStartDate(Timestamp bidStartDate) {
        this.bidStartDate = bidStartDate;
    }

    @Column(name="CREATED_DATE")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name="INITIAL_PRICE")
    public Double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }

@Id
@Column(name="ITEM_ID", nullable = false)
@GeneratedValue(strategy=GenerationType.AUTO)
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
  @TableGenerator(name="ITEM_TABLE_GEN",
       table="TABLE_SEQ_GEN",
       pkColumnName="SEQ_NAME",
       valueColumnName="SEQ_COUNT",
       pkColumnValue="ITEM_SEQ")

    @Column(name="ITEM_NAME")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

  
  public Category addCategory(Category category) {
      getCategorySet().add(category);
       return category;
  }

  public Category removeCategory(Category category) {
      getCategorySet().remove(category);
      //category.setCategory(null);
      return category;
  }



    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }
  @ManyToMany(mappedBy="items")    
 public Set<Category> getCategorySet() {
        return categorySet;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }
  @OneToMany(mappedBy="item", cascade=CascadeType.ALL)
    public Set<Bid> getBids() {
        return bids;
    }
  public Bid addBid(Bid bid) {
         getBids().add(bid);
         return bid;
     }

     public Bid removeBid(Bid bid) {
         getBids().remove(bid);
         return bid;
     }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
  
  


  @ManyToOne
  @JoinColumn(name="SELLER_ID", referencedColumnName="USER_ID" )

    public Seller getSeller() {
        return seller;
    }
}

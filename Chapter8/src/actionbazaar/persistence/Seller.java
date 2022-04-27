package actionbazaar.persistence;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

@Entity
@Inheritance
@DiscriminatorValue(value = "S")
public class Seller extends User{
    
      private Double commissionRate;
      private Long maxItemsAllowed;
      private Set<Item> items;

    
    public Seller() {
    }

    public void setCommissionRate(Double commissionRate) {
        this.commissionRate = commissionRate;
    }
  @Column(name = "COMM_RATE")
    public Double getCommissionRate() {
        return commissionRate;
    }

    public void setMaxItemsAllowed(Long maxItemsAllowed) {
        this.maxItemsAllowed = maxItemsAllowed;
    }
  @Column(name = "MAX_ITEMS")
    public Long getMaxItemsAllowed() {
        return maxItemsAllowed;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
  @OneToMany(mappedBy = "seller")
    public Set<Item> getItems() {
        return items;
    }
}

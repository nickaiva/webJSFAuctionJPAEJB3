package actionbazaar.persistence;

import java.io.Serializable;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({
  @NamedQuery(name = "Seller.findAll", query = "select o from Seller o")
})
@Inheritance
@DiscriminatorValue(value="S")
public class Seller extends User implements Serializable {
    @SuppressWarnings("compatibility:8584735183444995376")
    private static final long serialVersionUID = 1L;
    @Column(name = "COMM_RATE")
    private Double commissionRate;
    @Column(name = "MAX_ITEMS")
     private Long maxItemsAllowed;
     @OneToMany(mappedBy = "seller")
     private Set<Item> items;
     
    public Seller() {
    }

    public void setCommissionRate(Double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public Double getCommissionRate() {
        return commissionRate;
    }

    public void setMaxItemsAllowed(Long maxItemsAllowed) {
        this.maxItemsAllowed = maxItemsAllowed;
    }

    public Long getMaxItemsAllowed() {
        return maxItemsAllowed;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Item> getItems() {
        return items;
    }
}

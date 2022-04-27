package actionbazaar.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries({
  @NamedQuery(name = "CategoriesItems.findAll", query = "select o from CategoriesItems o")
})
@Table(name = "CATEGORIES_ITEMS")
@IdClass(CategoriesItemsPK.class)
public class CategoriesItems implements Serializable {
    @SuppressWarnings("compatibility:46131832585394375")
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="CI_CATEGORY_ID", nullable = false)
    private Long ciCategoryId;
    @Id
    @Column(name="CI_ITEM_ID", nullable = false)
    private Long ciItemId;

    public CategoriesItems() {
    }

    public CategoriesItems(Long ciCategoryId, Long ciItemId) {
        this.ciCategoryId = ciCategoryId;
        this.ciItemId = ciItemId;
    }

    public Long getCiCategoryId() {
        return ciCategoryId;
    }

    public void setCiCategoryId(Long ciCategoryId) {
        this.ciCategoryId = ciCategoryId;
    }

    public Long getCiItemId() {
        return ciItemId;
    }

    public void setCiItemId(Long ciItemId) {
        this.ciItemId = ciItemId;
    }
}

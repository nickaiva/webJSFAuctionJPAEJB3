package actionbazaar.persistence;

import java.io.Serializable;


public class CategoriesItemsPK implements Serializable {
    @SuppressWarnings("compatibility:975568171035043259")
    private static final long serialVersionUID = 1L;
   
    private Long ciCategoryId;
   
    private Long ciItemId;

    public CategoriesItemsPK() {
    }

    public CategoriesItemsPK(Long ciCategoryId, Long ciItemId) {
        this.ciCategoryId = ciCategoryId;
        this.ciItemId = ciItemId;
    }

    public boolean equals(Object other) {
        if (other instanceof CategoriesItemsPK) {
            final CategoriesItemsPK otherCategoriesItemsPK = (CategoriesItemsPK) other;
            final boolean areEqual =
                (otherCategoriesItemsPK.ciCategoryId.equals(ciCategoryId) && otherCategoriesItemsPK.ciItemId.equals(ciItemId));
            return areEqual;
        }
        return false;
    }

    public int hashCode() {
        return super.hashCode();
    }

    Long getCiCategoryId() {
        return ciCategoryId;
    }

     void setCiCategoryId(Long ciCategoryId) {
        this.ciCategoryId = ciCategoryId;
    }

    Long getCiItemId() {
        return ciItemId;
    }

     void setCiItemId(Long ciItemId) {
        this.ciItemId = ciItemId;
    }
}

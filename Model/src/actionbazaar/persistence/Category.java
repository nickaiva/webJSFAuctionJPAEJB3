package actionbazaar.persistence;

import java.io.Serializable;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries({
  @NamedQuery(name = "Category.findAll", query = "select o from Category o"),
  @NamedQuery(name = "Category.findByCategoryId", query = "select o from Category o where o.categoryId = :categoryId"),
   @NamedQuery(name = "Category.findByCreatedBy", query = "select o from Category o where o.createdBy = :createdBy"),
  @NamedQuery(name = "Category.findByCategoryName", query = "select o from Category o where o.categoryName LIKE :categoryName"),
  @NamedQuery(name = "Category.findByParentId", query = "select o from Category o where o.parentId = :parentId")
})
@Table(name = "CATEGORIES")
public class Category implements Serializable {
    @SuppressWarnings("compatibility:8120981210684379903")
    private static final long serialVersionUID = -1324480754254179116L;
    @Id
    @Column(name="CATEGORY_ID", nullable = false)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ" )
    private Long categoryId;
    @Column(name="CATEGORY_NAME")
    private String categoryName;
    @Column(name="CREATED_BY")
    private String createdBy;
    @Column(name="CREATE_DATE")
    private Date createDate;
    @Column(name="PARENT_ID")
    private Long parentId;

    public Category() {
    }

    public Category(Long categoryId, String categoryName, Date createDate,
                    String createdBy, Long parentId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.parentId = parentId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName.toUpperCase();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}

package actionbazaar.persistence;

import java.io.Serializable;

import java.lang.Integer;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries( { @NamedQuery(name = "Image.findAll",
                             query = "select o from Image o"),
                            @NamedQuery(name = "Image.getImageByObjectId", 
                                   query = "SELECT b.image FROM Image b " +
                                        " WHERE b.objectId = :objectId "),
                             @NamedQuery(name = "Image.getImagesByItemId",
                                         query = "SELECT b.image FROM Image b   " +
                                        "WHERE b.objectId = :itemId " )
                 
                 })
@Table(name = "IMAGES")
public class Image implements Serializable {
    @SuppressWarnings("compatibility:-2084862423215387200")
    private static final long serialVersionUID = -4790030142856199133L;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGE_SEQUENCE")// start with 100
    private int id;
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[] image;
  
    @Column(name="OBJECT_ID", nullable=false)
     private Integer objectId;
         /*
     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "OBJECT_ID", referencedColumnName = "ITEM_ID")
     private Item item;
    */   
    public Image() {
    }

    public Image(String description, Integer id, Integer objectId) {
        this.description = description;
        this.id = id;
        //this.objectId = objectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @Column(nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

  
    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

        /*
    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
*/
}

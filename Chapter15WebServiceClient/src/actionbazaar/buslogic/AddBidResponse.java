
package actionbazaar.buslogic;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addBidResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addBidResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bidNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addBidResponse", propOrder = {
    "bidNumber"
})
public class AddBidResponse {

    protected long bidNumber;

    /**
     * Gets the value of the bidNumber property.
     * 
     */
    public long getBidNumber() {
        return bidNumber;
    }

    /**
     * Sets the value of the bidNumber property.
     * 
     */
    public void setBidNumber(long value) {
        this.bidNumber = value;
    }

}

package actionbazaar.buslogic;


import actionbazaar.persistence.Order;
import actionbazaar.persistence.ShippingInfo;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;


@Remote
public interface PlaceShippingInfo {
    
  public Long addShippingInfo(          
                                           String streetName1,
                                           String streetName2,
                                           String zipCode, 
                                           String city, 
                                           String stateCode,
                                           String country,
                                          Set<Order> orderSet);


    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    ShippingInfo persistShippingInfo(ShippingInfo shippingInfo);

    ShippingInfo mergeShippingInfo(ShippingInfo shippingInfo);

    void removeShippingInfo(ShippingInfo shippingInfo);

    List<ShippingInfo> getShippingInfoFindAll();
    
    List<ShippingInfo> getShippingInfofindByShippingAddressStreetName1( String streetName1);
}

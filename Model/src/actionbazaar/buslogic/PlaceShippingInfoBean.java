package actionbazaar.buslogic;


import actionbazaar.persistence.Address;
import actionbazaar.persistence.Order;
import actionbazaar.persistence.ShippingInfo;

import java.util.List;
import java.util.Set;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.Query;


@DeclareRoles(value = {"BIDDER", "SELLER", "ADMINISTRATOR"})
@Remote
@Local
@Stateless(name = "PlaceShippingInfo", mappedName = "ejb3inaction-Model-PlaceShippingInfo")
public class PlaceShippingInfoBean extends CustomBean implements PlaceShippingInfo,  PlaceShippingInfoLocal {
 
    
    public PlaceShippingInfoBean() {
    }

    public ShippingInfo persistShippingInfo(ShippingInfo shippingInfo) {
        em.persist(shippingInfo);
        return shippingInfo;
    }

    public ShippingInfo mergeShippingInfo(ShippingInfo shippingInfo) {
        return em.merge(shippingInfo);
    }

    public void removeShippingInfo(ShippingInfo shippingInfo) {
        shippingInfo = em.find(ShippingInfo.class, shippingInfo.getShippingId());
        em.remove(shippingInfo);
    }

    /** <code>select o from ShippingInfo o</code> */
     @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<ShippingInfo> getShippingInfoFindAll() {
        return em.createNamedQuery("ShippingInfo.findAll").getResultList();
    }
@RolesAllowed(value = {"BIDDER",  "ADMINISTRATOR"})
    public Long addShippingInfo( 
                                           String streetName1,
                                           String streetName2,
                                           String zipCode, 
                                           String city, 
                                           String stateCode,
                                           String country,
                                           Set<Order> orderSet) {
        ShippingInfo si = new ShippingInfo();
        Address address = new Address();
        address.setStreetName1(streetName1);
        address.setStreetName2(streetName2);
        address.setZipCode(zipCode);
        address.setCity(city);
        address.setState(stateCode);
        address.setCountry(country);
        si.setAddress(address);
        si.setOrderSet(orderSet);
        em.persist(si);
        if (logger.isDebugEnabled())
            logger.debug("Pesisted new ShippingInfo with shippingId: " + si.getShippingId());
        return si.getShippingId();
    }

    @Override
    @RolesAllowed(value = {"BIDDER",  "ADMINISTRATOR"})
    public List<ShippingInfo> getShippingInfofindByShippingAddressStreetName1(String streetName1) {
        
        Query q = em.createNamedQuery("ShippingInfo.findByShippingAddressStreetName1").setParameter("streetName1", streetName1);
        q.setFirstResult(0);
        q.setMaxResults(10);
        return q.getResultList();
    }
}

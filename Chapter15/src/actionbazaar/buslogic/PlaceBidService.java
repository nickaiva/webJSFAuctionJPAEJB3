package actionbazaar.buslogic;

import java.rmi.Remote;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "PlaceBidService")
public interface PlaceBidService extends Remote {
    @WebMethod
    @WebResult(name = "bidNumber")
    public Long addBid(String userId, Long itemId, Double bidPrice);
}

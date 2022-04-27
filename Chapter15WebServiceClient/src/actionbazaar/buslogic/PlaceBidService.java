package actionbazaar.buslogic;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (11.1.1.0.0, build 100408.1504.05443)

@WebService(wsdlLocation="PlaceBidService.wsdl", targetNamespace="http://buslogic.actionbazaar/",
  name="PlaceBidService")
@XmlSeeAlso(
  { actionbazaar.buslogic.ObjectFactory.class })
public interface PlaceBidService
{
  @WebMethod
  @Action(input="", output="")
  @ResponseWrapper(localName="addBidResponse", targetNamespace="http://buslogic.actionbazaar/",
    className="actionbazaar.buslogic.AddBidResponse")
  @RequestWrapper(localName="addBid", targetNamespace="http://buslogic.actionbazaar/",
    className="actionbazaar.buslogic.AddBid")
  @WebResult(targetNamespace="", name="bidNumber")
  public long addBid(@WebParam(targetNamespace="", name="arg0")
    String arg0, @WebParam(targetNamespace="", name="arg1")
    long arg1, @WebParam(targetNamespace="", name="arg2")
    double arg2);
}
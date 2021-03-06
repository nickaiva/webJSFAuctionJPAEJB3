package actionbazaar.buslogic;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
// !DO NOT EDIT THIS FILE!
// This source file is generated by Oracle tools
// Contents may be subject to change
// For reporting problems, use the following
// Version = Oracle WebServices (11.1.1.0.0, build 100408.1504.05443)

@WebServiceClient(wsdlLocation="PlaceBidService.wsdl", targetNamespace="http://buslogic.actionbazaar/",
  name="PlaceBidService")
public class PlaceBidService_Service
  extends Service
{
  private static URL wsdlLocationURL;

  private static Logger logger;
  static
  {
    try
    {
      logger = Logger.getLogger("actionbazaar.buslogic.PlaceBidService_Service");
      URL baseUrl = PlaceBidService_Service.class.getResource(".");
      if (baseUrl == null)
      {
        wsdlLocationURL =
            PlaceBidService_Service.class.getResource("PlaceBidService.wsdl");
        if (wsdlLocationURL == null)
        {
          baseUrl = new File(".").toURL();
          wsdlLocationURL = new URL(baseUrl, "PlaceBidService.wsdl");
        }
      }
      else
      {
                if (!baseUrl.getPath().endsWith("/")) {
         baseUrl = new URL(baseUrl, baseUrl.getPath() + "/");
}
                wsdlLocationURL = new URL(baseUrl, "PlaceBidService.wsdl");
      }
    }
    catch (MalformedURLException e)
    {
      logger.log(Level.ALL,
          "Failed to create wsdlLocationURL using PlaceBidService.wsdl",
          e);
    }
  }

  public PlaceBidService_Service()
  {
    super(wsdlLocationURL,
          new QName("http://buslogic.actionbazaar/", "PlaceBidService"));
  }

  public PlaceBidService_Service(URL wsdlLocation, QName serviceName)
  {
    super(wsdlLocation, serviceName);
  }

  @WebEndpoint(name="PlaceBidServicePort")
  public actionbazaar.buslogic.PlaceBidService getPlaceBidServicePort()
  {
    return (actionbazaar.buslogic.PlaceBidService) super.getPort(new QName("http://buslogic.actionbazaar/",
                                                                           "PlaceBidServicePort"),
                                                                 actionbazaar.buslogic.PlaceBidService.class);
  }

  @WebEndpoint(name="PlaceBidServicePort")
  public actionbazaar.buslogic.PlaceBidService getPlaceBidServicePort(WebServiceFeature... features)
  {
    return (actionbazaar.buslogic.PlaceBidService) super.getPort(new QName("http://buslogic.actionbazaar/",
                                                                           "PlaceBidServicePort"),
                                                                 actionbazaar.buslogic.PlaceBidService.class,
                                                                 features);
  }
}

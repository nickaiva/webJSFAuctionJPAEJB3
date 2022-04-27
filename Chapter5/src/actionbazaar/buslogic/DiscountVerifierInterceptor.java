package actionbazaar.buslogic;

import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class DiscountVerifierInterceptor {
   
    public DiscountVerifierInterceptor() {
        super();
    }
    
  @AroundInvoke
      public Object giveDiscount(InvocationContext ic)   throws Exception {
         Logger logger =Logger.getAnonymousLogger();
         logger.info("*** DiscountVerifier Interceptor invoked for "
                             +ic.getMethod().getName()+" ***");
          //System.out.println("*** DiscountVerifier Interceptor invoked for "
            //                 +ic.getMethod().getName()+" ***");
         
          
          if (ic.getMethod().getName().equals("addBid") && 
              (((String)(ic.getContextData().get("MemberStatus"))).equals("Gold"))){
              Object[] params = ic.getParameters();
              params[2] = new Double ((Double) params[2] * 0.99);
              logger.info("*** DiscountVerifier Reducing Price by 1 percent ***");
              //System.out.println("*** DiscountVerifier Reducing Price by 1 percent ***");
              ic.setParameters(params);
          }
          return ic.proceed();
      }
}

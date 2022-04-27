package actionbazaar.buslogic;

import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ActionBazaarProfilingInterceptor {
  @AroundInvoke
      public Object profile(InvocationContext ic) throws Exception {
          Logger logger =Logger.getAnonymousLogger();
          logger.info("*** ActionBazaar Profile Interceptor invoked for "
                  + ic.getTarget() + " ***");
          System.out.println("*** ActionBazaar Profile Interceptor invoked for "
                  + ic.getTarget() + " ***");
          long startTime = 0;
          long endTime = 0;
          try {
              startTime = System.currentTimeMillis();
              ic.getContextData().put("MemberStatus", "Gold");
              return ic.proceed();
          } finally {
              endTime = System.currentTimeMillis();
              logger.info("*** Method " + ic.getMethod() + " executed in "
                      + (endTime - startTime) + "ms ***");
              System.out.println("*** Method " + ic.getMethod() + " executed in "
                      + (endTime - startTime) + "ms ***");
          }
      }
}

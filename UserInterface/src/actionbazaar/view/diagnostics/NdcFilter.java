package actionbazaar.view.diagnostics;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.apache.log4j.NDC;

public class NdcFilter implements Filter {
    private FilterConfig _filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        _filterConfig = filterConfig;
    }

    public void destroy() {
        _filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException,
                                                   ServletException {
        
      // adds the session ID to the NDC
          
      HttpServletRequest hreq = null;
       
        if( request instanceof HttpServletRequest )           {
             
              hreq = (HttpServletRequest)request;
              HttpSession session = hreq.getSession();
      
              if( session != null ) {
          
                     NDC.push(session.getId());
                     //NDC.push(request.getRemoteAddr());
                    
              } else
                    NDC.push("No session present");
          
      }   else
                NDC.push( "Not an HttpServlet request!" );
      
          chain.doFilter(request, response);
            NDC.pop();
       
    }
}

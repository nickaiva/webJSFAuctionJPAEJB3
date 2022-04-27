package actionbazaar.view.diagnostics;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.DateFormat;

import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*http://127.0.0.1:7101/ejb3inaction/servlet/SuperSnoop*/
public class SuperSnoop extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

  private String makeTitle( String title )
   {
       return new String("<H2>"+title+"</H2>\n");
   }
  
  private String showRequestParams( HttpServletRequest request )
    {
        StringBuffer sb = new StringBuffer(512);
        Enumeration e = request.getParameterNames();
        String key = null;
        String value = null;
        sb.append(makeTitle("Request Parameters"));
        sb.append("<table  border=1>");
        while( e.hasMoreElements() ){
            key = (String)e.nextElement();
            value = request.getParameter(key);
            sb.append( makeRow( key, value ));
        }
        sb.append("</table>");
        return sb.toString();
    }
  
  private String showInitParams() {
         StringBuffer sb = new StringBuffer(512);
         ServletContext context = getServletContext();
         Enumeration e = context.getInitParameterNames();
         String key = null;
         String value = null;
         sb.append(makeTitle("Initialization Parameters"));
         sb.append("<table border=1>");
         while( e.hasMoreElements() ){
             key = (String)e.nextElement();
             value = context.getInitParameter(key);
             sb.append( makeRow( key, value ));
         }
         sb.append("</table>");
         return sb.toString();
     }

  private String showServletContextInfo() 
     {
         StringBuffer sb = new StringBuffer(512);
         ServletContext context = getServletContext();
         sb.append(makeTitle("Servlet Context"));
         sb.append("<table  border=1>");
         sb.append( makeRow( "Server Info", context.getServerInfo()));
         sb.append( makeRow( "Servlet Context Name", context.getServletContextName()));
         sb.append( makeRow( "Real Path", context.getRealPath("/")));
         sb.append("</table>");
         return sb.toString();
     }
   
  private String showContextAttributes() {
         StringBuffer sb = new StringBuffer(512);
         ServletContext context = getServletContext();
         Enumeration e = context.getAttributeNames();
         String key = null;
         String value = null;
         sb.append(makeTitle("Servlet Context Attributes"));
         sb.append("<table  border=1>");
         while( e.hasMoreElements() ){
             key = (String)e.nextElement();
             value = context.getAttribute(key).toString();
             sb.append( makeRow( key, value ));
         }
         sb.append("</table>");
         return sb.toString();
     }
  
  
  private String showSysProps() {
         StringBuffer sb = new StringBuffer(512);
         Properties p = System.getProperties();
         Enumeration e = p.propertyNames();
         String key = null;
         String value = null;
         sb.append(makeTitle("System Properties"));
         sb.append("<table  border=1>");
         sb.append(makeRow("System Time", today() ));
         while( e.hasMoreElements() ){
             key = (String)e.nextElement();
             value = p.getProperty( key );
             sb.append( makeRow( key, value ));
         }
         sb.append("</table>");
         return sb.toString();
     }

   
  
  private String showHeaders( HttpServletRequest request ) {
      StringBuffer sb = new StringBuffer(512);
      Enumeration e = request.getHeaderNames();
      String key = null;
      String value = null;
      sb.append( makeTitle("Request Headers"));
      sb.append("<table  border=1>");
      while( e.hasMoreElements() ){
          key = (String)e.nextElement();
          value = request.getHeader(key);
          sb.append( makeRow( key, value ));
      }
      sb.append("</table>");
      return sb.toString();
  }
  
  private String showRequestInfo(HttpServletRequest request) {
      StringBuffer sb = new StringBuffer(512);
      sb.append(makeTitle( "Request Info"));
      sb.append("<table  border=1>\n");
      sb.append( makeRow( "ContextPath", request.getContextPath() ));
      sb.append( makeRow( "Method", request.getMethod() ));
      sb.append( makeRow( "RequestURL", request.getRequestURL().toString()));
      sb.append( makeRow( "ServletPath", request.getServletPath() ));
      sb.append( makeRow( "CharacterEncoding", request.getCharacterEncoding() ));
      sb.append( makeRow( "ContentType", request.getContentType() ));
      sb.append( makeRow( "RemoteAddress", request.getRemoteAddr() ));
      sb.append( makeRow( "RemoteHost", request.getRemoteHost() ));
      sb.append( makeRow( "Scheme", request.getScheme() ));
      sb.append( makeRow( "ServerName", request.getServerName() ));
      sb.append( makeRow( "ServerPort", Integer.toString(request.getServerPort()) ));
      sb.append("</table>\n");
      return sb.toString();
  }

  
   private String makeRow( String left, String right ) {
    
  StringBuffer sb = new StringBuffer(128);
       sb.append( "<tr>\n" );
       sb.append( "\t<td>"+left+"</td>\n");
       sb.append( "\t<td>"+right+"</td>\n");
       sb.append( "</tr>\n" );
       return sb.toString();
   }
   
   private String today()
   {
       Date now = new Date();
       DateFormat dateFormatter = DateFormat.getDateTimeInstance();
       return dateFormatter.format( now );
   }
  
  
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
      response.setContentType("text/html");
              PrintWriter out = response.getWriter();
              
              out.println("<html>");
              out.println("<head>");
              out.println("<title>SysProps Servlet</title>");
              out.println("</head>");
              out.println("<body>");
              
              out.println( today() );
              out.println( showHeaders(request) );
              out.println( showRequestInfo(request) );
              out.println( showRequestParams(request) );
              out.println( showServletContextInfo() );
              out.println( showContextAttributes() );
              out.println( showInitParams() );
              out.println( showSysProps() );

              out.println("</table>");
              out.println("</body>");
              out.println("</html>");
              out.close();
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>SuperSnoop</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a POST. This is the reply.</p>");
        out.println("</body></html>");
        out.close();
    }
}

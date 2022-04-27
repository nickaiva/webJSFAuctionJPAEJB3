package actionbazaar.web;

import actionbazaar.buslogic.PlaceBid;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;

import javax.servlet.*;
import javax.servlet.http.*;

public class InsertServlet extends HttpServlet {
  @EJB PlaceBid placeBid;
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
   

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>InsertServlet</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a GET. This is the reply.</p>");
        out.println("</body></html>");
        out.close();
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
              Long itemId;
              String userId = "";
              Double bidPrice = new Double(0);
              
             
              itemId =  Long.valueOf(request.getParameter("itemId"));
              userId = request.getParameter("userId");
              bidPrice = Double.valueOf(request.getParameter("bidPrice"));
              try {
                  System.out.println("itemId:" + itemId);
                  System.out.println("userId:" + userId);
                  System.out.println("bidPrice:" + bidPrice);

                  Long bidId = placeBid.addBid(userId, itemId, bidPrice);  
                  System.out.println("Bid Persisted successfully .."+bidId);
                  request.setAttribute("bidId",bidId);  
                   this.getServletContext().getRequestDispatcher("/jsp/success.jsp")
                          .forward(request, response);
              } catch (Exception e) {
                  e.printStackTrace();
                  request.setAttribute("error", e.getMessage());
                  this.getServletContext().getRequestDispatcher("/jsp/error.jsp")
                          .forward(request, response);
              }

          
        
        
    }
}

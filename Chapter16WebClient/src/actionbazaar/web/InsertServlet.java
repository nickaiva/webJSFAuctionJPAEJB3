package actionbazaar.web;


import actionbazaar.buslogic.PlaceBid;

import java.io.IOException;

import javax.ejb.EJB;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
  @EJB PlaceBid placeBid; 

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
      System.out.println("doGet Executed");
              doPost(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        response.setContentType(CONTENT_TYPE);
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

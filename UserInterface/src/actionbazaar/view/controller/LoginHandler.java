package actionbazaar.view.controller;

import actionbazaar.view.CustomBackingBean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class LoginHandler extends CustomBackingBean{
  
    private Logger  logger;
    
    public LoginHandler() {
        super();
    }
    
  public String performLogout() {
            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpServletRequest request =  (HttpServletRequest)ctx.getExternalContext().getRequest();
            HttpServletResponse response =  (HttpServletResponse)ctx.getExternalContext().getResponse();
            /*end_url = /faces/pages/home.jspx because you
             * need to invoke security once more to relogin otherwise 
             * java.lang.RuntimeException: Cannot find FacesContext
              */
            String logoutUrl =  "/adfAuthentication?logout=true&end_url=/faces/pages/home.jspx";
            sendForward(request, response, logoutUrl);
            return null;
      }
  
  private void sendForward(HttpServletRequest request,
                                        HttpServletResponse response,
                                        String loginUrl) {
      FacesContext ctx= FacesContext.getCurrentInstance();
      RequestDispatcher dispatcher = request.getRequestDispatcher(loginUrl);
      try {
          dispatcher.forward(request, response);
      } catch (ServletException se) {
        reportUnexpectedLoginError("ServletException", se);
      } catch (IOException ie) {
        reportUnexpectedLoginError("IOException", ie);
      }
    ctx.responseComplete();
  }

  private void reportUnexpectedLoginError(String errType, Exception e) {
    FacesMessage msg =
                  new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                   "Unexpected Error During Login",
                   "Unexpected Error during Login (" + errType +
                    "), please consult logs for detail");
    
    FacesContext.getCurrentInstance().addMessage(null, msg);
    e.printStackTrace();
    if (logger.isDebugEnabled())
        logger.debug(e.getMessage());
  }
}

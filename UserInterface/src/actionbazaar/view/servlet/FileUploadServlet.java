package actionbazaar.view.servlet;

import actionbazaar.buslogic.PlaceItem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class FileUploadServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
    private PlaceItem facadeSessionEJB;
    private Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        InitialContext context;
        try {
            context = new InitialContext();
            PlaceItem facadeSessionEJB =
                (PlaceItem)context.lookup("ejb3inaction-Model-PlaceItem#actionbazaar.buslogic.PlaceItem");
        } catch (NamingException e) {
            logger.info(e.getStackTrace());
        }

    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        response.setContentType(CONTENT_TYPE);
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        response.setContentType(CONTENT_TYPE);
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException,
                                                                       IOException   {


        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart)     {
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload();
            try           {


                // Parse the request
                FileItemIterator iter = upload.getItemIterator(request);
                while (iter.hasNext())                {


                    FileItemStream item = iter.next();
                    String fieldName = item.getFieldName();
                    String name = item.getName();


                    if (fieldName.equals("selectedFile"))   {


                        byte[] image = IOUtils.toByteArray(item.openStream());


                       

                        //facadeSessionEJB.placeItem(itemName, SellerId, initialPrice, description, image);


                    }


                }


                //response.sendRedirect("ok.jsp");


            }


            catch (IOException ex)


            {


                throw ex;


            }


            catch (Exception ex)


            {


                throw new ServletException(ex);


            }


        }


    }


}

package actionbazaar.view.servlet;


import actionbazaar.buslogic.PlaceItem;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jbo.domain.BlobDomain;

import org.apache.log4j.Logger;


public class ImageServlet extends HttpServlet {
    
    private static final String CONTENT_TYPE = "image/jpg; charset=utf-8";
    @SuppressWarnings("compatibility:988932384557339885")
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException,
                                                                                        IOException {
        response.setContentType(CONTENT_TYPE);
        Long itemId = new Long(request.getParameter("itemId"));
        
        if (logger.isDebugEnabled())
            logger.debug("itemId = " + itemId);
        
        try {
                Context context = new InitialContext();
               
                PlaceItem placeItem = (PlaceItem)context.lookup("ejb3inaction-Model-PlaceItem#actionbazaar.buslogic.PlaceItem");
                byte[] image  = placeItem.getImageByItemId(itemId);
    /*
                PlaceImage placeImage = (PlaceImage)context.lookup("ejb3inaction-Model-PlaceImage#actionbazaar.buslogic.PlaceImage");
                List<byte[]> images  = placeImage.getImagesByItemId(itemId);

            if (logger.isDebugEnabled())
                logger.debug("Number of images = " + images.size());
    
        for ( int i=0; i<images.size(); i++) {
    */        
            if (image != null) {
            
                BlobDomain bld = new BlobDomain(image);

                OutputStream os = response.getOutputStream();
                BufferedInputStream in =  new BufferedInputStream(bld.getBinaryStream());
                int b;
                byte[] buffer = new byte[10240];

                while ((b = in.read(buffer, 0, 10240)) != -1) {
                    os.write(buffer, 0, b);
                }
                os.close();
            } else {
                    if (logger.isDebugEnabled())
                        logger.debug("No  image exists yet for itemId: " + itemId);
            }
       /*  } */   //end for
        } catch (NamingException e){
            if (logger.isDebugEnabled())
                logger.debug(" Caught exception" + e.getMessage());
            e.printStackTrace();    
        }
        catch (IOException e){
            if (logger.isDebugEnabled())
                logger.debug(" Caught exception" + e.getMessage());
            e.printStackTrace();    
        }
       /*
        Connection conn = null;


        try {
            Context ctx = new InitialContext();
            DataSource ds =
                (DataSource)ctx.lookup("jdbc/ActionBazaarEJB3inActionDS");

            conn = ds.getConnection();
            PreparedStatement statement =
                conn.prepareStatement("SELECT   M.IMAGE " +
                                      " FROM IMAGES M , ITEMS I " +
                                    " WHERE OBJECT_ID= ? " +
                                    "AND M.OBJECT_ID = I.ITEM_ID");
            statement.setLong(1, new Long(itemId));
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Blob blob = rs.getBlob("IMAGE");

                BufferedInputStream in =
                    new BufferedInputStream(blob.getBinaryStream());
                OutputStream os = response.getOutputStream();
                int b=0;
                byte[] buffer = new byte[10240];

                while ((b = in.read(buffer, 0, 10240)) != -1) {
                    os.write(buffer, 0, b);
                }
                os.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {

                    conn.close();
                }

            } catch (SQLException sqle) {
                sqle.printStackTrace();

            }

        }*/
       
    }

    public void doPost(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException,
                                                                                         IOException {
            doGet(request,response);
    }
}

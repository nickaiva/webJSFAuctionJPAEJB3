package actionbazaar.view.backing;


import actionbazaar.buslogic.PlaceImage;

import actionbazaar.view.CustomBackingBean;
import actionbazaar.view.utils.ContentTypes;

import java.io.IOException;

import javax.faces.event.ValueChangeEvent;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.ServletException;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBorderLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichResetButton;

import org.apache.commons.io.IOUtils;
import org.apache.myfaces.trinidad.model.UploadedFile;


public class PlaceAdditionalImage extends CustomBackingBean {
    private RichForm f1;
    private RichDocument d1;

    //used for blob insert see ie http://docs.oracle.com/html/E18745_01/devguide/fileUpload.html
    // Visit http://commons.apache.org/proper/commons-io/download_io.cgi for jar lib
    private UploadedFile file;
    private RichPanelGroupLayout pgl1;
    private RichInputFile if1;
    private RichPanelFormLayout pfl1;
    private RichInputText it1;
    private RichPanelBorderLayout pbl1;
    private RichInputText it2;
    private RichResetButton rb1;
    private RichCommandButton cb1;

    public PlaceAdditionalImage() {
    }

    public void  uploadFileValueChangeEvent(ValueChangeEvent valueChangeEvent)       {
          
            // The event give access to an Uploade dFile which contains data about the file and its content
       UploadedFile   file = (UploadedFile) valueChangeEvent.getNewValue();
      
            if (file == null)
                logger.info("File is null");
             // Get the original file name
            String fileName = file.getFilename();
             if (logger.isDebugEnabled())
                             logger.debug("fileName " + fileName);
             // get the mime type
            String contentType = ContentTypes.get(fileName);
             if (logger.isDebugEnabled())
                             logger.debug("contentType " + contentType);
                  //NPE when  autosubmit="true"
                  setFile(file);
          
    
    }

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setIf1(RichInputFile if1) {
        this.if1 = if1;
    }

    public RichInputFile getIf1() {
        return if1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setPbl1(RichPanelBorderLayout pbl1) {
        this.pbl1 = pbl1;
    }

    public RichPanelBorderLayout getPbl1() {
        return pbl1;
    }

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
    }

    public void setRb1(RichResetButton rb1) {
        this.rb1 = rb1;
    }

    public RichResetButton getRb1() {
        return rb1;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public String placeAdditionalImage_action() throws ServletException,
                                                                IOException {
        try {
                //unless all values are given a NPE is thrown
                int objectID =  Integer.parseInt((( it1.getValue().toString() !=null) ? it1.getValue().toString() : " "));
                //    String SellerId = (String)it2.getValue();
                String description=( (String)it2.getValue() != null)  ? (String)it2.getValue() : " " ;
                   /* if (getFile().getLength() > 2048000) //size is in bytes!
                        throw new CustomException("00005",    new String[] { "", "" });
                */
                    //Apache IOUtils jar is necessary
                byte[] image = getFile() != null ?  IOUtils.toByteArray(getFile().getInputStream()) : null;
                   
                InitialContext context = new InitialContext();
                PlaceImage pi = (PlaceImage)context.lookup("ejb3inaction-Model-PlaceImage#actionbazaar.buslogic.PlaceImage");
                pi.placeImage(objectID, description, image);

        } catch (NamingException ex) {
                 logger.info(ex.getMessage()); 
                                      
        }
        
        return "queryItems";
    }
}

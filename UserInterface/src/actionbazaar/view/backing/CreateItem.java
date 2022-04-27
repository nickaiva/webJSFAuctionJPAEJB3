package actionbazaar.view.backing;


import actionbazaar.buslogic.PlaceItem;

import actionbazaar.view.CustomBackingBean;
import actionbazaar.view.utils.ContentTypes;

import java.io.IOException;

import javax.faces.event.ValueChangeEvent;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.ServletException;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBorderLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichResetButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;

import oracle.binding.BindingContainer;

import org.apache.commons.io.IOUtils;
import org.apache.myfaces.trinidad.model.UploadedFile;


public class CreateItem extends CustomBackingBean {
    private RichCommandButton cb2;
    private RichResetButton rb1;
    private RichPanelBorderLayout pbl1;
    private RichInputFile if1;
    private RichInputText it4;
    private RichInputText it3;
    private RichInputText it2;
    private RichInputText it1;
    private RichPanelFormLayout pfl2;
    private RichPanelGroupLayout pgl1;
    private RichPanelStretchLayout psl1;
    private RichForm f1;
    private RichMessages m1;
    private RichDocument d1;
    private RichOutputFormatted of1;
    //used for blob insert see ie http://docs.oracle.com/html/E18745_01/devguide/fileUpload.html
    // Visit http://commons.apache.org/proper/commons-io/download_io.cgi for jar lib
    private UploadedFile file;
    private RichOutputFormatted of2;


    public CreateItem() {
        
    }
    
    public String createItem_action() throws ServletException,
                                                                IOException{
    
        try {
                //unless all values are given a NPE is thrown
                String itemName =( (String)it1.getValue() !=null) ? (String)it1.getValue() : " ";
                    String SellerId = (String)it2.getValue();
                Double initialPrice = (Double)it3.getValue() != null ?  (Double)it3.getValue() : 1;
                String description=( (String)it4.getValue() != null)  ? (String)it4.getValue() : " " ;
                   /* if (getFile().getLength() > 2048000) //size is in bytes!
                        throw new CustomException("00005",    new String[] { "", "" });
                */
                    //Apache IOUtils jar is necessary
                byte[] image = getFile() != null ?  IOUtils.toByteArray(getFile().getInputStream()) : null;
                   
                InitialContext context = new InitialContext();
                PlaceItem pi = (PlaceItem)context.lookup("ejb3inaction-Model-PlaceItem#actionbazaar.buslogic.PlaceItem");
                pi.placeItem(itemName, SellerId, initialPrice, description, image);

        } catch (NamingException ex) {
                 logger.info(ex.getMessage()); 
                                      
        }
 
        return "queryItems";
    }

/*Once it returns, all references to image etc are null; unless its autosubmit="false" */
    
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



    public void setCb2(RichCommandButton cb2) {
        this.cb2 = cb2;
    }

    public RichCommandButton getCb2() {
        return cb2;
    }

    public void setRb1(RichResetButton rb1) {
        this.rb1 = rb1;
    }

    public RichResetButton getRb1() {
        return rb1;
    }

    public void setPbl1(RichPanelBorderLayout pbl1) {
        this.pbl1 = pbl1;
    }

    public RichPanelBorderLayout getPbl1() {
        return pbl1;
    }

    public void setIf1(RichInputFile if1) {
        this.if1 = if1;
    }

    public RichInputFile getIf1() {
        return if1;
    }

    public void setIt4(RichInputText it4) {
        this.it4 = it4;
    }

    public RichInputText getIt4() {
        return it4;
    }

    public void setIt3(RichInputText it3) {
        this.it3 = it3;
    }

    public RichInputText getIt3() {
        return it3;
    }

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setOf1(RichOutputFormatted of1) {
        this.of1 = of1;
    }

    public RichOutputFormatted getOf1() {
        return of1;
    }


    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getFile() {
        return file;
    }


    public void setOf2(RichOutputFormatted of2) {
        this.of2 = of2;
    }

    public RichOutputFormatted getOf2() {
        return of2;
    }
}



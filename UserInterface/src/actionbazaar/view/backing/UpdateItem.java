package actionbazaar.view.backing;


import actionbazaar.buslogic.PlaceItem;
import actionbazaar.buslogic.exceptions.CustomException;

import actionbazaar.view.CustomBackingBean;
import actionbazaar.view.utils.ContentTypes;

import java.io.IOException;

import javax.faces.event.ValueChangeEvent;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichCarousel;
import oracle.adf.view.rich.component.rich.data.RichCarouselItem;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBorderLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichResetButton;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichInlineFrame;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;
import oracle.adf.view.rich.component.rich.output.RichSeparator;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.binding.BindingContainer;

import org.apache.commons.io.IOUtils;
import org.apache.myfaces.trinidad.component.UIXGroup;
import org.apache.myfaces.trinidad.model.UploadedFile;


public class UpdateItem extends CustomBackingBean {
    private RichSeparator s2;
    private RichCommandButton cb1;
    private RichResetButton rb2;
    private RichPanelBorderLayout pbl1;
    private RichInputText it2;
    private RichInputText it1;
    private RichPanelFormLayout pfl1;
    private RichSpacer s1;
    private RichOutputFormatted of1;
    private RichPanelGroupLayout pgl2;
    private RichCommandButton cb2;
    private RichResetButton rb1;
    private RichPanelGroupLayout pgl1;
    private UIXGroup g1;
    private RichInputText it7;
    private RichInputText it6;
    private RichInputText it5;
    private RichInputText it4;
    private RichInputText it3;
    private RichInputDate id3;
    private RichPanelFormLayout pfl2;
    private RichPanelBorderLayout pbl2;
    private RichPanelGroupLayout pgl3;
    private RichPanelStretchLayout psl1;
    private RichForm f1;
    private RichMessages m1;
    private RichDocument d1;
    private RichInputFile if1;
    //used for blob insert see ie http://docs.oracle.com/html/E18745_01/devguide/fileUpload.html
    private UploadedFile file;
    private RichOutputFormatted of2;
    private RichInlineFrame if2;
    private RichCarousel c1;
    private RichCarouselItem ci1;
    private RichImage i1;

    public UpdateItem() {
    }
    
    public String updateItem_action() throws IOException {
        
        try {
                String itemName = (String)it5.getValue();
                Long itemId = new Long(it4.getValue().toString());
                Double initialPrice = new  Double(it3.getValue().toString());
                String description = null;
                if ( (String)it6.getValue() != null )
                    description =  (String)it6.getValue();
                else {
                    description= "No description exists!";
                    //throw new CustomException("00006",  new String[] { "FirstParameter", "SeventhParameter" });
                    
                }
                InitialContext context = new InitialContext();
                PlaceItem pi = (PlaceItem)context.lookup("ejb3inaction-Model-PlaceItem#actionbazaar.buslogic.PlaceItem");
               
                byte[] image =null ;
                //Check if initial image is  null
                if (pi.getImageByItemId(itemId) !=null); 
                    image= pi.getImageByItemId(itemId);
            //check whether a new image file is selected by the user    
            if(getFile() != null) {
                //Apache IOUtils jar is necessary
                 image = IOUtils.toByteArray(getFile().getInputStream());
            }
              
            pi.updateItem(itemId,itemName,  initialPrice, description, image);

        } catch (NamingException ex) {
                 logger.info(ex.getMessage()); 
                                      
        }
        /*BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("mergeItem");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        */
        return null;
    }

    
    public void  uploadFileValueChangeEvent(ValueChangeEvent valueChangeEvent)       {
          
            // The event gives access to an Uploade dFile which contains data about the file and its content
       UploadedFile   file = (UploadedFile) valueChangeEvent.getNewValue();
        
        if (file == null || file.getLength() > 2048000  ) { //size is in bytes!
                logger.info("File is null");
                throw new CustomException("00005", new String[] { "", "" });
        }
     
             // Get the original file name
            String fileName = file.getFilename();
             if (logger.isDebugEnabled())
                             logger.debug("fileName " + fileName);
             // get the mime type
            String contentType = ContentTypes.get(fileName);
             if (logger.isDebugEnabled())
                             logger.debug("contentType " + contentType);
             
                  //NPE thrown when  autosubmit="true"
                  setFile(file);
          
    
    }

    public void setS2(RichSeparator s2) {
        this.s2 = s2;
    }

    public RichSeparator getS2() {
        return s2;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setRb2(RichResetButton rb2) {
        this.rb2 = rb2;
    }

    public RichResetButton getRb2() {
        return rb2;
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

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setOf1(RichOutputFormatted of1) {
        this.of1 = of1;
    }

    public RichOutputFormatted getOf1() {
        return of1;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
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

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setG1(UIXGroup g1) {
        this.g1 = g1;
    }

    public UIXGroup getG1() {
        return g1;
    }

    public void setIt7(RichInputText it7) {
        this.it7 = it7;
    }

    public RichInputText getIt7() {
        return it7;
    }

    public void setIt6(RichInputText it6) {
        this.it6 = it6;
    }

    public RichInputText getIt6() {
        return it6;
    }

    public void setIt5(RichInputText it5) {
        this.it5 = it5;
    }

    public RichInputText getIt5() {
        return it5;
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

    public void setId3(RichInputDate id3) {
        this.id3 = id3;
    }

    public RichInputDate getId3() {
        return id3;
    }


    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }


    public void setPbl2(RichPanelBorderLayout pbl2) {
        this.pbl2 = pbl2;
    }

    public RichPanelBorderLayout getPbl2() {
        return pbl2;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
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

    public void setIf1(RichInputFile if1) {
        this.if1 = if1;
    }

    public RichInputFile getIf1() {
        return if1;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getFile() {
        return file;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

 
    public void setOf2(RichOutputFormatted of2) {
        this.of2 = of2;
    }

    public RichOutputFormatted getOf2() {
        return of2;
    }


    public void setIf2(RichInlineFrame if2) {
        this.if2 = if2;
    }

    public RichInlineFrame getIf2() {
        return if2;
    }

    public void setC1(RichCarousel c1) {
        this.c1 = c1;
    }

    public RichCarousel getC1() {
        return c1;
    }

    public void setCi1(RichCarouselItem ci1) {
        this.ci1 = ci1;
    }

    public RichCarouselItem getCi1() {
        return ci1;
    }

    public void setI1(RichImage i1) {
        this.i1 = i1;
    }

    public RichImage getI1() {
        return i1;
    }
}

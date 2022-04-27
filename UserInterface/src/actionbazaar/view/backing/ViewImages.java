package actionbazaar.view.backing;


import actionbazaar.buslogic.PlaceImage;

import actionbazaar.view.CustomBackingBean;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichCarousel;
import oracle.adf.view.rich.component.rich.data.RichCarouselItem;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBorderLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichResetButton;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichMessages;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;


public class ViewImages extends CustomBackingBean {
    
    private RichForm f1;
    private RichDocument d1;
    private RichPanelStretchLayout psl1;
    private RichCarousel c1;
    private RichCarouselItem ci1;
    private RichPanelFormLayout pfl1;
    private RichInputText it1;
    private RichCommandButton cb1;
    private RichMessages m1;
    private RichResetButton rb2;
    private RichPanelBorderLayout pbl1;
    private RichImage ai1;
    //used to load images
     private  List<byte[]> images;
    

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

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
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

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
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

    public void setAi1(RichImage ai1) {
        this.ai1 = ai1;
    }

    public RichImage getAi1() {
        return ai1;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String loadImages_action() {
        
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("getImagesByItemId");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }

        try {
            InitialContext context = new InitialContext();
            Long itemId = new Long(it1.getValue().toString());
            //Carousel not working unable to show many product images
            //code needs altering
            PlaceImage pi = (PlaceImage)context.lookup("ejb3inaction-Model-PlaceImage#actionbazaar.buslogic.PlaceImage");
             images = pi.getImagesByItemId(itemId);
            if (logger.isDebugEnabled())
                logger.debug("Number of images = " + images.size());
            
        } catch (NamingException ne) {
            // TODO: Add catch code
            logger.info(ne.getMessage());
        }
        
        
        return null;
    }

    public void setImages(List<byte[]> images) {
        this.images = images;
    }

    public List<byte[]> getImages() {
        return images;
    }
}

package actionbazaar.view.backing;


import actionbazaar.buslogic.AddUser;

import actionbazaar.persistence.Bidder;
import actionbazaar.persistence.BidderStatus;

import actionbazaar.view.CustomBackingBean;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBorderLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichResetButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.binding.BindingContainer;


public class ChangeBidderStatus extends CustomBackingBean {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelStretchLayout psl1;
    private RichMessages m1;
    private RichPanelFormLayout pfl2;
    private RichCommandButton cb2;
    private RichSelectOneChoice soc1;
    private UISelectItems si1;
    /*For the SelectSingleOption*/
    private ArrayList<SelectItem> allBidderStatuses;
    private ArrayList<SelectItem> allBidders;
    private RichSelectOneChoice soc2;
    private UISelectItems si2;
    private RichPanelBorderLayout pbl1;
    private RichResetButton rb1;
    private RichOutputFormatted of1;
    private RichSpacer s1;

    public ChangeBidderStatus() {
    }

  public void setAllBidderStatuses(ArrayList<SelectItem> allBidderStatuses) {
      this.allBidderStatuses = allBidderStatuses;
  }

  public ArrayList<SelectItem> getAllBidderStatuses() {
      
     BidderStatus allBidderStatusesArray[] =  BidderStatus.values();
     ArrayList<SelectItem> allBidderStatuses = new ArrayList<SelectItem>();
    /*An enumeration is used instead of selecting a db table*/
     for (BidderStatus bs : allBidderStatusesArray){
        SelectItem selectItem = new SelectItem(bs.name(), bs.name());// not (bs.ordinal(), bs.name())
        allBidderStatuses.add( selectItem);
    }
    return allBidderStatuses;
  }

  public ArrayList<SelectItem> getAllBidders() {
       try {
             InitialContext context = new InitialContext();
             AddUser bean1 = (AddUser)context.lookup("ejb3inaction-Model-AddUser#actionbazaar.buslogic.AddUser");
             //Either NEW or INACTIVE        
                       
             List<Bidder> AllBidders = bean1.getBidderFindBidderByStatus(BidderStatus.NEW);
             allBidders = new ArrayList<SelectItem>(AllBidders.size());
             for (Bidder bi : AllBidders) {
                 String bidderId = bi.getUserId() ;
                 String currentBidderStatus = bi.getBidderStatus().toString();
                 SelectItem selectItem1 = new SelectItem(bidderId, bidderId +" is: "+ currentBidderStatus );
                 allBidders.add(selectItem1);
           }
            if (logger.isDebugEnabled())
                logger.debug("allBidders.size() " + allBidders.size());
           this.setAllBidders(allBidders);
           } catch (NamingException e) {
               logger.info(e.getMessage());
       }
       return allBidders;
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

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }


    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }


    public void setCb2(RichCommandButton cb2) {
        this.cb2 = cb2;
    }

    public RichCommandButton getCb2() {
        return cb2;
    }

    public void setSoc1(RichSelectOneChoice soc1) {
        this.soc1 = soc1;
    }

    public RichSelectOneChoice getSoc1() {
        return soc1;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }


    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String changeBidderStatus() {
        
      try {
                FacesContext ctx = FacesContext.getCurrentInstance();
                ELContext ectx =ctx.getELContext();
                Application app = ctx.getApplication();
                ExpressionFactory factory = app.getExpressionFactory();
          /*See CreateOrder.java for other alternatives*/
                ValueExpression ve =   factory.createValueExpression(ectx,   "#{backingBeanScope.backing_changeBidderStatus.soc2.value}",Object.class );
                String bidderId =  ve.getValue(ectx).toString();
                ValueExpression ve1= factory.createValueExpression(ectx,   "#{backingBeanScope.backing_changeBidderStatus.soc1.value}",Object.class );
                BidderStatus bidderStatus = BidderStatus.valueOf(ve1.getValue(ectx).toString() );   
                
                 if (logger.isDebugEnabled())
                    logger.debug(  "bidderId " + bidderId +"  BidderStatus " + bidderStatus );
                    
                InitialContext context = new InitialContext();
                AddUser facadeSessionEJB = (AddUser)context.lookup("ejb3inaction-Model-AddUser#actionbazaar.buslogic.AddUser");
                facadeSessionEJB.changeBidderStatus(bidderId, bidderStatus );
                  
      } catch (NamingException e) {
                  logger.info(e.getMessage());
                  return null;
      }
      
   
        return null;
    }

    public void setAllBidders(ArrayList<SelectItem> bidders) {
        this.allBidders = bidders;
    }

    public void setSoc2(RichSelectOneChoice soc2) {
        this.soc2 = soc2;
    }

    public RichSelectOneChoice getSoc2() {
        return soc2;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setPbl1(RichPanelBorderLayout pbl1) {
        this.pbl1 = pbl1;
    }

    public RichPanelBorderLayout getPbl1() {
        return pbl1;
    }

    public void setRb1(RichResetButton rb1) {
        this.rb1 = rb1;
    }

    public RichResetButton getRb1() {
        return rb1;
    }

    public void setOf1(RichOutputFormatted of1) {
        this.of1 = of1;
    }

    public RichOutputFormatted getOf1() {
        return of1;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }
}

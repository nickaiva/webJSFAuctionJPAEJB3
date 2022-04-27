package actionbazaar.view.backing;


import actionbazaar.buslogic.PlaceBillingInfo;
import actionbazaar.buslogic.PlaceItem;
import actionbazaar.buslogic.PlaceOrder;
import actionbazaar.buslogic.PlaceShippingInfo;

import actionbazaar.persistence.Address;
import actionbazaar.persistence.BillingInfo;
import actionbazaar.persistence.Item;
import actionbazaar.persistence.ShippingInfo;

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

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBorderLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichResetButton;
import oracle.adf.view.rich.component.rich.output.RichMessages;


public class CreateOrder extends CustomBackingBean {
    private RichInputText it3;
    private RichPanelFormLayout pfl1;
    private RichPanelStretchLayout psl1;
    private RichForm f1;
    private RichMessages m1;
    private RichDocument d1;
    /**/
    private String bidderId ;
    private ArrayList<SelectItem> allItems;
    private ArrayList<SelectItem> orderItems;
    private ArrayList<SelectItem> shippingInfos;
    private ArrayList<SelectItem> billingInfos;

    private RichCommandButton cb1;
    private RichSelectOneChoice soc1;
    private UISelectItems si2;
    private RichSelectOneChoice soc2;
    private UISelectItems si3;
    private RichSelectManyChoice smc1;
    private UISelectItems si1;
    private RichPanelBorderLayout pbl1;
    private RichResetButton rb1;

    public CreateOrder() {
      bidderId = ADFContext.getCurrent().getSecurityContext().getUserName();
  
    }

  public ArrayList<SelectItem> getAllItems() {
       try {
           InitialContext context = new InitialContext();
           PlaceItem beanRemote = (PlaceItem) context.lookup( "ejb3inaction-Model-PlaceItem#actionbazaar.buslogic.PlaceItem");
           List<Item> allItems = beanRemote.getItemFindWonItems(bidderId);// getItemFindRemovedFromFurtherBiddingItems(); //.getItemFindAll();
           orderItems = new ArrayList<SelectItem>(allItems.size());
           for (Item item : allItems) {
                 Long itemId = item.getItemId();
                 String itemName= item.getItemName();
                 SelectItem selectItem = new SelectItem(itemId, itemName);
                 orderItems.add(selectItem);
           }
            if (logger.isDebugEnabled())
                logger.debug("orderItems.size() " + orderItems.size());
             this.setOrderItems(orderItems);
           } catch (NamingException e) {
               System.err.println(e.getMessage());
       }
         return orderItems;
   }
  
  public ArrayList<SelectItem> getAllBillingInfos() {
       try {
             InitialContext context = new InitialContext();
             PlaceBillingInfo beanRemote1 = (PlaceBillingInfo)context.lookup("ejb3inaction-Model-PlaceBillingInfo#actionbazaar.buslogic.PlaceBillingInfo");
                           
             List<BillingInfo> alllBillingInfos = beanRemote1.getBillingInfoFindByUserId(bidderId);
             billingInfos = new ArrayList<SelectItem>(alllBillingInfos.size());
             for (BillingInfo bi : alllBillingInfos) {
                 Long billingId = bi.getBillingId();
                 String billingName= bi.getAccountNo() + " " + bi.getCardType() + " " + bi.getExpiryDate() +" " + bi.getSecretCode();
                 SelectItem selectItem1 = new SelectItem(billingId, billingName);
                 billingInfos.add(selectItem1);
           }
            if (logger.isDebugEnabled())
                logger.debug("billingInfos.size() " + billingInfos.size());
           this.setBillingInfos(billingInfos);
           } catch (NamingException e) {
               System.err.println(e.getMessage());
       }
       return billingInfos;
   }

   public ArrayList<SelectItem> getAllShippingInfos() {
       try {
           InitialContext context = new InitialContext();
           PlaceShippingInfo beanRemote2 = (PlaceShippingInfo)context.lookup("ejb3inaction-Model-PlaceShippingInfo#actionbazaar.buslogic.PlaceShippingInfo");
           List<ShippingInfo> allShippingInfos = beanRemote2.getShippingInfoFindAll();
           shippingInfos = new ArrayList<SelectItem>(allShippingInfos.size());
           for (ShippingInfo si : allShippingInfos) {
                 Long ShippingInfoId = si.getShippingId();
                 Address address =si.getAddress();
                 String ShippingInfoName= address.getStreetName1() + " " +
                                                      address.getStreetName2() + " "+ 
                                                      address.getZipCode()+ " "+
                                                      address.getCity() + " " +
                                                      address.getState()+ " "+
                                                      address.getCountry();
                 SelectItem selectItem2 = new SelectItem(ShippingInfoId, ShippingInfoName);
                 shippingInfos.add(selectItem2);
           }
            if (logger.isDebugEnabled())
              logger.debug("shippingInfos.size() " + shippingInfos.size());
             this.setShippingInfos(shippingInfos);
           } catch (NamingException e) {
               System.err.println(e.getMessage());
       }
       return shippingInfos;
   }
  
  public Object createNewOrder() {
        try {
                  FacesContext ctx = FacesContext.getCurrentInstance();
                  ELContext ectx =ctx.getELContext();
                  Application app = ctx.getApplication();
                  ExpressionFactory factory = app.getExpressionFactory();
                /*  An alternate way which works too*/
                /*
                 java.util.ArrayList billingIdUIComponents = (java.util.ArrayList)si2.getValue();
                 SelectItem billingIdItem=(SelectItem)billingIdUIComponents.get(0);
                 Long billingId = new Long( billingIdItem.getValue().toString() );
                 */
                  ValueExpression ve =   factory.createValueExpression(ectx,   "#{backingBeanScope.backing_createOrder.soc1.value}",Object.class );
                  Long billingId = new Long(ve.getValue(ectx).toString());
                  ValueExpression ve1= factory.createValueExpression(ectx,   "#{backingBeanScope.backing_createOrder.soc2.value}",Object.class );
                  Long shippingId = new Long( ve1.getValue(ectx).toString());
                 // ValueExpression ve2= factory.createValueExpression(ectx,   "#{backingBeanScope.backing_createOrder.smc1.value}",Object.class );
                 /*selectManyChoice working alternate*/
               /*   ArrayList<Object> selectedItemsId =  (ArrayList<Object>)ve2.getValue(ectx);
                 ArrayList<Long> itemsId = new ArrayList<Long>(selectedItemsId.size());
                 for (int i = 0; i < selectedItemsId.size(); i++) {
                      Long val = new Long(selectedItemsId.get(i).toString());
                      logger.debug( "itemId " + val);
                      itemsId.add(val);
                  }
               */ 
                    
                 ArrayList itemIdUIComponents = (java.util.ArrayList)si1.getValue();
                 ArrayList<Long> itemsId = new ArrayList<Long>(itemIdUIComponents.size());
                 for (int i = 0; i < itemIdUIComponents.size(); i++) {
                      SelectItem selectedItemsId =(SelectItem)itemIdUIComponents.get(i);
                      Long itemId = new Long( selectedItemsId.getValue().toString() );
                      itemsId.add(itemId);
                }
                 if (logger.isDebugEnabled())
                         logger.debug(  "bidderId " + bidderId +"  billingId " + billingId +  " shippingId " + shippingId);
                 InitialContext context = new InitialContext();
                 PlaceOrder facadeSessionEJB = (PlaceOrder)context.lookup("ejb3inaction-Model-PlaceOrder#actionbazaar.buslogic.PlaceOrder");
                 facadeSessionEJB.addOrder(bidderId, itemsId, shippingId, billingId );
               
               } catch (NamingException e) {
                    System.err.println(e.getMessage());
                    return null;
               }
         return "queryOrders";
    }


    public void setIt3(RichInputText it3) {
        this.it3 = it3;
    }

    public RichInputText getIt3() {
        return it3;
    }


    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
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

    public void setAllItems(ArrayList<SelectItem> allItems) {
        this.allItems = allItems;
    }

    public void setOrderItems(ArrayList<SelectItem> orderItems) {
        this.orderItems = orderItems;
    }


    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setShippingInfos(ArrayList<SelectItem> shippingInfos) {
        this.shippingInfos = shippingInfos;
    }

    public void setBillingInfos(ArrayList<SelectItem> billingInfos) {
        this.billingInfos = billingInfos;
    }

    public void setSoc1(RichSelectOneChoice soc1) {
        this.soc1 = soc1;
    }

    public RichSelectOneChoice getSoc1() {
        return soc1;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setSoc2(RichSelectOneChoice soc2) {
        this.soc2 = soc2;
    }

    public RichSelectOneChoice getSoc2() {
        return soc2;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setSmc1(RichSelectManyChoice smc1) {
        this.smc1 = smc1;
    }

    public RichSelectManyChoice getSmc1() {
        return smc1;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
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
}

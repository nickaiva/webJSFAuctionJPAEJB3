package actionbazaar.view.backing;


import actionbazaar.view.CustomBackingBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.layout.RichPanelBorderLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichSpacer;


public class ChangeLocale extends CustomBackingBean{
    private RichForm f1;
    private RichDocument d1;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    //Addon
   private  List suppLocales;
   private Locale preferredLocale;
   private RichCommandButton cb1;
    private RichPanelBorderLayout pbl1;
    private RichSpacer s1;

    public void setSuppLocales(List suppLocales) {
     this.suppLocales = suppLocales;
  }
  
  public List getSuppLocales() {
              FacesContext fctx = FacesContext.getCurrentInstance();
              Iterator<Locale> localeIt = fctx.getApplication().getSupportedLocales();
              suppLocales = new ArrayList();
              while (localeIt.hasNext()) {
                      Locale locale = localeIt.next();
                      SelectItem item = new  SelectItem(locale.getLanguage(),locale.getDisplayLanguage(locale));
                      suppLocales.add(item);
              }
              return suppLocales;
  }

  public void listChanged(ValueChangeEvent valueChangeEvent) {
          changeLocale(valueChangeEvent.getNewValue().toString());
  }
  
  
  private void changeLocale (String language){
          Locale newLocale = new Locale (language);
          FacesContext fctx = FacesContext.getCurrentInstance();
          fctx.getViewRoot().setLocale(newLocale);
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


    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }


    public void setPreferredLocale(Locale preferredLocale) {
        this.preferredLocale = preferredLocale;
    }

    public Locale getPreferredLocale() {
        return preferredLocale;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public String cb1_action() {
        setPreferredLocale(FacesContext.getCurrentInstance().getViewRoot().getLocale());
        return null;
    }

    public void setPbl1(RichPanelBorderLayout pbl1) {
        this.pbl1 = pbl1;
    }

    public RichPanelBorderLayout getPbl1() {
        return pbl1;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }
}

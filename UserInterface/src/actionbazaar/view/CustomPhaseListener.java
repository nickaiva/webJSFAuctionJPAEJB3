package actionbazaar.view;


import actionbazaar.view.backing.ChangeLocale;

import java.util.Locale;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import oracle.adf.controller.v2.lifecycle.ADFLifecycle;
import oracle.adf.controller.v2.lifecycle.PagePhaseEvent;
import oracle.adf.controller.v2.lifecycle.PagePhaseListener;


public class CustomPhaseListener implements PagePhaseListener {
    public CustomPhaseListener() {
        super();
    }

    public void afterPhase(PagePhaseEvent pagePhaseEvent) {
    }

    public void beforePhase(PagePhaseEvent event) {
          Integer phase = event.getPhaseId();
          if (phase.equals(ADFLifecycle.PREPARE_MODEL_ID)) {
                  FacesContext fctx = FacesContext.getCurrentInstance();
                  ChangeLocale changelocale = (ChangeLocale)fctx.getApplication().evaluateExpressionGet(fctx, "#{ChangeLocale}", Object.class);
                  Locale preferredLocale = changelocale.getPreferredLocale();
                  UIViewRoot uiViewRoot = fctx.getCurrentInstance().getViewRoot();
                  if (preferredLocale == null) {
                         changelocale.setPreferredLocale(uiViewRoot.getLocale());
                } else {
                      uiViewRoot.setLocale(preferredLocale);
                }
          }
    }
}

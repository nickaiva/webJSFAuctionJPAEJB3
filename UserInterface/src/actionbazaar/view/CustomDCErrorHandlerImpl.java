package actionbazaar.view;


import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCErrorHandlerImpl;

import oracle.jbo.JboException;


public class CustomDCErrorHandlerImpl extends DCErrorHandlerImpl {

    public CustomDCErrorHandlerImpl(boolean b) {
        super(b);
    }

    public CustomDCErrorHandlerImpl() {
        super(true);
    }

    @Override
    public void reportException(DCBindingContainer dCBindingContainer,
                                Exception exception) {
        // report JboExceptions as errors
        if (exception instanceof JboException  ) {
                                                         FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                          exception.getCause().getCause().getCause().getMessage(),// notice the three getCause()  calls!
                                                                          null));// java.lang.reflect.InvocationTargetException comes twice first

        } else {
            // report all others as information
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                          exception.getMessage(),
                                                                          null));
        }
    }

    public String getDisplayMessage(BindingContext bindingContext,
                                    Exception exception) {
        // get the error message from the framework
        String errorMessageRaw =
            super.getDisplayMessage(bindingContext, exception);
        // handle messages generated by the database business logic
        return handleDatabaseApplicationError(errorMessageRaw);

       //return super.getDisplayMessage(bindingContext, exception);
    }

    private String handleDatabaseApplicationError(String errorMessageRaw) {
        // the error code for application-specific messages
        // generated by the database application-specific
        // business code
        final String APPLICATION_ERROR_CODE = "20200";
        // the application error messages bundle
        final ResourceBundle errorMessagesBundle =
            ResourceBundle.getBundle("actionbazaar.buslogic.exceptions.messages.ErrorMessagesEL_GR");
        // check for null/empty error message
        if (errorMessageRaw == null || "".equals(errorMessageRaw)) {
            return errorMessageRaw;
        }
        // check for database error message
        if (errorMessageRaw.indexOf("ORA-") == -1) {
            return errorMessageRaw;
        }
        // check for end of database error code indicator
        int endIndex = errorMessageRaw.indexOf(":");
        if (endIndex == -1) {
            return errorMessageRaw;
        }
        // get the database error code
        String dbmsErrorMessageCode = errorMessageRaw.substring(4, endIndex);
        String errorMessageCode = "";
        if (APPLICATION_ERROR_CODE.equals(dbmsErrorMessageCode)) {
            int start = errorMessageRaw.indexOf("-", endIndex) + 1;
            int end = errorMessageRaw.indexOf(":", start);
            errorMessageCode = errorMessageRaw.substring(start, end);
        } else {
            // not application-related error message
            return errorMessageRaw;
        }
        // get the application error message from the
        // application resource bundle using the specific
        // application error code
        String errorMessage = null;
        try {
            errorMessage =
                    errorMessagesBundle.getString("message." + errorMessageCode);
        } catch (MissingResourceException mre) {
            // application error code not found in the bundle,
            // use original message
            return errorMessageRaw;
        }
        // get the error message parameters
        ArrayList parameters = getErrorMessageParameters(errorMessageRaw);
        if (parameters != null && parameters.size() > 0) {
            // replace the message parameter placeholders with the
            // actual parameter values
            int counter = 1;
            for (Object parameter : parameters) {
                // parameter placeholders appear in the message
                // as {1}, {2}, and so on
                errorMessage =
                        errorMessage.replace("{" + counter + "}", parameter.toString());
                counter++;
            }
        }
        // return the formated application error message
        return errorMessage;
    }

    private ArrayList getErrorMessageParameters(String errorMessageRaw) {
        // the parameter indicator in the database
        // application-specific error
        final String PARAMETER_INDICATOR = "$";
        ArrayList parameters = new ArrayList();
        // get parameters from the error message
        for (int i = 1; i <= 10; i++) {
            int start = errorMessageRaw.indexOf(PARAMETER_INDICATOR + i) + 2;
            int end = errorMessageRaw.indexOf(PARAMETER_INDICATOR + i, start);
            if (end == -1) {
                parameters.add(i - 1, "");
            } else {
                parameters.add(i - 1, errorMessageRaw.substring(start, end));
            }
        }
        // return the parameters
        return parameters;
    }
}

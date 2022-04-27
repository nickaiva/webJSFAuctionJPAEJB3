package actionbazaar.buslogic.exceptions;


import java.util.Locale;
import java.util.ResourceBundle;

import javax.ejb.ApplicationException;

import oracle.jbo.JboException;

import org.apache.log4j.Logger;


@ApplicationException(rollback=true)
public class CustomException extends JboException { 
   
    @SuppressWarnings("compatibility:3282808060810172912")
    private static final long serialVersionUID = 3267875030495605142L;
    private static Logger logger = Logger.getLogger("CustomException");
    private static final String ERRORS_BUNDLE =        "actionbazaar.buslogic.exceptions.messages.ErrorMessagesEL_GR";
    private static final String PARAMETERS_BUNDLE = "actionbazaar.buslogic.exceptions.messages.ErrorParamsEL_GR";
    private static final String MESSAGE_PREFIX = "message.";
    private static final String PARAMETER_PREFIX = "parameter.";
    
    public CustomException(Throwable throwable) {
        super(throwable);
    }
    /**
      * Constructor to create an exception using a standard error code and
      * error message parameters
      * @param errorCode, the error message code
      * @param errorParameters, the error message parameters
      */
     public CustomException(final String errorCode,
                                              final Object[] errorParameters) {
         super(ResourceBundle.class, errorCode, errorParameters);
     }
    
    /**
       * Constructor to create an exception using a standard error code.
       * @param errorCode, the error message code
       */
      public CustomException(final String errorCode) {
          super(ResourceBundle.class, errorCode, null);
      }
    /**
        * Construct using exception.
        * @param exception
        */
       public CustomException(final Exception exception) {
           super(exception);
       }
    
  
    
    @Override
    public String getMessage() {
      
      // default message
             String errorMessage =  super.getMessage();
             try {
                 // get access to the error messages bundle
                 final ResourceBundle messagesBundle =
                     ResourceBundle.getBundle(ERRORS_BUNDLE, Locale.getDefault());
                 // construct the error message
                 errorMessage =  this.getErrorCode() + " - " + messagesBundle.getString(MESSAGE_PREFIX +   this.getErrorCode());

                 // get access to the error message parameters bundle
                 final ResourceBundle parametersBundle =
                     ResourceBundle.getBundle(PARAMETERS_BUNDLE,  Locale.getDefault());
                 // loop for all parameters
                 for (int i = 0; i < this.getErrorParameters().length; i++) {
                     // get parameter value
                     final String parameterValue =  parametersBundle.getString(PARAMETER_PREFIX +    (String)this.getErrorParameters()[i]);
                     // replace parameter placeholder in the error message string
                     errorMessage =
                             errorMessage.replaceAll("\\{" + (i + 1) + "}", parameterValue);
                 }
            
             } catch (Exception e) {
                 // log the exception
                 logger.info(e);
             }

             return errorMessage;
    }
  
  public static void main(String[] args) {
         // throw a custom exception with error code "00002" and two parameters
        /* throw new BidValidationException("00002",
                                   new String[] { "ThirdParameter", "FourthParameter" });*/
         throw new CustomException("00004",
                                   new String[] { "FirstParameter", "" });
     }
}

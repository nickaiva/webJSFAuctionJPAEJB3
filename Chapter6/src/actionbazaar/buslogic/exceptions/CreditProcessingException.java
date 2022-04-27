package actionbazaar.buslogic.exceptions;

public class CreditProcessingException extends Exception {
    public CreditProcessingException(Throwable throwable) {
        super(throwable);
    }

    public CreditProcessingException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public CreditProcessingException(String string) {
        super(string);
    }

    public CreditProcessingException() {
        super();
    }
}

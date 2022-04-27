package actionbazaar.buslogic.exceptions;

public class CreditValidationException extends Exception {
    public CreditValidationException(Throwable throwable) {
        super(throwable);
    }

    public CreditValidationException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public CreditValidationException(String string) {
        super(string);
    }

    public CreditValidationException() {
        super();
    }
}

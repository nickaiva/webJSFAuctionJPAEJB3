package ejb3inaction.example.buslogic;

public class BillingException extends RuntimeException {
    public BillingException(Throwable throwable) {
        super(throwable);
    }

    public BillingException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public BillingException(String string) {
        super(string);
    }

    public BillingException() {
        super();
    }
}

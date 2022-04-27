package actionbazaar.buslogic;

public class WorkflowOrderViolationException extends RuntimeException {
    public WorkflowOrderViolationException(Throwable throwable) {
        super(throwable);
    }

    public WorkflowOrderViolationException(String string,
                                           Throwable throwable) {
        super(string, throwable);
    }

    public WorkflowOrderViolationException(String string) {
        super(string);
    }

    public WorkflowOrderViolationException() {
        super();
    }
}

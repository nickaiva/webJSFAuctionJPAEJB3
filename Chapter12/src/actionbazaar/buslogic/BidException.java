package actionbazaar.buslogic;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class BidException extends RuntimeException {
    public BidException(Throwable throwable) {
        super(throwable);
    }

    public BidException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public BidException(String string) {
        super(string);
    }

    public BidException() {
        super();
    }
}

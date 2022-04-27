package actionbazaar.persistence;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class ItemException extends RuntimeException {
    public ItemException(Throwable throwable) {
        super(throwable);
    }

    public ItemException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public ItemException(String string) {
        super(string);
    }

    public ItemException() {
        super();
    }
}

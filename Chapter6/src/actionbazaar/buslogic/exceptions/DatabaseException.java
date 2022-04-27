package actionbazaar.buslogic.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(Throwable throwable) {
        super(throwable);
    }

    public DatabaseException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public DatabaseException(String string) {
        super(string);
    }

    public DatabaseException() {
        super();
    }
}

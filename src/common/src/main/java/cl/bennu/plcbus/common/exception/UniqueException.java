package cl.bennu.plcbus.common.exception;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 21-04-14
 * Time: 11:50 PM
 */
public class UniqueException extends Exception {
    public UniqueException() {
        super();
    }

    public UniqueException(String message) {
        super(message);
    }

    public UniqueException(String message, Throwable cause) {
        super(message, cause);
    }

    public UniqueException(Throwable cause) {
        super(cause);
    }

    protected UniqueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

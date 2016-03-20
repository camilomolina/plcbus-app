package cl.bennu.plcbus.common.exception;

/**
 * Created with IntelliJ IDEA.
 * User: _Camilo
 * Date: 21-04-14
 * Time: 11:50 PM
 */
public class RainException extends Exception {
    public RainException() {
        super();
    }

    public RainException(String message) {
        super(message);
    }

    public RainException(String message, Throwable cause) {
        super(message, cause);
    }

    public RainException(Throwable cause) {
        super(cause);
    }

    protected RainException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

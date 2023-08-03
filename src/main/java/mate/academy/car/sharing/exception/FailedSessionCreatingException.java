package mate.academy.car.sharing.exception;

public class FailedSessionCreatingException extends RuntimeException {
    public FailedSessionCreatingException(String message, Throwable cause) {
        super(message, cause);
    }
}

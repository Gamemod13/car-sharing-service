package mate.academy.car.sharing.exception;

public class TelegramNotificationException extends RuntimeException {
    public TelegramNotificationException(String message) {
        super(message);
    }

    public TelegramNotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}

package assignment.notification;

public class NotificationException extends RuntimeException {
    public NotificationException(String message) {
        super(message);
    }

    public NotificationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return "NotificationException: " + getMessage();
    }

    @Override
    public String getMessage() {
        return "Notification failed: " + super.getMessage();
    }
}

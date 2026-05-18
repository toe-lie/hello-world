package assignment.notification;

public interface EmailSender {
    void send(String recipient, String message);
}

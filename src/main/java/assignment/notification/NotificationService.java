package assignment.notification;

public class NotificationService {
    private final EmailSender emailSender;

    public NotificationService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendWelcomeEmail(User user) {
        emailSender.send(user.getEmail(), "Welcome to our website!");
    }
}

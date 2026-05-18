package assignment.notification;

public class NotificationService {
    private final EmailSender emailSender;

    public NotificationService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendWelcomeEmail(User user) {
        emailSender.send(user.getEmail(), "Welcome to our website!");
    }

    public void sendPasswordReset(User user, String token) {
        emailSender.send(user.getEmail(), "Your password reset token is: " + token);
    }
}

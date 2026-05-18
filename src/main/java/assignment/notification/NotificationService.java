package assignment.notification;

public class NotificationService {
    private final EmailSender emailSender;
    private  final Logger logger;

    public NotificationService(EmailSender emailSender, Logger logger) {
        this.emailSender = emailSender;
        this.logger = logger;
    }

    public void sendWelcomeEmail(User user) {
        sendEmail(user.getEmail(), "Welcome to our website!");
    }

    public void sendPasswordReset(User user, String token) {
        sendEmail(user.getEmail(), "Your password reset token is: " + token);
    }

    private void sendEmail(String recipient, String message) {
        try {
            emailSender.send(recipient, message);
        } catch (Exception e) {
            logException(e);
        }
    }

    private void logException(Exception e) {
        logger.log("error: " + e.getMessage());
    }
}

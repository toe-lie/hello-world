package assignment.notification;

public class NotificationService {
    private final EmailSender emailSender;
    private  final Logger logger;

    public NotificationService(EmailSender emailSender, Logger logger) {
        this.emailSender = emailSender;
        this.logger = logger;
    }

    public void sendWelcomeEmail(User user) {
        try {
            emailSender.send(user.getEmail(), "Welcome to our website!");
        } catch (Exception e) {
            logger.log("error: Failed to send welcome email to " + user.getEmail() + ": " + e.getMessage());
            throw new NotificationException("Failed to send welcome email", e);
        }
    }

    public void sendPasswordReset(User user, String token) {
        try {
            emailSender.send(user.getEmail(), "Your password reset token is:" + token);
        } catch (Exception e) {
            logger.log("error: Failed to send password reset email to " + user.getEmail() + ": " + e.getMessage());
            throw new NotificationException("Failed to send password reset email", e);
        }
    }
}

package assignment.notification;

public class NotificationService {
    private final EmailSender emailSender;
    private final EmailTemplate emailTemplate;

    public NotificationService(EmailSender emailSender, EmailTemplate emailTemplate) {
        this.emailSender = emailSender;
        this.emailTemplate = emailTemplate;
    }

    public void sendWelcomeEmail(User user) {
        emailSender.send(user.getEmail(), emailTemplate.getWelcomeMessage());
    }
}

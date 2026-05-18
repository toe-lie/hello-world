package assignment.notification;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NotificationServiceTest {

    @Test
    void sendWelcomeEmail_sent_to_the_right_address_with_the_right_message() {
        EmailSender emailSender = mock(EmailSender.class);
        EmailTemplate emailTemplate = new EmailTemplate();
        NotificationService service = new NotificationService(
                emailSender,
                emailTemplate
        );
        User user = new User("test@example.com");
        service.sendWelcomeEmail(user);
        verify(emailSender).send(user.getEmail(), emailTemplate.getWelcomeMessage());
    }
}

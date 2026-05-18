package assignment.notification;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class NotificationServiceTest {

    @Test
    void sendWelcomeEmail_sent_to_the_right_address_with_the_right_message() {
        EmailSender emailSender = mock(EmailSender.class);
        NotificationService service = new NotificationService(
                emailSender
        );
        User user = new User("test@example.com");
        service.sendWelcomeEmail(user);
        verify(emailSender).send(user.getEmail(), "Welcome to our website!");
    }

    @Test
    void sendPasswordReset_includes_the_reset_token_in_the_email_body() {

    }
}

package assignment.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class NotificationServiceTest {

    private NotificationService service;
    private EmailSender emailSender;
    private Logger logger;
    private User user;

    @BeforeEach
    void setUp() {
        emailSender = mock(EmailSender.class);
        logger = mock(Logger.class);
        service = new NotificationService(emailSender, logger);
        user = new User("test@example.com");
    }

    @Test
    void sendWelcomeEmail_sent_to_the_right_address_with_the_right_message() {
        service.sendWelcomeEmail(user);
        verify(emailSender).send(user.getEmail(), "Welcome to our website!");
    }

    @Test
    void sendPasswordReset_includes_the_reset_token_in_the_email_body() {
        String token = "ABC123";
        service.sendPasswordReset(user, token);
        verify(emailSender).send(eq(user.getEmail()), contains(token));
    }

    @Test
    void sendWelcomeEmail_throwsNotificationException_and_logsError_whenEmailSenderFails() {
        doThrow(new RuntimeException("Email sending failed"))
                .when(emailSender).send(anyString(), anyString());
        assertThrows(NotificationException.class, () -> service.sendWelcomeEmail(user));
        verify(logger).log(contains("error"));
    }
}

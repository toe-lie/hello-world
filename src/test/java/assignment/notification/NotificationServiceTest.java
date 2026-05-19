package assignment.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest()
    @ValueSource(strings = {"test@example.com", "user@example.org"})
    void sendWelcomeEmail_sendsToCorrectAddress_forAnyUser(
            String email
    ) {
        User user = new User(email);
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

    @ParameterizedTest
    @ValueSource(strings = {
            "Connection refused",
            "Timeout exceeded",
            "Authentication failed"
    })
    void sendWelcomeEmail_logsErrorMessage_whenEmailSenderFails(String errorMessage) {
        doThrow(new RuntimeException(errorMessage))
                .when(emailSender).send(anyString(), anyString());

        assertThrows(NotificationException.class, () -> service.sendWelcomeEmail(user));
        verify(logger).log("error: Failed to send email to " + user.getEmail() + ": " + errorMessage);
    }
}

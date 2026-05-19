package assignment.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

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

    @Nested
    @DisplayName("sendWelcomeEmail")
    class SendWelcomeEmail {

        @ParameterizedTest()
        @ValueSource(strings = {"test@example.com", "user@example.org"})
        @DisplayName("sends to correct address for any user (parameterized)")
        void sendsToCorrectAddress(
                String email
        ) {
            User user = new User(email);
            service.sendWelcomeEmail(user);
            verify(emailSender).send(user.getEmail(), "Welcome to our website!");
        }

        @Nested
        @DisplayName("when email sender fails")
        class WhenEmailSenderFails {
            private final String errorMessage = "SMTP down";

            @BeforeEach
            void setUp() {
                doThrow(new RuntimeException(errorMessage))
                        .when(emailSender).send(anyString(), anyString());
            }

            @Test
            @DisplayName("throws NotificationException")
            void throwsNotificationException() {
                assertThrows(NotificationException.class, () -> service.sendWelcomeEmail(user));
            }

            @Test
            @DisplayName("logs the error message")
            void logsErrorMessage() {
                assertThrows(NotificationException.class, () -> service.sendWelcomeEmail(user));
                verify(logger).log("error: Failed to send email to " + user.getEmail() + ": " + errorMessage);
            }

            @ParameterizedTest
            @ValueSource(strings = {
                    "Connection refused",
                    "Timeout exceeded",
                    "Authentication failed"
            })
            @DisplayName("logs error messages for specific error messages")
            void logsErrorMessages(String errorMessage) {
                doThrow(new RuntimeException(errorMessage))
                        .when(emailSender).send(anyString(), anyString());
                assertThrows(NotificationException.class, () -> service.sendWelcomeEmail(user));
                verify(logger).log("error: Failed to send email to " + user.getEmail() + ": " + errorMessage);
            }
        }
    }

    @Nested
    @DisplayName("sendPasswordReset")
    class SendPasswordReset {
        @Test
        @DisplayName("sends the reset token")
        void sendsResetToken() {
            String token = "ABC123";
            service.sendPasswordReset(user, token);
            verify(emailSender).send(eq(user.getEmail()), contains(token));
        }
    }
}

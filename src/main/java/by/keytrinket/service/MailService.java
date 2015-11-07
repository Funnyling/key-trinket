package by.keytrinket.service;

import by.keytrinket.domain.User;
import org.springframework.scheduling.annotation.Async;

/**
 * @author ntishkevich
 */
public interface MailService {
    @Async
    void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);

    @Async
    void sendActivationEmail(User user, String baseUrl);

    @Async
    void sendPasswordResetMail(User user, String baseUrl);
}

package by.keytrinket.service.impl;

import by.keytrinket.config.KeyTrinketProperties;
import by.keytrinket.domain.User;
import by.keytrinket.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author ntishkevich
 */
@Service
public class MailServiceImpl implements MailService {
    private final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private KeyTrinketProperties keyTrinketProperties;

//    @Autowired
//    private JavaMailSenderImpl javaMailSender;
//
//    @Autowired
//    private MessageSource messageSource;

    @Async
    @Override
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug("Send e-mail[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                isMultipart, isHtml, to, subject, content);
    }

    @Async
    @Override
    public void sendActivationEmail(User user, String baseUrl) {
        log.debug("Sending activation e-mail to '{}'", user.getEmail());
    }

    @Async
    @Override
    public void sendPasswordResetMail(User user, String baseUrl) {
        log.debug("Sending password reset e-mail to '{}'", user.getEmail());
    }
}

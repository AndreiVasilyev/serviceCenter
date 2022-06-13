package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * The type Email service.
 */
public class EmailServiceImpl implements EmailService {

    private static final Logger log = LogManager.getLogger();
    private static final String LOCALE_FILE_NAME = "locale";
    private static final String GMAIL_PROPERTIES_FILE_NAME = "gmail.properties";
    private static final String LOGIN = "study.test.acc@gmail.com";
    private static final String FROM = "service_center@gmail.com";
    private static final String PASSWORD = "cxjeyfxhttfaohkq";
    private static final String FROM_LOCALE_KEY = "email.gmail.from";
    private static final String SUBJECT_LOCALE_KEY = "email.gmail.subject";
    private static final String MESSAGE_LOCALE_KEY = "email.gmail.message";
    private static final String MESSAGE_REPLACE_POINT_REGEX = "\\{\\}";

    @Override
    public void sendConfirmationCodeEmail(String toEmail, String code, Locale locale) throws ServiceException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE_FILE_NAME, locale);
        try {
            Address toAddress = new InternetAddress(toEmail);
            String subject = resourceBundle.getString(SUBJECT_LOCALE_KEY);
            String message = resourceBundle.getString(MESSAGE_LOCALE_KEY);
            String mailText = message.replaceAll(MESSAGE_REPLACE_POINT_REGEX, code);
            MimeMessage mimeMessage = initMessage();
            mimeMessage.setFrom(FROM);
            mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);
            mimeMessage.setSubject(subject);
            mimeMessage.setText(mailText);
            Transport.send(mimeMessage);
        } catch (IOException e) {
            log.error("Error loading property file {} when send email", LOCALE_FILE_NAME, e);
            throw new ServiceException("Error loading property file when send email", e);
        } catch (MessagingException e) {
            log.error("Error sending email to {}", toEmail, e);
            throw new ServiceException("Error sending email to" + toEmail, e);
        }
    }

    private MimeMessage initMessage() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(GMAIL_PROPERTIES_FILE_NAME);
        Properties properties = new Properties();
        properties.load(inputStream);
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(LOGIN, PASSWORD);
            }
        });
        return new MimeMessage(session);
    }
}





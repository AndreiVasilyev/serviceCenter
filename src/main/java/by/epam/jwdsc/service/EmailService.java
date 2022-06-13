package by.epam.jwdsc.service;

import by.epam.jwdsc.exception.ServiceException;

import java.util.Locale;

/**
 * The interface Email service.
 */
public interface EmailService {
    /**
     * Send confirmation code email.
     *
     * @param toEmail the to email
     * @param code    the code
     * @param locale  the locale
     * @throws ServiceException the service exception
     */
    void sendConfirmationCodeEmail(String toEmail, String code, Locale locale) throws ServiceException;
}

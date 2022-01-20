package by.epam.jwdsc.service;

import by.epam.jwdsc.exception.ServiceException;

import java.util.Locale;

public interface EmailService {
    void sendConfirmationCodeEmail(String toEmail, String code, Locale locale) throws ServiceException;
}

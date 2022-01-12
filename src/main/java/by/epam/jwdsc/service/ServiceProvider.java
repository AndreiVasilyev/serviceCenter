package by.epam.jwdsc.service;

import by.epam.jwdsc.service.impl.EmailServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;

import static by.epam.jwdsc.service.ServiceName.EMAIL;

public class ServiceProvider {

    private static final Logger log = LogManager.getLogger();
    private static ServiceProvider instance;
    private final EnumMap<ServiceName, ScService> services;

    private ServiceProvider() {
        services = new EnumMap<ServiceName, ScService>(ServiceName.class);
        services.put(EMAIL, new EmailServiceImpl());
    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    public EmailService getEmailService() {
        return (EmailService) services.get(EMAIL);
    }
}

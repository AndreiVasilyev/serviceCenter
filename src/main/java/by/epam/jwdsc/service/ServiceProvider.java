package by.epam.jwdsc.service;

import by.epam.jwdsc.service.impl.ConfirmationCodeServiceImpl;
import by.epam.jwdsc.service.impl.EmailServiceImpl;
import by.epam.jwdsc.service.impl.EmployeeServiceImpl;
import by.epam.jwdsc.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceProvider {

    private static final Logger log = LogManager.getLogger();
    private static ServiceProvider instance;
    private final EmailService emailService = new EmailServiceImpl();
    private final ConfirmationCodeService confirmationCodeService = new ConfirmationCodeServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public ConfirmationCodeService getConfirmationCodeService() {
        return confirmationCodeService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }
}

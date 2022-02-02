package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.RequestParameter;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.controller.command.SessionAttribute;
import by.epam.jwdsc.entity.Order;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.ConfirmationCodeService;
import by.epam.jwdsc.service.EmailService;
import by.epam.jwdsc.service.OrderService;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.util.VerifyCodeGenerator;
import by.epam.jwdsc.validator.Validator;
import by.epam.jwdsc.validator.impl.ValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static by.epam.jwdsc.controller.command.PagePath.ERROR_PAGE;
import static by.epam.jwdsc.controller.command.ResponseJsonText.*;
import static by.epam.jwdsc.controller.command.Router.RouterType.JSON;
import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

public class SendCodeCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Validator validator = ValidatorImpl.getInstance();
        String toEmail = request.getParameter(RequestParameter.EMAIL_PARAM);
        String orderNumber = request.getParameter(RequestParameter.ORDER_NUMBER_PARAM);
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(SessionAttribute.LOCALE);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE_FILE_NAME, locale);
        String responseText;
        if (validator.isEmailValid(toEmail) && validator.isOrderNumberValid(orderNumber)) {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            EmailService emailService = serviceProvider.getEmailService();
            ConfirmationCodeService confirmationCodeService = serviceProvider.getConfirmationCodeService();
            OrderService orderService = serviceProvider.getOrderService();
            try {
                Optional<Order> order = orderService.findOrderByOrderNumber(orderNumber);
                if (order.isPresent() && order.get().getClient().getEmail().equalsIgnoreCase(toEmail)) {
                    String confirmationCode = VerifyCodeGenerator.generateCode();
                    confirmationCodeService.saveCode(confirmationCode, toEmail);
                    emailService.sendConfirmationCodeEmail(toEmail, confirmationCode, locale);
                    responseText = POSITIVE_RESPONSE;
                } else {
                    log.error("Error executing command Send Code. There is no orders {} with email {}", orderNumber, toEmail);
                    String errorMessage = resourceBundle.getString(NOMATCH_CODE_EMAIL_LOCAL_KEY);
                    responseText = Strings.concat(NEGATIVE_RESPONSE, errorMessage);
                }
            } catch (ServiceException e) {
                log.error("Error executing command Send Code", e);
                session.setAttribute(EXCEPTION, e);
                return new Router(ERROR_PAGE, Router.RouterType.REDIRECT);
            }
        } else {
            log.error("Error executing command Send Code. Email value {} or order number {} is invalid", toEmail, orderNumber);
            String errorMessage = resourceBundle.getString(INVALID_ORDER_NUMBER_EMAIL_LOCAL_KEY);
            responseText = Strings.concat(NEGATIVE_RESPONSE, errorMessage);
        }
        return new Router(JSON, responseText);
    }
}

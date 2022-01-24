package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.controller.command.SessionAttribute;
import by.epam.jwdsc.entity.Order;
import by.epam.jwdsc.entity.UserRole;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.ConfirmationCodeService;
import by.epam.jwdsc.service.OrderService;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.util.GsonUtil;
import by.epam.jwdsc.validator.Validator;
import by.epam.jwdsc.validator.impl.ValidatorImpl;
import jakarta.servlet.http.Cookie;
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
import static by.epam.jwdsc.controller.command.RequestParameter.*;
import static by.epam.jwdsc.controller.command.ResponseJsonText.*;
import static by.epam.jwdsc.controller.command.SessionAttribute.*;

public class FindOrderByNumberCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(SessionAttribute.LOCALE);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE_FILE_NAME, locale);
        String currentRole = (String) session.getAttribute(USER_ROLE);
        Validator validator = ValidatorImpl.getInstance();
        String orderNumber = request.getParameter(ORDER_NUMBER_PARAM);
        String responseText;
        if (!validator.isOrderNumberValid(orderNumber)) {
            String errorMessage = resourceBundle.getString(INVALID_ORDER_NUMBER_LOCAL_KEY);
            responseText = Strings.concat(NEGATIVE_RESPONSE, errorMessage);
            return new Router(Router.RouterType.RESPONSE_BODY, GsonUtil.getGson().toJson(responseText));
        }
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        try {
            if (UserRole.GUEST.name().equalsIgnoreCase(currentRole)) {
                String email = request.getParameter(EMAIL_PARAM);
                String confirmationCode = request.getParameter(CODE_PARAM);
                if (!validator.isEmailValid(email) || !validator.isCodeValid(confirmationCode)) {
                    String errorMessage = resourceBundle.getString(INVALID_CODE_EMAIL_LOCAL_KEY);
                    responseText = Strings.concat(NEGATIVE_RESPONSE, errorMessage);
                    return new Router(Router.RouterType.RESPONSE_BODY, GsonUtil.getGson().toJson(responseText));
                }
                ConfirmationCodeService confirmationCodeService = serviceProvider.getConfirmationCodeService();
                boolean isVerifyCode = confirmationCodeService.verifyCode(confirmationCode, email);
                if (!isVerifyCode) {
                    String errorMessage = resourceBundle.getString(NOT_VERIFIED_CODE_LOCAL_KEY);
                    responseText = Strings.concat(NEGATIVE_RESPONSE, errorMessage);
                    return new Router(Router.RouterType.RESPONSE_BODY, GsonUtil.getGson().toJson(responseText));
                }
                session.removeAttribute(USER_ROLE);
                session.setAttribute(USER_ROLE, UserRole.CLIENT.name());
                session.setAttribute(CLIENT_LOGIN,email);
                Cookie cookieRole = new Cookie(USER_ROLE, UserRole.CLIENT.name());
                cookieRole.setMaxAge(COOKIE_AGE_MONTH);
                response.addCookie(cookieRole);
                Cookie cookieLogin = new Cookie(CLIENT_LOGIN, email);
                cookieLogin.setMaxAge(COOKIE_AGE_MONTH);
                response.addCookie(cookieLogin);
            }
            OrderService orderService = serviceProvider.getOrderService();
            Optional<Order> order = orderService.findOrderByOrderNumber(orderNumber);
            String clientLogin= (String) session.getAttribute(CLIENT_LOGIN);
            if (order.isEmpty() || !clientLogin.equalsIgnoreCase(order.get().getClient().getEmail())) {
                String message = resourceBundle.getString(ORDER_NOT_FOUND_LOCAL_KEY);
                responseText = Strings.concat(POSITIVE_RESPONSE, message);
                return new Router(Router.RouterType.RESPONSE_BODY, GsonUtil.getGson().toJson(responseText));
            }
            responseText = GsonUtil.getGson().toJson(order.get());
            return new Router(Router.RouterType.RESPONSE_BODY, responseText);
        } catch (ServiceException e) {
            log.error("Error executing command Find order by order number", e);
            return new Router(ERROR_PAGE, Router.RouterType.FORWARD);
        }
    }
}

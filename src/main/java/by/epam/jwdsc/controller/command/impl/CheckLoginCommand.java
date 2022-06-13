package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.controller.command.SessionAttribute;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.EmployeeService;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.util.GsonUtil;
import by.epam.jwdsc.validator.Validator;
import by.epam.jwdsc.validator.impl.ValidatorImpl;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

import static by.epam.jwdsc.controller.command.RequestParameter.*;
import static by.epam.jwdsc.controller.command.ResponseJsonText.*;
import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

/**
 * The type Check login command.
 */
public class CheckLoginCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(LOGIN_PARAM);
        Validator validator = ValidatorImpl.getInstance();
        HttpSession session = request.getSession();
        Gson gson = GsonUtil.getInstance().getGson();
        Locale locale = (Locale) session.getAttribute(SessionAttribute.LOCALE);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE_FILE_NAME, locale);
        try {
            if (validator.isLoginValid(login)) {
                EmployeeService employeeService = ServiceProvider.getInstance().getEmployeeService();
                if (employeeService.checkLogin(login)) {
                    String responseText = resourceBundle.getString(DUPLICATE_LOGIN_LOCAL_KEY);
                    return new Router(Router.RouterType.JSON, gson.toJson(NEGATIVE_RESPONSE.concat(responseText)));
                } else {
                    String responseText = resourceBundle.getString(UNIQUE_LOGIN_LOCAL_KEY);
                    return new Router(Router.RouterType.JSON, gson.toJson(POSITIVE_RESPONSE.concat(responseText)));
                }
            } else {
                String responseText = resourceBundle.getString(INVALID_LOGIN_LOCAL_KEY);
                return new Router(Router.RouterType.JSON, gson.toJson(NEGATIVE_RESPONSE.concat(responseText)));
            }
        } catch (ServiceException e) {
            log.error("Error execute command check login");
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}

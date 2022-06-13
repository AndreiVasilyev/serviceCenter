package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.controller.command.SessionAttribute;
import by.epam.jwdsc.entity.Employee;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.EmployeeService;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.validator.Validator;
import by.epam.jwdsc.validator.impl.ValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static by.epam.jwdsc.controller.command.RequestAttribute.REGISTERED_EMPLOYEE;
import static by.epam.jwdsc.controller.command.RequestParameter.*;
import static by.epam.jwdsc.controller.command.ResponseJsonText.*;
import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

/**
 * The type Registration first step command.
 */
public class RegistrationFirstStepCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession();
        String login = request.getParameter(LOGIN_PARAM);
        String role = request.getParameter(EMPLOYEE_ROLE_PARAM);
        if (login == null && role == null) {
            login = (String) httpSession.getAttribute(SessionAttribute.LOGIN);
            role = (String) httpSession.getAttribute(SessionAttribute.EMPLOYEE_ROLE);
        }
        request.removeAttribute(REGISTRATION_FIRST_STEP_FAILED_PARAM);
        Validator validator = ValidatorImpl.getInstance();
        Locale locale = (Locale) httpSession.getAttribute(SessionAttribute.LOCALE);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE_FILE_NAME, locale);
        if (validator.isLoginValid(login) && validator.isUserRoleValid(role)) {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            EmployeeService employeeService = serviceProvider.getEmployeeService();
            try {
                Optional<Employee> registeredEmployee = employeeService.findRegisteredEmployee(login, role);
                if (registeredEmployee.isPresent()) {
                    request.setAttribute(REGISTERED_EMPLOYEE, registeredEmployee.get());
                    httpSession.setAttribute(SessionAttribute.LOGIN, login);
                    httpSession.setAttribute(SessionAttribute.EMPLOYEE_ROLE, role);
                    return new Router(PagePath.REGISTRATION_PAGE_FINAL, Router.RouterType.FORWARD);
                } else {
                    String requestParameter = resourceBundle.getString(NOT_REGISTERED_LOGIN_LOCAL_KEY);
                    request.setAttribute(REGISTRATION_FIRST_STEP_FAILED_PARAM, requestParameter);
                    log.warn("Registration failed because of login not registered");
                    return new Router(PagePath.REGISTRATION_PAGE, Router.RouterType.FORWARD);
                }
            } catch (ServiceException e) {
                log.error("Error executing registration first step command", e);
                httpSession.setAttribute(EXCEPTION, e);
                return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
            }
        } else {
            String requestParameter = resourceBundle.getString(INVALID_LOGIN_USER_ROLE_LOCAL_KEY);
            request.setAttribute(REGISTRATION_FIRST_STEP_FAILED_PARAM, requestParameter);
            log.warn("Registration failed. Invalid login or role");
            return new Router(PagePath.REGISTRATION_PAGE, Router.RouterType.FORWARD);
        }
    }
}

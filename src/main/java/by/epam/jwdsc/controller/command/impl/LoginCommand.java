package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.entity.Employee;
import by.epam.jwdsc.entity.UserRole;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.EmployeeService;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.util.CookieUtil;
import by.epam.jwdsc.validator.Validator;
import by.epam.jwdsc.validator.impl.ValidatorImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static by.epam.jwdsc.controller.command.RequestParameter.*;
import static by.epam.jwdsc.controller.command.SessionAttribute.*;

public class LoginCommand implements Command {

    private static final Logger log = LogManager.getLogger();
    private static final String REMEMBER_PARAM_VALUE = "on";

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String remember = request.getParameter(REMEMBER_PARAM);
        Validator validator = ValidatorImpl.getInstance();
        if (validator.isLoginValid(login) && validator.isPasswordValid(password)) {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            EmployeeService employeeService = serviceProvider.getEmployeeService();
            Optional<Employee> employee = Optional.empty();
            try {
                employee = employeeService.authorize(login, password);
            } catch (ServiceException e) {
                log.error("Error executing Login command", e);
                return new Router(PagePath.ERROR_PAGE, Router.RouterType.FORWARD);
            }
            if (employee.isPresent()) {
                HttpSession httpSession = request.getSession();
                UserRole currentUserRole = employee.get().getUserRole();
                Long currentUserId = employee.get().getId();
                httpSession.setAttribute(USER_ROLE, currentUserRole);
                httpSession.setAttribute(EMPLOYEE_ID, currentUserId);
                CookieUtil cookieUtil = CookieUtil.getInstance();
                Cookie[] cookies = request.getCookies();
                if (REMEMBER_PARAM_VALUE.equals(remember)) {
                    cookieUtil.setCookie(cookies, USER_ROLE, currentUserRole.name())
                            .ifPresent(response::addCookie);
                    cookieUtil.setCookie(cookies, EMPLOYEE_ID, String.valueOf(currentUserId))
                            .ifPresent(response::addCookie);
                } else {
                    cookieUtil.remove(cookies, USER_ROLE, currentUserRole.name());
                    cookieUtil.remove(cookies, EMPLOYEE_ID, String.valueOf(currentUserId));
                }
                return new Router(PagePath.CONTROL_PAGE, Router.RouterType.FORWARD);
            } else {
                //return old values on login page wrong login or password
                return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
            }
        } else {
            return new Router(PagePath.LOGIN_PAGE, Router.RouterType.FORWARD);
            // return old values on login page invalid situation
        }
    }
}

package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.entity.UserRole;
import by.epam.jwdsc.service.EmployeeService;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.validator.Validator;
import by.epam.jwdsc.validator.impl.ValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static by.epam.jwdsc.controller.command.RequestParameter.*;

public class LoginCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        String remember = request.getParameter(REMEMBER_PARAM);
        Validator validator = ValidatorImpl.getInstance();
        if (validator.isLoginValid(login) && validator.isPasswordValid(password)) {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            EmployeeService employeeService = serviceProvider.getEmployeeService();
            Optional<UserRole> userRole = employeeService.authorize(login, password);
            if (userRole.isPresent()) {
                // change current role, redirect main page
            }
        } else {
            // return old values on login page
        }
        return new Router(PagePath.MAIN_PAGE, Router.RouterType.REDIRECT);
    }
}

package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.controller.command.SessionAttribute;
import by.epam.jwdsc.entity.Employee;
import by.epam.jwdsc.entity.UserBuilders;
import by.epam.jwdsc.entity.UserRole;
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
import org.apache.logging.log4j.util.Strings;

import java.util.Locale;
import java.util.ResourceBundle;

import static by.epam.jwdsc.controller.command.RequestParameter.*;
import static by.epam.jwdsc.controller.command.ResponseJsonText.*;
import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

/**
 * The type Register employee command.
 */
public class RegisterEmployeeCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(LOGIN_PARAM);
        String role = request.getParameter(EMPLOYEE_ROLE_PARAM);
        Validator validator = ValidatorImpl.getInstance();
        HttpSession session = request.getSession();
        Gson gson = GsonUtil.getInstance().getGson();
        Locale locale = (Locale) session.getAttribute(SessionAttribute.LOCALE);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE_FILE_NAME, locale);
        try {
            if (validator.isLoginValid(login) && validator.isUserRoleValid(role)) {
                UserRole employeeRole = UserRole.valueOf(role);
                Employee employee = UserBuilders.newEmployee()
                        .login(login)
                        .userRole(employeeRole)
                        .firstName(Strings.EMPTY)
                        .secondName(Strings.EMPTY)
                        .build();
                EmployeeService employeeService = ServiceProvider.getInstance().getEmployeeService();
                employeeService.createEmployee(employee);
                String responseText = resourceBundle.getString(SUCCESS_REGISTER_LOCAL_KEY);
                return new Router(Router.RouterType.JSON, gson.toJson(POSITIVE_RESPONSE.concat(responseText)));
            } else {
                String responseText = resourceBundle.getString(INVALID_LOGIN_USER_ROLE_LOCAL_KEY);
                return new Router(Router.RouterType.JSON, gson.toJson(NEGATIVE_RESPONSE.concat(responseText)));
            }
        } catch (ServiceException e) {
            log.error("Error execute command register employee");
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}

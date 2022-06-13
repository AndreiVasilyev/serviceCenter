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
import by.epam.jwdsc.util.GsonUtil;
import com.google.gson.Gson;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import static by.epam.jwdsc.controller.command.RequestParameter.*;
import static by.epam.jwdsc.controller.command.ResponseJsonText.POSITIVE_RESPONSE;
import static by.epam.jwdsc.controller.command.SessionAttribute.*;

/**
 * The type Change employee role command.
 */
public class ChangeEmployeeRoleCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        EmployeeService employeeService = serviceProvider.getEmployeeService();
        HttpSession session = request.getSession();
        String userId = request.getParameter(EMPLOYEE_ID_PARAM);
        String userRole = request.getParameter(EMPLOYEE_ROLE_PARAM);
        Gson gson = GsonUtil.getInstance().getGson();
        long id = Long.parseLong(userId);
        long currentUserId = (long) session.getAttribute(EMPLOYEE_ID);
        UserRole role = UserRole.valueOf(userRole);
        try {
            Employee employee = employeeService.findById(id).get();
            employee.setUserRole(role);
            employeeService.updateEmployee(employee);
            if (id == currentUserId) {
                session.setAttribute(USER_ROLE, role);
                Cookie[] cookies = request.getCookies();
                CookieUtil cookieUtil = CookieUtil.getInstance();
                if (cookies != null && !cookieUtil.getCookie(cookies, USER_ROLE).isEmpty()) {
                    Cookie cookie = new Cookie(USER_ROLE, userRole);
                    response.addCookie(cookie);
                }
            }
            String responseText = Strings.concat(POSITIVE_RESPONSE, userId);
            return new Router(Router.RouterType.JSON, gson.toJson(responseText));
        } catch (ServiceException e) {
            log.error("Error executing command update employee role", e);
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}

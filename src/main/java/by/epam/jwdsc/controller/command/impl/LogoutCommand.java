package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static by.epam.jwdsc.controller.command.PagePath.MAIN_PAGE;
import static by.epam.jwdsc.controller.command.Router.RouterType.FORWARD;
import static by.epam.jwdsc.controller.command.SessionAttribute.*;
import static by.epam.jwdsc.entity.UserRole.GUEST;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(USER_ROLE, GUEST);
        httpSession.removeAttribute(EMPLOYEE_ID);
        return new Router(MAIN_PAGE, FORWARD);
    }
}

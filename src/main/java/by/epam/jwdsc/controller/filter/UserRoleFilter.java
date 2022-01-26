package by.epam.jwdsc.controller.filter;

import by.epam.jwdsc.entity.UserRole;
import by.epam.jwdsc.util.CookieUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static by.epam.jwdsc.controller.command.SessionAttribute.*;
import static by.epam.jwdsc.entity.UserRole.*;

@WebFilter(urlPatterns = {"/control"}, servletNames = {"scServlet"})
public class UserRoleFilter implements Filter {
    private static final Logger log = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //define userRole session attribute when start session

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpRequest.getSession(true);
        Cookie[] cookies = httpRequest.getCookies();
        CookieUtil cookieUtil = CookieUtil.getInstance();
        if (httpSession.getAttribute(USER_ROLE) == null) {
            if (cookies == null || cookieUtil.getCookie(cookies, USER_ROLE).isEmpty()) {
                Cookie cookie = new Cookie(USER_ROLE, GUEST.name());
                cookie.setMaxAge(COOKIE_AGE_MONTH);
                httpResponse.addCookie(cookie);
                httpSession.setAttribute(USER_ROLE, GUEST);
            } else {
                Cookie cookieRole = cookieUtil.getCookie(cookies, USER_ROLE).get();
                UserRole userRole = UserRole.valueOf(cookieRole.getValue());
                httpSession.setAttribute(USER_ROLE, userRole);
                if (CLIENT == userRole) {
                    Cookie cookieLogin = cookieUtil.getCookie(cookies, CLIENT_LOGIN).get();
                    httpSession.setAttribute(CLIENT_LOGIN, cookieLogin.getValue());
                }
                if (ADMIN == userRole || INGINEER == userRole || MANAGER == userRole) {
                    Cookie cookieLogin = cookieUtil.getCookie(cookies, EMPLOYEE_ID).get();
                    httpSession.setAttribute(EMPLOYEE_ID, Long.parseLong(cookieLogin.getValue()));
                }
            }
        }
        chain.doFilter(request, response);
    }
}

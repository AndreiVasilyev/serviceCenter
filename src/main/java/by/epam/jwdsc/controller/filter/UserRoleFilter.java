package by.epam.jwdsc.controller.filter;

import by.epam.jwdsc.controller.command.PagePath;
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
        log.debug("Start UserRoleFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpRequest.getSession(true);
        if (httpSession.getAttribute(USER_ROLE) == null) {
            log.debug("Start define user role");
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            Cookie[] cookies = httpRequest.getCookies();
            CookieUtil cookieUtil = CookieUtil.getInstance();
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
                if (GUEST != userRole && CLIENT != userRole) {
                    Cookie cookieLogin = cookieUtil.getCookie(cookies, EMPLOYEE_ID).get();
                    httpSession.setAttribute(EMPLOYEE_ID, Long.parseLong(cookieLogin.getValue()));
                    log.debug("Start redirect to control_page from UserRoleFilter");
                    httpResponse.sendRedirect(PagePath.CONTROL_PAGE);
                    return;
                }
            }
        }
        log.debug("UserRoleFilterExecuted");
        chain.doFilter(request, response);
    }
}

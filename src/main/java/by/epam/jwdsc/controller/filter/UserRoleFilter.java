package by.epam.jwdsc.controller.filter;

import by.epam.jwdsc.entity.UserRole;
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
        if (httpSession.getAttribute(USER_ROLE) == null) {
            if (cookies == null || getCookie(cookies, USER_ROLE).isEmpty()) {
                Cookie cookie = new Cookie(USER_ROLE, GUEST.name());
                cookie.setMaxAge(COOKIE_AGE_MONTH);
                httpResponse.addCookie(cookie);
                httpSession.setAttribute(USER_ROLE, GUEST.name());
            } else {
                Cookie cookieRole = getCookie(cookies, USER_ROLE).get();
                httpSession.setAttribute(USER_ROLE, cookieRole.getValue());
            }
        }
        if (CLIENT.name().equalsIgnoreCase((String) httpSession.getAttribute(USER_ROLE))) {
            Cookie cookieLogin = getCookie(cookies, CLIENT_LOGIN).get();
            httpSession.setAttribute(CLIENT_LOGIN, cookieLogin.getValue());
        }
        chain.doFilter(request, response);
    }

    private Optional<Cookie> getCookie(Cookie[] cookies, String name) {
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}

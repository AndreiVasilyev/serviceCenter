package by.epam.jwdsc.controller.filter;

import by.epam.jwdsc.entity.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static by.epam.jwdsc.controller.command.SessionAttribute.COOKIE_AGE_MONTH;
import static by.epam.jwdsc.controller.command.SessionAttribute.USER_ROLE;

@WebFilter
public class UserRoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //define userRole session attribute when start session
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpRequest.getSession(true);
        if (httpSession.getAttribute(USER_ROLE) == null) {
            Cookie[] cookies = httpRequest.getCookies();
            Optional<Cookie> userRoleCookie = Arrays.stream(cookies)
                    .filter(c -> c.getName().equalsIgnoreCase(USER_ROLE))
                    .findFirst();
            if (userRoleCookie.isPresent()) {
                httpSession.setAttribute(USER_ROLE, userRoleCookie.get());
            } else {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                Cookie cookie = new Cookie(USER_ROLE, UserRole.GUEST.name());
                cookie.setMaxAge(COOKIE_AGE_MONTH);
                httpResponse.addCookie(cookie);
                httpSession.setAttribute(USER_ROLE, UserRole.GUEST.name());
            }
        }
        chain.doFilter(request, response);
    }
}

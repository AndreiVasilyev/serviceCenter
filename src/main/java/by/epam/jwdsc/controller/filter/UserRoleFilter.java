package by.epam.jwdsc.controller.filter;

import by.epam.jwdsc.entity.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;

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
            String userRole = Arrays.stream(cookies)
                    .filter(c -> c.getName().equalsIgnoreCase(USER_ROLE))
                    .map(Cookie::getName)
                    .findFirst()
                    .orElse(UserRole.GUEST.name());
            httpSession.setAttribute(USER_ROLE, userRole);
        }
        chain.doFilter(request,response);
    }
}

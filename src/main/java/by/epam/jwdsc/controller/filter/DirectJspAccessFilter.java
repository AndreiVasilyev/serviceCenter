package by.epam.jwdsc.controller.filter;

import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.entity.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static by.epam.jwdsc.controller.command.SessionAttribute.USER_ROLE;

@WebFilter(urlPatterns = "/jsp/*", initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp")})
public class DirectJspAccessFilter implements Filter {

    private static final Logger log = LogManager.getLogger();
    private static final String INDEX_PATH = "INDEX_PATH";
    private String indexPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter(INDEX_PATH);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (!httpRequest.getRequestURI().equals(PagePath.ERROR_PAGE) && !httpRequest.getRequestURI().equals(PagePath.LOGIN_PAGE)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
        }
        chain.doFilter(request, response);
    }
}

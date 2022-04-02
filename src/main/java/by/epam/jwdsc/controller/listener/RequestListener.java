package by.epam.jwdsc.controller.listener;

import by.epam.jwdsc.controller.command.CommandName;
import by.epam.jwdsc.entity.RequestData;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static by.epam.jwdsc.controller.command.CommandName.CHANGE_LOCALE;
import static by.epam.jwdsc.controller.command.RequestParameter.COMMAND_PARAM;
import static by.epam.jwdsc.controller.command.SessionAttribute.REQUEST_DATA;

@WebListener
public class RequestListener implements ServletRequestListener {

    private static final Logger log = LogManager.getLogger();
    private static final String DEFAULT_REQUEST_ENCODING = "UTF-8";
    private static final String REQUEST_REFERER = "referer";

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        //set UTF-8 encoding
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        try {
            servletRequest.setCharacterEncoding(DEFAULT_REQUEST_ENCODING);
        } catch (UnsupportedEncodingException e) {
            log.error("Error when request listener setting UTF-8 encoding ", e);
        }
        //save current request data in session scope
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String command = request.getParameter(COMMAND_PARAM);
        String requestReferer = request.getHeader(REQUEST_REFERER);
        if (command != null && requestReferer != null) {
            String requestURI = request.getRequestURI();
            String requestURL = request.getRequestURL().toString();
            String pagePath = requestReferer.replaceAll(requestURL.replaceAll(requestURI, ""), "");
            Map<String, String[]> parameters = request.getParameterMap();
            Map<String, Object> attributes = new HashMap<>();
            Iterator<String> iterator = request.getAttributeNames().asIterator();
            while (iterator.hasNext()) {
                String attributeName = iterator.next();
                Object attributeValue = request.getAttribute(attributeName);
                attributes.put(attributeName, attributeValue);
            }
            RequestData requestData = new RequestData(parameters, attributes, pagePath);
            HttpSession httpSession = request.getSession();
            httpSession.removeAttribute(REQUEST_DATA);
            httpSession.setAttribute(REQUEST_DATA, requestData);
        }
    }
}

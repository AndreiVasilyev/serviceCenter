package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.entity.RequestData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Locale;

import static by.epam.jwdsc.controller.command.RequestParameter.LOCALE_PARAM;
import static by.epam.jwdsc.controller.command.Router.RouterType.FORWARD;
import static by.epam.jwdsc.controller.command.Router.RouterType.REDIRECT;
import static by.epam.jwdsc.controller.command.SessionAttribute.*;

public class ChangeLocaleCommand implements Command {

    private static final String LOCALE_PARAMS_SEPARATOR = "_";

    @Override
    public Router execute(HttpServletRequest request) {
        String localeParameter = request.getParameter(LOCALE_PARAM);
        String[] localeParams = localeParameter.split(LOCALE_PARAMS_SEPARATOR);
        Locale locale = new Locale(localeParams[0], localeParams[1]);
        HttpSession session = request.getSession(true);
        session.setAttribute(LOCALE, locale);
        RequestData requestData = (RequestData) session.getAttribute(REQUEST_DATA);
        session.setAttribute("requestData", requestData);
        return new Router(requestData.getRequestPagePath(), REDIRECT);
    }
}

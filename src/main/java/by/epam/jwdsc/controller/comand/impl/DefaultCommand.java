package by.epam.jwdsc.controller.comand.impl;

import by.epam.jwdsc.controller.comand.Command;
import by.epam.jwdsc.controller.comand.PagePath;
import by.epam.jwdsc.controller.comand.Router;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
    }
}

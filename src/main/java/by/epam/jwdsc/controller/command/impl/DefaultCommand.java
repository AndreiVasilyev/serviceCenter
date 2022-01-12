package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

import static by.epam.jwdsc.controller.command.RequestParameter.COMMAND_PARAM;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
    }
}

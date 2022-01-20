package by.epam.jwdsc.controller.command.impl.gotocommand;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static by.epam.jwdsc.controller.command.PagePath.ERROR_PAGE;
import static by.epam.jwdsc.controller.command.Router.RouterType.FORWARD;

public class GotoErrorPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return new Router(ERROR_PAGE, FORWARD);
    }
}

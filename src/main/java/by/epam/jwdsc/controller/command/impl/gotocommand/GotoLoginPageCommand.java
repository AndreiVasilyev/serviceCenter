package by.epam.jwdsc.controller.command.impl.gotocommand;

import by.epam.jwdsc.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import static by.epam.jwdsc.controller.command.PagePath.LOGIN_PAGE;
import static by.epam.jwdsc.controller.command.Router.RouterType.FORWARD;

/**
 * The type Goto login page command.
 */
public class GotoLoginPageCommand implements by.epam.jwdsc.controller.command.Command {

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return new Router(LOGIN_PAGE, FORWARD);
    }
}

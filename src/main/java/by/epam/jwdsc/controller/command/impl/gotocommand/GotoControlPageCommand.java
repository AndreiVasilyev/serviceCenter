package by.epam.jwdsc.controller.command.impl.gotocommand;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.util.CookieUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.jwdsc.controller.command.PagePath.CONTROL_PAGE;
import static by.epam.jwdsc.controller.command.Router.RouterType.FORWARD;
import static by.epam.jwdsc.controller.command.SessionAttribute.USER_ROLE;

/**
 * The type Goto control page command.
 */
public class GotoControlPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return new Router(CONTROL_PAGE, FORWARD);
    }
}

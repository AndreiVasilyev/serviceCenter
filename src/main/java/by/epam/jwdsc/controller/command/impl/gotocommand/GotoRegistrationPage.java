package by.epam.jwdsc.controller.command.impl.gotocommand;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static by.epam.jwdsc.controller.command.PagePath.REGISTRATION_PAGE;
import static by.epam.jwdsc.controller.command.Router.RouterType.FORWARD;

/**
 * The type Goto registration page.
 */
public class GotoRegistrationPage implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return new Router(REGISTRATION_PAGE, FORWARD);
    }
}

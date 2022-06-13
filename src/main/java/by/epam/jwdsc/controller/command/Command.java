package by.epam.jwdsc.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute router.
     *
     * @param request  the request
     * @param response the response
     * @return the router
     */
    Router execute(HttpServletRequest request, HttpServletResponse response);
}

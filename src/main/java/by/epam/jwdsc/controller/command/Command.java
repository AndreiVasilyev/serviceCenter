package by.epam.jwdsc.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Command {
    Router execute(HttpServletRequest request, HttpServletResponse response);
}

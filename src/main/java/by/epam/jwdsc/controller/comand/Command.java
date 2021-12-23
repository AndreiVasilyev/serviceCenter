package by.epam.jwdsc.controller.comand;

import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    void execute(HttpServletRequest request);
}

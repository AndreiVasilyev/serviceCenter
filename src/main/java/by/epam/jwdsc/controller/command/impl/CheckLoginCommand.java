package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckLoginCommand implements Command {



    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}

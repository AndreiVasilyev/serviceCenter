package by.epam.jwdsc.controller;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.CommandProvider;
import by.epam.jwdsc.controller.command.Router;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


import static by.epam.jwdsc.controller.command.RequestParameter.COMMAND_PARAM;

@WebServlet(name = "scServlet", urlPatterns = "/control")
public class ScController extends HttpServlet {

    private static final Logger log = LogManager.getLogger();
    private static final CommandProvider commandProvider = CommandProvider.getInstance();
    private static int counter=0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("query#"+(++counter));
        String commandName = request.getParameter(COMMAND_PARAM);
        Command command = commandProvider.getCommand(commandName);
        Router router = command.execute(request);
        switch (router.getRouterType()) {
            case FORWARD -> {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(router.getPagePath());
                requestDispatcher.forward(request, response);
                break;
            }
            case REDIRECT -> {
                response.sendRedirect(router.getPagePath());
                break;
            }
            case RESPONSE_BODY -> {
                response.setContentType("text/plain");
                response.getWriter().write(router.getJson());
                break;
            }
        }
    }


}

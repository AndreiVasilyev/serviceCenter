package by.epam.jwdsc.controller;

import by.epam.jwdsc.controller.comand.Command;
import by.epam.jwdsc.controller.comand.CommandProvider;
import by.epam.jwdsc.controller.comand.Router;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "scServlet", urlPatterns = "/control")
public class ScController extends HttpServlet {

    private static final Logger log = LogManager.getLogger();
    private static final CommandProvider commandProvider = CommandProvider.getInstance();
    public static final String COMMAND = "command";
    public static final int ERROR_CODE = 500;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND);
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
        }
    }


}

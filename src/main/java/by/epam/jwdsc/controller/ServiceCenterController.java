package by.epam.jwdsc.controller;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.CommandProvider;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Collection;
import java.util.List;


import static by.epam.jwdsc.controller.command.RequestParameter.COMMAND_PARAM;

@WebServlet(name = "scServlet", urlPatterns = "/control")
@MultipartConfig
public class ServiceCenterController extends HttpServlet {

    private static final Logger log = LogManager.getLogger();
    private static final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = commandProvider.getCommand(request);
        Router router = command.execute(request, response);
        switch (router.getRouterType()) {
            case FORWARD -> {
                log.debug("Execute FORWARD to {}", router.getPagePath());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(router.getPagePath());
                requestDispatcher.forward(request, response);
                break;
            }
            case REDIRECT -> {
                log.debug("Execute REDIRECT to {}", router.getPagePath());
                response.sendRedirect(router.getPagePath());
                break;
            }
            case JSON -> {
                log.debug("Execute return JSON", router.getPagePath());
                response.setContentType("text/plain");
                response.getWriter().write(router.getJson());
                break;
            }
        }
    }
}

package by.epam.jwdsc.controller;

import by.epam.jwdsc.controller.comand.Command;
import by.epam.jwdsc.controller.comand.CommandProvider;
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

    private static final Logger log= LogManager.getLogger();
    private static final CommandProvider commandProvider= CommandProvider.getInstance();
    public static final String COMMAND = "command";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        String commandStr = request.getParameter(COMMAND);
        Command command = commandProvider.getCommand(commandStr);
      /*  String page = command.execute(request);

        if (page != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            request.getSession().setAttribute("nullPage", MessageManager.EN.getMessage("message.nullpage"));
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }*/
    }


}

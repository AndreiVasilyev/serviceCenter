package by.epam.jwdsc.controller.command.impl.findcommand;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.entity.Client;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.ClientService;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.util.GsonUtil;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.Optional;

import static by.epam.jwdsc.controller.command.RequestParameter.USER_ID_PARAM;
import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

public class FindClientByIdCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter(USER_ID_PARAM);
        long id = Long.parseLong(userId);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();
        HttpSession session=request.getSession();
        Gson gson = GsonUtil.getInstance().getGson();
        try {
            Optional<Client> client = clientService.findClientById(id);
            String gsonResult = Strings.EMPTY;
            if (client.isPresent()) {
                gsonResult = gson.toJson(client.get());
            }
            return new Router(Router.RouterType.JSON, gsonResult);
        } catch (ServiceException e) {
            log.error("Error executing command find client by id", e);
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}

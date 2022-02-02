package by.epam.jwdsc.controller.command.impl;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.util.List;

import static by.epam.jwdsc.controller.command.RequestParameter.FIND_PHONE_NUMBER_PARAM;

public class FindClientsByPhoneCommand implements Command {

    private static final Logger log = LogManager.getLogger();
    private static final String PHONE_DELIMITER = "-";

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String phoneNumber = request.getParameter(FIND_PHONE_NUMBER_PARAM);
        phoneNumber = phoneNumber.replaceAll(PHONE_DELIMITER, Strings.EMPTY);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ClientService clientService = serviceProvider.getClientService();
        Gson gson = GsonUtil.getInstance().getGson();
        try {
            List<Client> clients = clientService.findClientsByPhone(phoneNumber);
            log.debug("result: {}",clients);
            return new Router(Router.RouterType.JSON, gson.toJson(clients));
        } catch (ServiceException e) {
            log.error("Error executing command find clients by phone number", e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }

    }
}

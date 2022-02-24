package by.epam.jwdsc.controller.command.impl.findcommand;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.entity.Client;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.ClientService;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.util.GsonUtil;
import by.epam.jwdsc.validator.Validator;
import by.epam.jwdsc.validator.impl.ValidatorImpl;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.epam.jwdsc.controller.command.RequestParameter.FIND_PHONE_NUMBER_PARAM;
import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

public class FindClientsByPhoneCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String phoneNumber = request.getParameter(FIND_PHONE_NUMBER_PARAM);
        Validator validator = ValidatorImpl.getInstance();
        HttpSession session = request.getSession();
        Gson gson = GsonUtil.getInstance().getGson();
        try {
            if (validator.isPhoneNumberValid(phoneNumber)) {
                ServiceProvider serviceProvider = ServiceProvider.getInstance();
                ClientService clientService = serviceProvider.getClientService();
                List<Client> clients = clientService.findClientsByPhone(phoneNumber);
                return new Router(Router.RouterType.JSON, gson.toJson(clients));
            } else {
                log.error("Error executing command find clients by phone number. Invalid phone number");
                return new Router(Router.RouterType.JSON, gson.toJson("error:invalid phone number"));
            }
        } catch (ServiceException e) {
            log.error("Error executing command find clients by phone number", e);
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}

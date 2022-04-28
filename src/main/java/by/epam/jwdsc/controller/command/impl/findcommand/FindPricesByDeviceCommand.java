package by.epam.jwdsc.controller.command.impl.findcommand;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.controller.command.SessionAttribute;
import by.epam.jwdsc.entity.PriceInfo;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.PriceService;
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
import java.util.Locale;
import java.util.ResourceBundle;

import static by.epam.jwdsc.controller.command.RequestParameter.DEVICE_ID_PARAM;
import static by.epam.jwdsc.controller.command.ResponseJsonText.*;
import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

public class FindPricesByDeviceCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(SessionAttribute.LOCALE);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE_FILE_NAME, locale);
        Validator validator = ValidatorImpl.getInstance();
        String deviceIdParam = request.getParameter(DEVICE_ID_PARAM);
        Gson gson = GsonUtil.getInstance().getGson();
        try {
            if (validator.isIdValid(deviceIdParam)) {
                long deviceId = Long.parseLong(deviceIdParam);
                ServiceProvider serviceProvider = ServiceProvider.getInstance();
                PriceService priceService = serviceProvider.getPriceService();
                List<PriceInfo> prices = priceService.findPricesByDevice(deviceId);
                return new Router(Router.RouterType.JSON, gson.toJson(prices));
            } else {
                String responseText = resourceBundle.getString(INVALID_DEVICE_ID_LOCAL_KEY);
                return new Router(Router.RouterType.JSON, gson.toJson(NEGATIVE_RESPONSE.concat(responseText)));
            }
        } catch (ServiceException e) {
            log.error("Error execute command find prices by device Id");
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}

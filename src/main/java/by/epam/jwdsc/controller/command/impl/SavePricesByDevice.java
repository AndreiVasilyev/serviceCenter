package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.controller.command.SessionAttribute;
import by.epam.jwdsc.entity.dto.PricesData;
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

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static by.epam.jwdsc.controller.command.ResponseJsonText.*;
import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

/**
 * The type Save prices by device.
 */
public class SavePricesByDevice implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = GsonUtil.getInstance().getGson();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        PriceService priceService = serviceProvider.getPriceService();
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(SessionAttribute.LOCALE);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE_FILE_NAME, locale);
        try {
            PricesData pricesData = gson.fromJson(request.getReader(), PricesData.class);
            Validator validator = ValidatorImpl.getInstance();
            if (validator.isPricesDataValid(pricesData)) {
                priceService.savePricesByDevice(pricesData);
                String responseText = resourceBundle.getString(PRICES_SAVED_LOCAL_KEY);
                return new Router(Router.RouterType.JSON, gson.toJson(POSITIVE_RESPONSE.concat(responseText)));
            } else {
                String responseText = resourceBundle.getString(INVALID_PRICES_PARAMETER_LOCAL_KEY);
                return new Router(Router.RouterType.JSON, gson.toJson(NEGATIVE_RESPONSE.concat(responseText)));
            }
        } catch (IOException e) {
            log.error("Error reading JSON string from request");
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            log.error("Error execute command save prices by device");
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}

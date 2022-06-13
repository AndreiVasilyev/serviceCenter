package by.epam.jwdsc.controller.command.impl.findcommand;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.PriceService;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.util.GsonUtil;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.util.Optional;

import static by.epam.jwdsc.controller.command.RequestParameter.*;
import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

/**
 * The type Find work cost command.
 */
public class FindWorkCostCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String deviceId = request.getParameter(DEVICE_ID_PARAM);
        String repairLevel = request.getParameter(REPAIR_LEVEL_PARAM);
        HttpSession session = request.getSession();
        Gson gson = GsonUtil.getInstance().getGson();
        try {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            PriceService priceService = serviceProvider.getPriceService();
            Optional<BigDecimal> workCost = priceService.findCostByDeviceAndLevel(Long.parseLong(deviceId), repairLevel);
            String result = Strings.EMPTY;
            if (workCost.isPresent()) {
                result = String.valueOf(workCost.get());
            }
            return new Router(Router.RouterType.JSON, gson.toJson(result));
        } catch (ServiceException e) {
            log.error("Error executing command find work cost by device id and repair level", e);
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}

package by.epam.jwdsc.controller.command.impl.findcommand;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.entity.Device;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.DeviceService;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.util.GsonUtil;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

/**
 * The type Find all devices command.
 */
public class FindAllDevicesCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        DeviceService deviceService = serviceProvider.getDeviceService();
        Gson gson = GsonUtil.getInstance().getGson();
        try {
            List<Device> devices = deviceService.findAll();
            return new Router(Router.RouterType.JSON, gson.toJson(devices));
        } catch (ServiceException e) {
            log.error("Error executing command find all devices", e);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }

    }
}

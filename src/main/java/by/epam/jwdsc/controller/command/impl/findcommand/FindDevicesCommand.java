package by.epam.jwdsc.controller.command.impl.findcommand;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.entity.Device;
import by.epam.jwdsc.entity.dto.DeviceData;
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

import java.io.IOException;
import java.util.List;

import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

/**
 * The type Find devices command.
 */
public class FindDevicesCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = GsonUtil.getInstance().getGson();
        DeviceService deviceService = ServiceProvider.getInstance().getDeviceService();
        HttpSession session = request.getSession();
        try {
            DeviceData deviceParams = gson.fromJson(request.getReader(), DeviceData.class);
            List<Device> devices = deviceService.findDevicesByParameters(deviceParams);
            return new Router(Router.RouterType.JSON, gson.toJson(devices));
        } catch (IOException e) {
            log.error("Error reading JSON string from request");
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            log.error("Error execute command find devices by parameters");
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}

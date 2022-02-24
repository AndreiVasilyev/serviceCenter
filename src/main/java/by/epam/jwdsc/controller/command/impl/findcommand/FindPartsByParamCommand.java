package by.epam.jwdsc.controller.command.impl.findcommand;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.entity.SparePart;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.service.SparePartService;
import by.epam.jwdsc.util.GsonUtil;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.epam.jwdsc.controller.command.RequestParameter.FIND_PART_PARAM;
import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

public class FindPartsByParamCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String partParameter = request.getParameter(FIND_PART_PARAM);
        HttpSession session = request.getSession();
        Gson gson = GsonUtil.getInstance().getGson();
        try {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            SparePartService sparePartService = serviceProvider.getSparePartService();
            List<SparePart> parts = sparePartService.findPartsByParam(partParameter);
            return new Router(Router.RouterType.JSON, gson.toJson(parts));
        } catch (ServiceException e) {
            log.error("Error executing command find spare parts by parameter", e);
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}

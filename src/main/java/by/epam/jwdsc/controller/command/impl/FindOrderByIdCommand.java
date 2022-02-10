package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.entity.Order;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.OrderService;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.util.GsonUtil;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.util.Optional;

import static by.epam.jwdsc.controller.command.RequestParameter.ORDER_ID_PARAM;
import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

public class FindOrderByIdCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter(ORDER_ID_PARAM);
        long id = Long.parseLong(orderId);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();
        HttpSession session = request.getSession();
        Gson gson = GsonUtil.getInstance().getGson();
        try {
            Optional<Order> order = orderService.findOrderById(id);
            String gsonResult = Strings.EMPTY;
            if (order.isPresent()) {
                gsonResult = gson.toJson(order.get());
            }
            return new Router(Router.RouterType.JSON, gsonResult);
        } catch (ServiceException e) {
            log.error("Error executing command find order by id", e);
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}

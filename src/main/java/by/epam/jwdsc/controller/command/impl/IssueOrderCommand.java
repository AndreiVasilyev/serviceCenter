package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.entity.Order;
import by.epam.jwdsc.entity.OrderStatus;
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

import java.time.LocalDateTime;

import static by.epam.jwdsc.controller.command.RequestParameter.ORDER_ID_PARAM;
import static by.epam.jwdsc.controller.command.ResponseJsonText.POSITIVE_RESPONSE;
import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

/**
 * The type Issue order command.
 */
public class IssueOrderCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();
        String orderId = request.getParameter(ORDER_ID_PARAM);
        HttpSession session = request.getSession();
        Gson gson = GsonUtil.getInstance().getGson();
        long id = Long.parseLong(orderId);
        try {
            Order order = orderService.findOrderById(id).get();
            order.setOrderStatus(OrderStatus.ISSUED);
            LocalDateTime issueDate = LocalDateTime.now();
            order.setIssueDate(issueDate);
            orderService.updateOrder(order);
            return new Router(Router.RouterType.JSON, gson.toJson(POSITIVE_RESPONSE));
        } catch (ServiceException e) {
            log.error("Error executing command issue order", e);
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}

package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.entity.Order;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.entity.dto.OrderParameters;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.OrderService;
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

import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

public class SaveNewOrderCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = GsonUtil.getInstance().getGson();
        OrderService orderService = ServiceProvider.getInstance().getOrderService();
        HttpSession session = request.getSession();
        try {
            NewOrderData newOrderData = gson.fromJson(request.getReader(), NewOrderData.class);
            Validator validator= ValidatorImpl.getInstance();
            if(validator.isNewOrderDataValid(newOrderData)){
                //call service to save data
                //List<Order> orders = orderService.findOrdersByParameters(orderParameters);
            }else{
                // return message about invalid data
            }
            return new Router(Router.RouterType.JSON, gson.toJson(orders));
        } catch (IOException e) {
            log.error("Error reading JSON string from request");
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            log.error("Error execute command save new order");
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }



    }
}

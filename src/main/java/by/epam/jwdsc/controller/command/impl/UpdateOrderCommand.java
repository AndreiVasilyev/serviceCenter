package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.entity.dto.OrderData;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.*;
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
import java.util.ArrayList;
import java.util.List;

import static by.epam.jwdsc.controller.command.SessionAttribute.*;

public class UpdateOrderCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = GsonUtil.getInstance().getGson();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();
        HttpSession session = request.getSession();
        try {
            OrderData orderData = gson.fromJson(request.getReader(), OrderData.class);
            log.debug("created orderData class");
            Validator validator = ValidatorImpl.getInstance();
            if (validator.isOrderDataValid(orderData)) {
                List<String> phones = new ArrayList<>();
                phones.add(orderData.getPhoneFirst());
                if (!orderData.getPhoneSecond().isBlank()) {
                    phones.add(orderData.getPhoneSecond());
                }
                if (!orderData.getPhoneThird().isBlank()) {
                    phones.add(orderData.getPhoneThird());
                }
                log.debug("created phones list");
                AddressService addressService = serviceProvider.getAddressService();
                addressService.updateAddress(orderData);
                log.debug("address updated");
                ClientService clientService = serviceProvider.getClientService();
                clientService.updateClient(orderData, phones);
                log.debug("client updated");
                orderService.updateOrder(orderData, (long) session.getAttribute(EMPLOYEE_ID));
                log.debug("order updated");
                return new Router(Router.RouterType.JSON, gson.toJson("ok:order was updated"));
            } else {
                return new Router(Router.RouterType.JSON, gson.toJson("error: invalid order parameters"));
            }
        } catch (IOException e) {
            log.error("Error reading JSON string from request");
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            log.error("Error execute command update order");
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}

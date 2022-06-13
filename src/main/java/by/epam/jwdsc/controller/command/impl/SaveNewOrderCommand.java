package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.controller.command.SessionAttribute;
import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.entity.dto.NewOrderData;
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
import java.util.Locale;
import java.util.ResourceBundle;

import static by.epam.jwdsc.controller.command.ResponseJsonText.*;
import static by.epam.jwdsc.controller.command.SessionAttribute.*;

/**
 * The type Save new order command.
 */
public class SaveNewOrderCommand implements Command {

    private static final Logger log = LogManager.getLogger();
    private static final String DEFAULT_PARAM_VALUE = "0";

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = GsonUtil.getInstance().getGson();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(SessionAttribute.LOCALE);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE_FILE_NAME, locale);
        try {
            NewOrderData newOrderData = gson.fromJson(request.getReader(), NewOrderData.class);
            Validator validator = ValidatorImpl.getInstance();
            if (validator.isNewOrderDataValid(newOrderData)) {
                List<String> phones = new ArrayList<>();
                phones.add(newOrderData.getPhoneFirst());
                if (!newOrderData.getPhoneSecond().isBlank()) {
                    phones.add(newOrderData.getPhoneSecond());
                }
                if (!newOrderData.getPhoneThird().isBlank()) {
                    phones.add(newOrderData.getPhoneThird());
                }
                ClientService clientService = serviceProvider.getClientService();
                long clientId;
                if (newOrderData.getClientId().isBlank()) {
                    long addressId;
                    AddressService addressService = serviceProvider.getAddressService();
                    List<Address> existingAddresses = addressService.findAddressesByParams(newOrderData);
                    if (existingAddresses.isEmpty()) {
                        addressId = addressService.createAddress(newOrderData);
                    } else {
                        addressId = existingAddresses.get(0).getId();
                    }
                    Address address = addressService.findById(addressId).get();
                    clientId = clientService.createClient(newOrderData, address, phones);
                    newOrderData.setClientId(String.valueOf(clientId));
                } else {
                    clientService.updateClient(newOrderData, phones);
                }
                if (newOrderData.getDeviceId().equals(DEFAULT_PARAM_VALUE)) {
                    DeviceService deviceService = serviceProvider.getDeviceService();
                    long deviceId = deviceService.createDevice(newOrderData.getDeviceName());
                    newOrderData.setDeviceId(String.valueOf(deviceId));
                }
                if (newOrderData.getCompanyId().equals(DEFAULT_PARAM_VALUE) && !newOrderData.getCompanyId().isBlank()) {
                    CompanyService companyService = serviceProvider.getCompanyService();
                    long companyId = companyService.createCompany(newOrderData.getCompanyName(), false);
                    newOrderData.setCompanyId(String.valueOf(companyId));
                }
                orderService.createNewOrder(newOrderData, (long) session.getAttribute(EMPLOYEE_ID));
                String responseText = resourceBundle.getString(ORDER_CREATED_LOCAL_KEY);
                return new Router(Router.RouterType.JSON, gson.toJson(POSITIVE_RESPONSE.concat(responseText)));
            } else {
                String responseText = resourceBundle.getString(INVALID_ORDER_PARAMETER_LOCAL_KEY);
                return new Router(Router.RouterType.JSON, gson.toJson(NEGATIVE_RESPONSE.concat(responseText)));
            }
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

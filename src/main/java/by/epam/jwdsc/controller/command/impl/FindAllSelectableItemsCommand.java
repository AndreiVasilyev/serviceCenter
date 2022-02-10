package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.entity.RepairLevel;
import by.epam.jwdsc.entity.dto.SelectableItems;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.CompanyService;
import by.epam.jwdsc.service.DeviceService;
import by.epam.jwdsc.service.EmployeeService;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.util.GsonUtil;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Stream;

import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

public class FindAllSelectableItemsCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        CompanyService companyService = serviceProvider.getCompanyService();
        DeviceService deviceService = serviceProvider.getDeviceService();
        EmployeeService employeeService = serviceProvider.getEmployeeService();

        Gson gson = GsonUtil.getInstance().getGson();
        SelectableItems selectableItems = new SelectableItems();
        try {
            selectableItems.setCompanies(companyService.findAll());
            selectableItems.setDevices(deviceService.findAll());
            selectableItems.setEmployees(employeeService.findAll());
            List<String> levels = Stream.of(RepairLevel.values())
                    .map(RepairLevel::name)
                    .toList();
            selectableItems.setLevels(levels);
        } catch (ServiceException e) {
            log.error("Error executing command find all selectable items", e);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
        return new Router(Router.RouterType.JSON, gson.toJson(selectableItems));
    }
}

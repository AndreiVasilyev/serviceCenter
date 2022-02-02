package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.entity.dto.CompaniesAndDevices;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.CompanyService;
import by.epam.jwdsc.service.DeviceService;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.util.GsonUtil;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

public class FindAllCompaniesDevicesCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        CompanyService companyService = serviceProvider.getCompanyService();
        DeviceService deviceService = serviceProvider.getDeviceService();
        Gson gson = GsonUtil.getInstance().getGson();
        CompaniesAndDevices companiesAndDevices = new CompaniesAndDevices();
        try {
            companiesAndDevices.setCompanies(companyService.findAll());
            companiesAndDevices.setDevices(deviceService.findAll());
        } catch (ServiceException e) {
            log.error("Error executing command find all companies and devices", e);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE,Router.RouterType.REDIRECT);
        }
        return new Router(Router.RouterType.JSON, gson.toJson(companiesAndDevices));
    }
}

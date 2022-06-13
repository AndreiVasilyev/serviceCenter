package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.controller.command.SessionAttribute;
import by.epam.jwdsc.entity.dto.CompanyData;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.CompanyService;
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
import java.util.Locale;
import java.util.ResourceBundle;

import static by.epam.jwdsc.controller.command.ResponseJsonText.*;
import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

/**
 * The type Add new company command.
 */
public class AddNewCompanyCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Gson gson = GsonUtil.getInstance().getGson();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        CompanyService companyService = serviceProvider.getCompanyService();
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(SessionAttribute.LOCALE);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE_FILE_NAME, locale);
        try {
            CompanyData companyData = gson.fromJson(request.getReader(), CompanyData.class);
            Validator validator = ValidatorImpl.getInstance();
            if (validator.isCompanyNameValid(companyData.getName()) && validator.isContractValid(companyData.getIsContract())) {
                boolean isContract = Boolean.parseBoolean(companyData.getIsContract());
                companyService.createCompany(companyData.getName(), isContract);
                String responseText = resourceBundle.getString(COMPANY_CREATED_LOCAL_KEY);
                return new Router(Router.RouterType.JSON, gson.toJson(POSITIVE_RESPONSE.concat(responseText)));
            } else {
                String responseText = resourceBundle.getString(INVALID_COMPANY_PARAMETER_LOCAL_KEY);
                return new Router(Router.RouterType.JSON, gson.toJson(NEGATIVE_RESPONSE.concat(responseText)));
            }
        } catch (IOException e) {
            log.error("Error reading JSON string from request");
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            log.error("Error execute command save new company");
            session.setAttribute(EXCEPTION, e);
            return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
    }
}

package by.epam.jwdsc.controller.command.impl;

import by.epam.jwdsc.controller.command.Command;
import by.epam.jwdsc.controller.command.PagePath;
import by.epam.jwdsc.controller.command.Router;
import by.epam.jwdsc.controller.command.SessionAttribute;
import by.epam.jwdsc.entity.dto.EmployeeParameters;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.EmployeeService;
import by.epam.jwdsc.service.EntityMapper;
import by.epam.jwdsc.service.ServiceProvider;
import by.epam.jwdsc.validator.Validator;
import by.epam.jwdsc.validator.impl.ValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static by.epam.jwdsc.controller.command.RequestParameter.*;
import static by.epam.jwdsc.controller.command.ResponseJsonText.*;
import static by.epam.jwdsc.controller.command.SessionAttribute.EXCEPTION;

/**
 * The type Registration final command.
 */
public class RegistrationFinalCommand implements Command {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        String password = request.getParameter(PASSWORD_PARAM);
        String passwordConfirm = request.getParameter(PASSWORD_CONFIRM_PARAM);
        EmployeeParameters employeeParameters = extractEmployeeParameters(request);
        request.removeAttribute(REGISTRATION_FINAL_FAILED_PARAM);
        Validator validator = ValidatorImpl.getInstance();
        HttpSession httpSession = request.getSession();
        Locale locale = (Locale) httpSession.getAttribute(SessionAttribute.LOCALE);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE_FILE_NAME, locale);
        if (validator.isEmployeeValid(employeeParameters, password, passwordConfirm)) {
            try {
                ServiceProvider serviceProvider = ServiceProvider.getInstance();
                EmployeeService employeeService = serviceProvider.getEmployeeService();
                employeeService.registrationEmployee(employeeParameters, password);
                httpSession.removeAttribute(SessionAttribute.EMPLOYEE_ROLE);
                httpSession.removeAttribute(SessionAttribute.LOGIN);
                return new Router(PagePath.LOGIN_PAGE, Router.RouterType.REDIRECT);
            } catch (ServiceException e) {
                log.error("Error executing registration final command", e);
                httpSession.setAttribute(EXCEPTION, e);
                return new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
            }
        } else {
            String requestParameter = resourceBundle.getString(INVALID_EMPLOYEE_LOCAL_KEY);
            request.setAttribute(REGISTRATION_FINAL_FAILED_PARAM, requestParameter);
            log.warn("Registration failed. Invalid employee data");
            return new Router(PagePath.REGISTRATION_PAGE_FINAL, Router.RouterType.FORWARD);
        }
    }

    private EmployeeParameters extractEmployeeParameters(HttpServletRequest request) {
        EmployeeParameters employeeParameters = new EmployeeParameters();
        employeeParameters.setId(request.getParameter(USER_ID_PARAM));
        employeeParameters.setLogin(request.getParameter(LOGIN_PARAM));
        employeeParameters.setUserRole(request.getParameter(EMPLOYEE_ROLE_PARAM));
        employeeParameters.setFirstName(request.getParameter(EMPLOYEE_FIRST_NAME_PARAM));
        employeeParameters.setSecondName(request.getParameter(EMPLOYEE_SECOND_NAME_PARAM));
        employeeParameters.setPatronymic(request.getParameter(EMPLOYEE_PATRONYMIC_PARAM));
        employeeParameters.setEmail(request.getParameter(EMPLOYEE_EMAIL_PARAM));
        employeeParameters.setPostcode(request.getParameter(EMPLOYEE_POSTCODE_PARAM));
        employeeParameters.setCountry(request.getParameter(EMPLOYEE_COUNTRY_PARAM));
        employeeParameters.setState(request.getParameter(EMPLOYEE_STATE_PARAM));
        employeeParameters.setRegion(request.getParameter(EMPLOYEE_REGION_PARAM));
        employeeParameters.setCity(request.getParameter(EMPLOYEE_CITY_PARAM));
        employeeParameters.setStreet(request.getParameter(EMPLOYEE_STREET_PARAM));
        employeeParameters.setHouseNumber(request.getParameter(EMPLOYEE_HOUSE_NUMBER_PARAM));
        employeeParameters.setApartmentNumber(request.getParameter(EMPLOYEE_APARTMENT_NUMBER_PARAM));
        String phones = request.getParameter(EMPLOYEE_PHONE1_PARAM).concat(EntityMapper.PHONES_DELIMITER);
        phones = phones.concat(request.getParameter(EMPLOYEE_PHONE2_PARAM)).concat(EntityMapper.PHONES_DELIMITER);
        phones = phones.concat(request.getParameter(EMPLOYEE_PHONE3_PARAM));
        employeeParameters.setPhones(phones);
        return employeeParameters;
    }
}

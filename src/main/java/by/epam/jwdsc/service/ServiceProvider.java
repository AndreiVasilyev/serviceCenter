package by.epam.jwdsc.service;

import by.epam.jwdsc.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Service provider.
 */
public class ServiceProvider {

    private static ServiceProvider instance;
    private final EmailService emailService = new EmailServiceImpl();
    private final ConfirmationCodeService confirmationCodeService = new ConfirmationCodeServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();
    private final CompanyService companyService = new CompanyServiceImpl();
    private final DeviceService deviceService = new DeviceServiceImpl();
    private final ClientService clientService = new ClientServiceImpl();
    private final AddressService addressService = new AddressServiceImpl();
    private final SparePartService sparePartService = new SparePartServiceImpl();
    private final PriceService priceService = new PriceServiceImpl();

    private ServiceProvider() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    /**
     * Gets email service.
     *
     * @return the email service
     */
    public EmailService getEmailService() {
        return emailService;
    }

    /**
     * Gets confirmation code service.
     *
     * @return the confirmation code service
     */
    public ConfirmationCodeService getConfirmationCodeService() {
        return confirmationCodeService;
    }

    /**
     * Gets order service.
     *
     * @return the order service
     */
    public OrderService getOrderService() {
        return orderService;
    }

    /**
     * Gets employee service.
     *
     * @return the employee service
     */
    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    /**
     * Gets company service.
     *
     * @return the company service
     */
    public CompanyService getCompanyService() {
        return companyService;
    }

    /**
     * Gets device service.
     *
     * @return the device service
     */
    public DeviceService getDeviceService() {
        return deviceService;
    }

    /**
     * Gets client service.
     *
     * @return the client service
     */
    public ClientService getClientService() {
        return clientService;
    }

    /**
     * Gets address service.
     *
     * @return the address service
     */
    public AddressService getAddressService() {
        return addressService;
    }

    /**
     * Gets spare part service.
     *
     * @return the spare part service
     */
    public SparePartService getSparePartService() {
        return sparePartService;
    }

    /**
     * Gets price service.
     *
     * @return the price service
     */
    public PriceService getPriceService() {
        return priceService;
    }
}

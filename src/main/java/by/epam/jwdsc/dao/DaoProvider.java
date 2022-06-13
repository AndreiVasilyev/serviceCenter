package by.epam.jwdsc.dao;

import by.epam.jwdsc.dao.impl.*;

/**
 * The type Dao provider.
 */
public class DaoProvider {

    private static final DaoProvider instance = new DaoProvider();
    private ClientDao clientDao;
    private EmployeeDao employeeDao;
    private SparePartDao sparePartDao;
    private PriceInfoDao priceInfoDao;
    private DeviceDao deviceDao;
    private CompanyDao companyDao;
    private OrderDao orderDao;
    private CodeDao codeDao;
    private AddressDao addressDao;

    private DaoProvider() {
        this.clientDao = new ClientDaoImpl();
        this.employeeDao = new EmployeeDaoImpl();
        this.sparePartDao = new SparePartDaoImpl();
        this.priceInfoDao = new PriceInfoDaoImpl();
        this.deviceDao = new DeviceDaoImpl();
        this.companyDao = new CompanyDaoImpl();
        this.orderDao = new OrderDaoImpl();
        this.codeDao = new CodeDaoImpl();
        this.addressDao = new AddressDaoImpl();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DaoProvider getInstance() {
        return instance;
    }

    /**
     * Gets client dao.
     *
     * @return the client dao
     */
    public ClientDao getClientDao() {
        return clientDao;
    }

    /**
     * Gets employee dao.
     *
     * @return the employee dao
     */
    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    /**
     * Gets spare part dao.
     *
     * @return the spare part dao
     */
    public SparePartDao getSparePartDao() {
        return sparePartDao;
    }

    /**
     * Gets price info dao.
     *
     * @return the price info dao
     */
    public PriceInfoDao getPriceInfoDao() {
        return priceInfoDao;
    }

    /**
     * Gets device dao.
     *
     * @return the device dao
     */
    public DeviceDao getDeviceDao() {
        return deviceDao;
    }

    /**
     * Gets company dao.
     *
     * @return the company dao
     */
    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    /**
     * Gets order dao.
     *
     * @return the order dao
     */
    public OrderDao getOrderDao() {
        return orderDao;
    }

    /**
     * Gets code dao.
     *
     * @return the code dao
     */
    public CodeDao getCodeDao() {
        return codeDao;
    }

    /**
     * Gets address dao.
     *
     * @return the address dao
     */
    public AddressDao getAddressDao() {
        return addressDao;
    }
}

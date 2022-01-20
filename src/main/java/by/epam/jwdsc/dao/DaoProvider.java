package by.epam.jwdsc.dao;

import by.epam.jwdsc.dao.impl.*;
import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.entity.Device;

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

    private DaoProvider() {
        this.clientDao = new ClientDaoImpl();
        this.employeeDao = new EmployeeDaoImpl();
        this.sparePartDao = new SparePartDaoImpl();
        this.priceInfoDao = new PriceInfoDaoImpl();
        this.deviceDao = new DeviceDaoImpl();
        this.companyDao = new CompanyDapImpl();
        this.orderDao = new OrderDaoImpl();
        this.codeDao = new CodeDaoImpl();
    }

    public static DaoProvider getInstance() {
        return instance;
    }

    public ClientDao getClientDao() {
        return clientDao;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public SparePartDao getSparePartDao() {
        return sparePartDao;
    }

    public PriceInfoDao getPriceInfoDao() {
        return priceInfoDao;
    }

    public DeviceDao getDeviceDao() {
        return deviceDao;
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public CodeDao getCodeDao() {
        return codeDao;
    }
}

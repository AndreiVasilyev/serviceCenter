package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.*;
import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.entity.dto.OrderParameters;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static by.epam.jwdsc.dao.ColumnName.*;
import static by.epam.jwdsc.dao.TableAliasName.*;

public class OrderServiceImpl implements OrderService {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Optional<Order> findOrderById(long id) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        OrderDao orderDao = daoProvider.getOrderDao();
        try {
            Optional<Order> order = orderDao.findById(id);
            return order;
        } catch (DaoException e) {
            log.error("Error when find order by id in DB", e);
            throw new ServiceException("Error when find order by id in DB", e);
        }
    }

    @Override
    public Optional<Order> findOrderByOrderNumber(String orderNumber) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        OrderDao orderDao = daoProvider.getOrderDao();
        QueryParametersMapper queryParametersMapper = QueryParametersMapper.getInstance();
        LinkedHashMap<String, Object> parameters = queryParametersMapper.mapParameter(SC_ORDERS, ORDERS_ORDER_NUMBER, orderNumber);
        try {
            List<Order> orders = orderDao.findByParams(parameters);
            return !orders.isEmpty() ? Optional.of(orders.get(0)) : Optional.empty();
        } catch (DaoException e) {
            log.error("Error when find order by number in DB", e);
            throw new ServiceException("Error when find order by number in DB", e);
        }
    }

    @Override
    public List<Order> findOrdersByClientEmail(String email) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        OrderDao orderDao = daoProvider.getOrderDao();
        QueryParametersMapper queryParametersMapper = QueryParametersMapper.getInstance();
        LinkedHashMap<String, Object> parameters = queryParametersMapper.mapParameter(SC_USERS, USERS_EMAIL, email);
        try {
            return orderDao.findByParams(parameters);
        } catch (DaoException e) {
            log.error("Error when find orders by email in DB", e);
            throw new ServiceException("Error when find orders by email in DB", e);
        }
    }

    @Override
    public List<Order> findOrdersByParameters(OrderParameters orderParameters) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        OrderDao orderDao = daoProvider.getOrderDao();
        QueryParametersMapper queryParametersMapper = QueryParametersMapper.getInstance();
        LinkedHashMap<String, Object> parameters = queryParametersMapper.mapOrderParameters(orderParameters);
        String sort = queryParametersMapper.mapOrderSort(orderParameters);
        int pageNumber = Integer.parseInt(orderParameters.getPageNumber());
        try {
            return orderDao.findByParamsWithSortAndPage(parameters, sort, pageNumber);
        } catch (DaoException e) {
            log.error("Error when find orders by parameters in DB", e);
            throw new ServiceException("Error when find orders by parameters in DB", e);
        }
    }

    @Override
    public boolean createNewOrder(NewOrderData newOrderData, long employeeId) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        OrderDao orderDao = daoProvider.getOrderDao();
        ClientDao clientDao = daoProvider.getClientDao();
        EmployeeDao employeeDao = daoProvider.getEmployeeDao();
        DeviceDao deviceDao = daoProvider.getDeviceDao();
        CompanyDao companyDao = daoProvider.getCompanyDao();
        try {
            String lastOrderNumber = orderDao.findLastOrderNumber(newOrderData.getOrderNumber());
            long orderNumber = Long.parseLong(lastOrderNumber) + 1;
            String newOrderNumber = Strings.concat(newOrderData.getOrderNumber(), String.valueOf(orderNumber));
            Client client = clientDao.findById(Long.parseLong(newOrderData.getClientId())).get();
            Employee acceptedEmployee = employeeDao.findById(employeeId).get();
            LocalDateTime currentDate = LocalDateTime.now();
            Device device = deviceDao.findById(Long.parseLong(newOrderData.getDeviceId())).get();
            Company company = companyDao.findById(Long.parseLong(newOrderData.getCompanyId())).get();
            Order newOrder = new Order.Builder(0, newOrderNumber, currentDate, client, acceptedEmployee, device)
                    .company(company)
                    .model(newOrderData.getModel())
                    .serialNumber(newOrderData.getSerial())
                    .note(newOrderData.getNote())
                    .orderStatus(OrderStatus.ACCEPTED)
                    .build();
            return orderDao.create(newOrder);
        } catch (DaoException e) {
            log.error("Error in service when creating new order in DB", e);
            throw new ServiceException("Error in service when creating new order in DB", e);
        }
    }
}

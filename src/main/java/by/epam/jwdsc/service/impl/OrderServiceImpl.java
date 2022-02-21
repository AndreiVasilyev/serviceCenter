package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.*;
import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.entity.dto.OrderData;
import by.epam.jwdsc.entity.dto.OrderParameters;
import by.epam.jwdsc.entity.dto.OrdersWithPagination;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static by.epam.jwdsc.dao.ColumnName.*;
import static by.epam.jwdsc.dao.TableAliasName.*;

public class OrderServiceImpl implements OrderService {

    private static final Logger log = LogManager.getLogger();
    private static final String PARTS_SEPARATOR = " ";

    @Override
    public Optional<Order> findOrderById(long id) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        OrderDao orderDao = daoProvider.getOrderDao();
        try {
            return orderDao.findById(id);
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
    public OrdersWithPagination findOrdersByParameters(OrderParameters orderParameters) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        OrderDao orderDao = daoProvider.getOrderDao();
        OrdersWithPagination ordersWithPagination = new OrdersWithPagination();
        QueryParametersMapper queryParametersMapper = QueryParametersMapper.getInstance();
        LinkedHashMap<String, Object> parameters = queryParametersMapper.mapOrderParameters(orderParameters);
        String sort = queryParametersMapper.mapOrderSort(orderParameters);
        int pageNumber = Integer.parseInt(orderParameters.getPageNumber());
        try {
            long totalOrdersQuantity = orderDao.countOrdersByParams(parameters);
            List<Order> orders = orderDao.findByParamsWithSortAndPage(parameters, sort, pageNumber);
            ordersWithPagination.setOrders(orders);
            ordersWithPagination.setTotalOrdersQuantity(totalOrdersQuantity);
            ordersWithPagination.setCurrentPage(pageNumber);
            return ordersWithPagination;
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

    @Override
    public Optional<Order> updateOrder(OrderData orderData, long employeeId) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        OrderDao orderDao = daoProvider.getOrderDao();
        ClientDao clientDao = daoProvider.getClientDao();
        EmployeeDao employeeDao = daoProvider.getEmployeeDao();
        DeviceDao deviceDao = daoProvider.getDeviceDao();
        CompanyDao companyDao = daoProvider.getCompanyDao();
        try {
            long id = Long.parseLong(orderData.getId());
            Client client = clientDao.findById(Long.parseLong(orderData.getClientId())).get();
            Employee acceptedEmployee = employeeDao.findById(Long.parseLong(orderData.getAcceptedEmployeeId())).get();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm:ss");
            LocalDateTime creationDate = LocalDateTime.parse(orderData.getCreationDate(), formatter);
            Device device = deviceDao.findById(Long.parseLong(orderData.getDeviceId())).get();
            Company company = companyDao.findById(Long.parseLong(orderData.getCompanyId())).get();
            OrderStatus orderStatus = OrderStatus.valueOf(orderData.getOrderStatus());
            Order order = new Order.Builder(id, orderData.getOrderNumber(), creationDate, client, acceptedEmployee, device)
                    .company(company)
                    .model(orderData.getModel())
                    .serialNumber(orderData.getSerial())
                    .note(orderData.getNote())
                    .orderStatus(orderStatus)
                    .build();
            if (orderStatus != OrderStatus.ACCEPTED) {
                String completedEmployeeId = orderData.getCompletedEmployeeId();
                if (!completedEmployeeId.isBlank()) {
                    Employee completedEmployee = employeeDao.findById(Long.parseLong(completedEmployeeId)).get();
                    order.setCompletedEmployee(completedEmployee);
                }
                String repairLevel = orderData.getRepairLevel();
                if (!repairLevel.isBlank()) {
                    PriceInfoDao priceInfoDao = daoProvider.getPriceInfoDao();
                    PriceInfo priceInfo = priceInfoDao.findByDeviceAndLevel(device.getId(), repairLevel).get();
                    order.setWorkPrice(priceInfo);
                }
                String workDescription = orderData.getWorkDescription();
                if (!workDescription.isBlank()) {
                    order.setWorkDescription(workDescription);
                }
                String spareParts = orderData.getSpareParts();
                if (!spareParts.isBlank()) {
                    SparePartDao sparePartDao = daoProvider.getSparePartDao();
                    List<SparePart> parts = new ArrayList<>();
                    String[] partsId = spareParts.split(PARTS_SEPARATOR);
                    for (String part : partsId) {
                        long partId = Long.parseLong(part);
                        SparePart sparePart = sparePartDao.findById(partId).get();
                        parts.add(sparePart);
                    }
                    order.setSpareParts(parts);
                }
                String completionDate = orderData.getCompletionDate();
                if (!completionDate.isBlank()) {
                    LocalDateTime completionDateParsed = LocalDateTime.parse(completionDate, formatter);
                    order.setCompletionDate(completionDateParsed);
                }
                String issueDate = orderData.getIssueDate();
                if (!issueDate.isBlank()) {
                    LocalDateTime issueDateParsed = LocalDateTime.parse(issueDate, formatter);
                    order.setIssueDate(issueDateParsed);
                }
            }
            return orderDao.update(order);
        } catch (DaoException e) {
            log.error("Error in service when updating order in DB", e);
            throw new ServiceException("Error in service when updating order in DB", e);
        }
    }

    @Override
    public Optional<Order> updateOrder(Order order) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        OrderDao orderDao = daoProvider.getOrderDao();
        try {
            return orderDao.update(order);
        } catch (DaoException e) {
            log.error("Error in service when updating order in DB", e);
            throw new ServiceException("Error in service when updating order in DB", e);
        }
    }

    @Override
    public boolean removeOrderById(long id) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        OrderDao orderDao = daoProvider.getOrderDao();
        try {
            return orderDao.deleteById(id);
        } catch (DaoException e) {
            log.error("Error executing service to remove order by id", e);
            throw new ServiceException("Error executing service to remove order by id", e);
        }
    }
}

package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.*;
import by.epam.jwdsc.entity.Order;
import by.epam.jwdsc.entity.dto.OrderParameters;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static by.epam.jwdsc.dao.ColumnName.*;
import static by.epam.jwdsc.dao.TableAliasName.*;

public class OrderServiceImpl implements OrderService {

    private static final Logger log = LogManager.getLogger();

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
        log.debug("start service find command");
        DaoProvider daoProvider = DaoProvider.getInstance();
        OrderDao orderDao = daoProvider.getOrderDao();
        QueryParametersMapper queryParametersMapper = QueryParametersMapper.getInstance();
        log.debug("start mapping params");
        LinkedHashMap<String, Object> parameters = queryParametersMapper.mapOrderParameters(orderParameters);
        log.debug("start mapping sort");
        String sort=queryParametersMapper.mapOrderSort(orderParameters);
        log.debug("start mapping page");
        int pageNumber=Integer.parseInt(orderParameters.getPageNumber());
        try {
            log.debug("call dao find params");
            return orderDao.findByParamsWithSortAndPage(parameters,sort,pageNumber);
        } catch (DaoException e) {
            log.error("Error when find orders by parameters in DB", e);
            throw new ServiceException("Error when find orders by parameters in DB", e);
        }
    }


}

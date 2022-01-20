package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.ColumnName;
import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.dao.OrderDao;
import by.epam.jwdsc.dao.TableAliasName;
import by.epam.jwdsc.entity.Order;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static final Logger log = LogManager.getLogger();

    @Override
    public Optional<Order> findOrderByOrderNumber(String orderNumber) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        OrderDao orderDao = daoProvider.getOrderDao();
        try {
            List<Order> orders = orderDao.findByParam(TableAliasName.SC_ORDERS, ColumnName.ORDERS_ORDER_NUMBER, orderNumber);
            return orders.isEmpty()? Optional.empty():Optional.of(orders.get(0));
        } catch (DaoException e) {
            log.error("Error when find order by number in DB", e);
            throw new ServiceException("Error when find order by number in DB", e);
        }
    }

    @Override
    public List<Order> findOrdersByClientEmail(String email) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        OrderDao orderDao = daoProvider.getOrderDao();
        try {
           return orderDao.findByParam(TableAliasName.SC_USERS, ColumnName.USERS_EMAIL, email);
        } catch (DaoException e) {
            log.error("Error when find orders by email in DB", e);
            throw new ServiceException("Error when find orders by email in DB", e);
        }
    }
}

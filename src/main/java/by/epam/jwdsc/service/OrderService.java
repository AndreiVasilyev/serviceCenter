package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Order;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.entity.dto.OrderData;
import by.epam.jwdsc.entity.dto.OrderParameters;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> findOrderById(long id) throws ServiceException;

    Optional<Order> findOrderByOrderNumber(String orderNumber) throws ServiceException;

    List<Order> findOrdersByClientEmail(String email) throws ServiceException;

    List<Order> findOrdersByParameters(OrderParameters orderParameters) throws ServiceException;

    boolean createNewOrder(NewOrderData newOrderData, long attribute) throws ServiceException;

    Optional<Order> updateOrder(OrderData orderData, long employeeId) throws ServiceException;
}

package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Order;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<Order> findOrderByOrderNumber(String orderNumber) throws ServiceException;

    List<Order> findOrdersByClientEmail(String email) throws ServiceException;
}

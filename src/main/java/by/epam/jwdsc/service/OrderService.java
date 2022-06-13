package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Order;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.entity.dto.OrderData;
import by.epam.jwdsc.entity.dto.OrderParameters;
import by.epam.jwdsc.entity.dto.OrdersWithPagination;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface Order service.
 */
public interface OrderService {

    /**
     * Find order by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Order> findOrderById(long id) throws ServiceException;

    /**
     * Find order by order number optional.
     *
     * @param orderNumber the order number
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Order> findOrderByOrderNumber(String orderNumber) throws ServiceException;

    /**
     * Find orders by client email list.
     *
     * @param email the email
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findOrdersByClientEmail(String email) throws ServiceException;

    /**
     * Find orders by parameters orders with pagination.
     *
     * @param orderParameters the order parameters
     * @return the orders with pagination
     * @throws ServiceException the service exception
     */
    OrdersWithPagination findOrdersByParameters(OrderParameters orderParameters) throws ServiceException;

    /**
     * Create new order boolean.
     *
     * @param newOrderData the new order data
     * @param attribute    the attribute
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createNewOrder(NewOrderData newOrderData, long attribute) throws ServiceException;

    /**
     * Update order optional.
     *
     * @param orderData  the order data
     * @param employeeId the employee id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Order> updateOrder(OrderData orderData, long employeeId) throws ServiceException;

    /**
     * Update order optional.
     *
     * @param order the order
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Order> updateOrder(Order order) throws ServiceException;

    /**
     * Remove order by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean removeOrderById(long id) throws ServiceException;
}

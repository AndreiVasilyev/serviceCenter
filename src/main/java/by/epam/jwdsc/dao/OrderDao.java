package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Order;
import by.epam.jwdsc.exception.DaoException;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * The interface Order dao.
 */
public interface OrderDao extends BaseDao<Order> {

    /**
     * Count orders by params long.
     *
     * @param parameters the parameters
     * @return the long
     * @throws DaoException the dao exception
     */
    long countOrdersByParams(LinkedHashMap<String, Object> parameters) throws DaoException;

    /**
     * Find by params list.
     *
     * @param parameters the parameters
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findByParams(LinkedHashMap<String, Object> parameters) throws DaoException;

    /**
     * Find by params with sort and page list.
     *
     * @param parameters the parameters
     * @param sort       the sort
     * @param pageNumber the page number
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findByParamsWithSortAndPage(LinkedHashMap<String, Object> parameters, String sort, int pageNumber) throws DaoException;

    /**
     * Find last order number string.
     *
     * @param orderType the order type
     * @return the string
     * @throws DaoException the dao exception
     */
    String findLastOrderNumber(String orderType) throws DaoException;
}

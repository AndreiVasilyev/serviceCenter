package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Order;
import by.epam.jwdsc.exception.DaoException;

import java.util.LinkedHashMap;
import java.util.List;

public interface OrderDao extends BaseDao<Order> {

    List<Order> findByParams(LinkedHashMap<String, Object> parameters) throws DaoException;

    List<Order> findByParamsWithSortAndPage(LinkedHashMap<String, Object> parameters, String sort, int pageNumber) throws DaoException;
}

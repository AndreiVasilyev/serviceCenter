package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Order;
import by.epam.jwdsc.exception.DaoException;

import java.util.List;

public interface OrderDao extends BaseDao<Order> {

    <T> List<Order> findByParam(String paramTableName, String paramColumnName, T paramValue) throws DaoException;

}

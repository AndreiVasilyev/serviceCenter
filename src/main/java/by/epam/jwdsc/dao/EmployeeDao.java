package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Employee;
import by.epam.jwdsc.exception.DaoException;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * The interface Employee dao.
 */
public interface EmployeeDao extends BaseDao<Employee> {
    /**
     * Find by params list.
     *
     * @param parameters the parameters
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Employee> findByParams(LinkedHashMap<String, Object> parameters) throws DaoException;

    /**
     * Find by params with sort list.
     *
     * @param parameters the parameters
     * @param sort       the sort
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Employee> findByParamsWithSort(LinkedHashMap<String, Object> parameters, String sort) throws DaoException;
}

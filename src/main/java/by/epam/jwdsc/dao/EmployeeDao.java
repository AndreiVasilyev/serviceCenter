package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Employee;
import by.epam.jwdsc.exception.DaoException;

import java.util.LinkedHashMap;
import java.util.List;

public interface EmployeeDao extends BaseDao<Employee> {
    List<Employee> findByParams(LinkedHashMap<String, Object> parameters) throws DaoException;

    List<Employee> findByParamsWithSort(LinkedHashMap<String, Object> parameters, String sort) throws DaoException;
}

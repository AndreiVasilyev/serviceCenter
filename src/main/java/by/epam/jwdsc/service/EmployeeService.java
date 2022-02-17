package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Employee;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAll() throws ServiceException;

    Optional<Employee> findById(long id) throws ServiceException;

    Optional<Employee> authorize(String login, String password) throws ServiceException;
}

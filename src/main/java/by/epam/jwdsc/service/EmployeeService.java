package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Employee;
import by.epam.jwdsc.exception.ServiceException;

import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> authorize(String login, String password) throws ServiceException;
}

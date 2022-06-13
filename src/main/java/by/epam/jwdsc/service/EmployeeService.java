package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Employee;
import by.epam.jwdsc.entity.dto.EmployeeParameters;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface Employee service.
 */
public interface EmployeeService {

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Employee> findAll() throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Employee> findById(long id) throws ServiceException;

    /**
     * Find employees by parameters list.
     *
     * @param employeeParameters the employee parameters
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Employee> findEmployeesByParameters(EmployeeParameters employeeParameters) throws ServiceException;

    /**
     * Authorize optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Employee> authorize(String login, String password) throws ServiceException;

    /**
     * Find registered employee optional.
     *
     * @param login the login
     * @param role  the role
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Employee> findRegisteredEmployee(String login, String role) throws ServiceException;

    /**
     * Update employee optional.
     *
     * @param employee the employee
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Employee> updateEmployee(Employee employee) throws ServiceException;

    /**
     * Create employee boolean.
     *
     * @param employee the employee
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createEmployee(Employee employee) throws ServiceException;

    /**
     * Check login boolean.
     *
     * @param login the login
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean checkLogin(String login) throws ServiceException;

    /**
     * Registration employee optional.
     *
     * @param employeeParameters the employee parameters
     * @param password           the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Employee> registrationEmployee(EmployeeParameters employeeParameters, String password) throws ServiceException;
}

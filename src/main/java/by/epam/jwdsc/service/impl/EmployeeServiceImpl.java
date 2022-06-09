package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.dao.EmployeeDao;
import by.epam.jwdsc.dao.QueryParametersMapper;
import by.epam.jwdsc.entity.Employee;
import by.epam.jwdsc.entity.UserRole;
import by.epam.jwdsc.entity.dto.EmployeeParameters;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.EmployeeService;
import by.epam.jwdsc.service.EntityMapper;
import by.epam.jwdsc.util.PasswordHashGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import static by.epam.jwdsc.dao.ColumnName.*;
import static by.epam.jwdsc.dao.TableAliasName.*;

public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LogManager.getLogger();
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl() {
        this.employeeDao = DaoProvider.getInstance().getEmployeeDao();
    }

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() throws ServiceException {
        try {
            return employeeDao.findAll();
        } catch (DaoException e) {
            log.error("Error executing service find all employees", e);
            throw new ServiceException("Error executing service find all employees", e);
        }
    }

    @Override
    public Optional<Employee> findById(long id) throws ServiceException {
        try {
            return employeeDao.findById(id);
        } catch (DaoException e) {
            log.error("Error executing service find by Id employee", e);
            throw new ServiceException("Error executing service find by Id employee", e);
        }
    }

    @Override
    public List<Employee> findEmployeesByParameters(EmployeeParameters employeeParameters) throws ServiceException {
        QueryParametersMapper queryParametersMapper = QueryParametersMapper.getInstance();
        LinkedHashMap<String, Object> parameters = queryParametersMapper.mapEmployeeParameters(employeeParameters);
        String sort = queryParametersMapper.mapEmployeeSort(employeeParameters);
        try {
            return employeeDao.findByParamsWithSort(parameters, sort);
        } catch (DaoException e) {
            log.error("Error when find employees by parameters in DB", e);
            throw new ServiceException("Error when find employees by parameters in DB", e);
        }
    }

    @Override
    public Optional<Employee> authorize(String login, String password) throws ServiceException {
        QueryParametersMapper queryParametersMapper = QueryParametersMapper.getInstance();
        LinkedHashMap<String, Object> queryParameters = queryParametersMapper.mapParameter(SC_EMPLOYEES, EMPLOYEES_LOGIN, login);
        try {
            List<Employee> employees = employeeDao.findByParams(queryParameters);
            if (!employees.isEmpty()) {
                PasswordHashGenerator passwordHashGenerator = PasswordHashGenerator.getInstance();
                String savedPasswordHash = employees.get(0).getPassword();
                String salt = savedPasswordHash.split(PasswordHashGenerator.SALT_DELIMITER)[0];
                String passwordHash = passwordHashGenerator.hash(password, salt);
                if (savedPasswordHash.equals(passwordHash)) {
                    return Optional.of(employees.get(0));
                }
            }
            return Optional.empty();
        } catch (DaoException e) {
            log.error("Error find user when authorize employee", e);
            throw new ServiceException("Error find user when authorize employee", e);
        } catch (NoSuchAlgorithmException e) {
            log.error("Error encrypt password when authorize employee", e);
            throw new ServiceException("Error encrypt password when authorize employee", e);
        }
    }

    @Override
    public Optional<Employee> findRegisteredEmployee(String login, String role) throws ServiceException {
        QueryParametersMapper queryParametersMapper = QueryParametersMapper.getInstance();
        LinkedHashMap<String, Object> queryParameters = queryParametersMapper.mapParameter(SC_EMPLOYEES, EMPLOYEES_LOGIN, login);
        try {
            List<Employee> employees = employeeDao.findByParams(queryParameters);
            if (!employees.isEmpty()) {
                String savedPasswordHash = employees.get(0).getPassword();
                if (savedPasswordHash == null || savedPasswordHash.isBlank()) {
                    if (employees.get(0).getUserRole() == UserRole.valueOf(role)) {
                        return Optional.ofNullable(employees.get(0));
                    }
                }
            }
            return Optional.empty();
        } catch (DaoException e) {
            log.error("Error find employee when checking is login registered", e);
            throw new ServiceException("Error find employee when checking is login registered", e);
        }
    }

    @Override
    public Optional<Employee> updateEmployee(Employee employee) throws ServiceException {
        try {
            return employeeDao.update(employee);
        } catch (DaoException e) {
            log.error("Error executing service update Employee", e);
            throw new ServiceException("Error executing service update Employee", e);
        }
    }

    @Override
    public boolean createEmployee(Employee employee) throws ServiceException {
        try {
            return employeeDao.create(employee);
        } catch (DaoException e) {
            log.error("Error executing service create Employee", e);
            throw new ServiceException("Error executing service create Employee", e);
        }
    }

    @Override
    public boolean checkLogin(String login) throws ServiceException {
        QueryParametersMapper queryParametersMapper = QueryParametersMapper.getInstance();
        LinkedHashMap<String, Object> queryParameters = queryParametersMapper.mapParameter(SC_EMPLOYEES, EMPLOYEES_LOGIN, login);
        try {
            List<Employee> employees = employeeDao.findByParams(queryParameters);
            return !employees.isEmpty();
        } catch (DaoException e) {
            log.error("Error executing service check login", e);
            throw new ServiceException("Error executing service check login", e);
        }
    }

    @Override
    public Optional<Employee> registrationEmployee(EmployeeParameters employeeParameters, String password) throws ServiceException {
        try {
            PasswordHashGenerator passwordHashGenerator = PasswordHashGenerator.getInstance();
            String hashedPassword = passwordHashGenerator.hash(password);
            EntityMapper entityMapper = EntityMapper.getInstance();
            Employee employee = entityMapper.mapEmployee(employeeParameters, hashedPassword);
            return employeeDao.update(employee);
        } catch (DaoException e) {
            log.error("Error executing service registration Employee", e);
            throw new ServiceException("Error executing service registration Employee", e);
        } catch (NoSuchAlgorithmException e) {
            log.error("Error encrypt password when registration employee", e);
            throw new ServiceException("Error encrypt password when registration employee", e);
        } catch (NumberFormatException e) {
            log.error("Error when executing service registration Employee", e);
            throw new ServiceException("Error when executing service registration Employee", e);
        }
    }
}

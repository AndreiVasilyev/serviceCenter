package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.dao.EmployeeDao;
import by.epam.jwdsc.entity.Employee;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.EmployeeService;
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

    @Override
    public List<Employee> findAll() throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        EmployeeDao employeeDao = daoProvider.getEmployeeDao();
        try {
            return employeeDao.findAll();
        } catch (DaoException e) {
            log.error("Error executing service find all employees", e);
            throw new ServiceException("Error executing service find all employees", e);
        }
    }

    @Override
    public Optional<Employee> authorize(String login, String password) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        EmployeeDao employeeDao = daoProvider.getEmployeeDao();
        LinkedHashMap<String, Object> queryParameters = new LinkedHashMap<>();
        StringBuilder parameterBuilder = new StringBuilder(SC_EMPLOYEES);
        parameterBuilder.append(COLUMN_NAME_DELIMITER);
        parameterBuilder.append(EMPLOYEES_LOGIN);
        queryParameters.put(parameterBuilder.toString(), login);
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
}

package by.epam.jwdsc.dao.impl;

import by.epam.jwdsc.dao.EmployeeDao;
import by.epam.jwdsc.dao.UserDao;
import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.epam.jwdsc.dao.ColumnName.*;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class EmployeeDaoImpl extends UserDao implements EmployeeDao{

    private static final String SQL_SELECT_ALL_EMPLOYEES = "SELECT e.user_id, e.login, e.password, u.user_role, " +
            "u.first_name, u.second_name, u.patronymic, u.email, a.address_id, a.country, a.postcode, a.state, " +
            "a.region, a.city, a.street, a.house_number, a.apartment_number, " +
            "GROUP_CONCAT(p.phone_number) AS phone_number FROM employees AS e JOIN users AS u USING (user_id) " +
            "JOIN addresses AS a ON (u.address=a.address_id) JOIN phone_numbers AS p ON(u.user_id = p.user_id) " +
            "GROUP BY u.user_id";
    private static final String SQL_SELECT_EMPLOYEE_BY_ID = "SELECT e.user_id, e.login, e.password, u.user_role, " +
            "u.first_name, u.second_name, u.patronymic, u.email, a.address_id, a.country, a.postcode, a.state, " +
            "a.region, a.city, a.street, a.house_number, a.apartment_number, GROUP_CONCAT(p.phone_number) " +
            "AS phone_number FROM employees AS e JOIN users AS u USING (user_id) JOIN addresses AS a " +
            "ON (u.address=a.address_id) JOIN phone_numbers AS p ON(u.user_id = p.user_id) WHERE u.user_id=? " +
            "GROUP BY u.user_id";
    private static final String SQL_DELETE_EMPLOYEE_BY_ID = "DELETE e, u, a, p FROM employees AS e JOIN users AS u " +
            "USING (user_id) JOIN addresses AS a ON (u.address=a.address_id) JOIN phone_numbers AS p " +
            "ON(u.user_id = p.user_id) WHERE u.user_id=?";
    private static final String SQL_CREATE_ADDRESS = "INSERT INTO addresses(country, postcode, state, region, city," +
            "street, house_number, apartment_number) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_CREATE_USER = "INSERT INTO users(first_name, second_name, patronymic, address, " +
            "email,user_role) VALUES(?,?,?,?,?,?)";
    private static final String SQL_CREATE_EMPLOYEE = "INSERT INTO employees(user_id, login, password) " +
            "VALUES(?,?,?)";
    private static final String SQL_UPDATE_EMPLOYEE = "UPDATE employees AS e JOIN users AS u USING (user_id) " +
            "JOIN addresses AS a ON (u.address=a.address_id) SET e.login=?, e.password=?, u.user_role=?, u.first_name=?, " +
            "u.second_name=?, u.patronymic=?, u.email=?, a.country=?, a.postcode=?, a.state=?, a.region=?, a.city=?, " +
            "a.street=?, a.house_number=?, a.apartment_number=? WHERE u.user_id=?";


    @Override
    public List<Employee> findAll() throws DaoException {
        List<Employee> employees = new ArrayList<>();
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_EMPLOYEES)) {
            while (resultSet.next()) {
                Employee employee = extractEmployee(resultSet);
                employees.add(employee);
            }
        } catch (SQLException e) {
            log.error("Error executing query findAll from Employees", e);
            throw new DaoException("Error executing query findAll from Employees", e);
        } finally {
            close(connection);
        }
        return employees;
    }

    @Override
    public Optional<Employee> findById(long id) throws DaoException {
        Optional<Employee> employee = Optional.empty();
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    employee = Optional.of(extractEmployee(resultSet));
                }
            }
        } catch (SQLException e) {
            log.error("Error executing query findById from Employees", e);
            throw new DaoException("Error executing query findById from Employees", e);
        } finally {
            close(connection);
        }
        return employee;
    }

    @Override
    public boolean delete(Employee employee) throws DaoException {
        return deleteById(employee.getId());
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result = false;
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_EMPLOYEE_BY_ID)) {
            statement.setLong(1, id);
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Error executing query deleteById from Employees", e);
            throw new DaoException("Error executing query deleteById from Employees", e);
        } finally {
            close(connection);
        }
        return result;
    }

    @Override
    public boolean create(Employee employee) throws DaoException {
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statementNewAddress = connection.prepareStatement(SQL_CREATE_ADDRESS, RETURN_GENERATED_KEYS);
             PreparedStatement statementNewUser = connection.prepareStatement(SQL_CREATE_USER, RETURN_GENERATED_KEYS);
             PreparedStatement statementNewClient = connection.prepareStatement(SQL_CREATE_EMPLOYEE);
             PreparedStatement statementNewPhones = connection.prepareStatement(SQL_CREATE_PHONE_NUMBER)
        ) {
            connection.setAutoCommit(false);
            collectCreateAddressQuery(statementNewAddress, employee);
            statementNewAddress.executeUpdate();
            try (ResultSet generatedAddressKey = statementNewAddress.getGeneratedKeys()) {
                employee.getAddress().setId(generatedAddressKey.getLong(1));
                collectCreateUserQuery(statementNewUser, employee);
                statementNewUser.executeUpdate();
                try (ResultSet generatedUserKey = statementNewUser.getGeneratedKeys()) {
                    employee.setId(generatedUserKey.getLong(1));
                    collectCreateEmployeeQuery(statementNewClient, employee);
                    statementNewClient.executeUpdate();
                    if (employee.getPhones() != null && !employee.getPhones().isEmpty()) {
                        for (String phoneNumber : employee.getPhones()) {
                            statementNewPhones.setLong(1, employee.getId());
                            statementNewClient.setString(2, phoneNumber);
                            statementNewPhones.executeUpdate();
                        }
                    }
                    connection.commit();
                    connection.setAutoCommit(true);
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Transaction to create new Employee not executed. Rollback changes not executed", ex);
                throw new DaoException("Transaction to create new Employee not executed. Rollback changes not executed", ex);
            }
            log.error("Transaction to create new Employee not executed. All changes canceled", e);
            throw new DaoException("Transaction to create new Employee not executed. All changes canceled", e);
        } finally {
            close(connection);
        }
        return true;
    }

    @Override
    public Optional<Employee> update(Employee employee) throws DaoException {
        Optional<Employee> oldEmployeeFound=findById(employee.getId());
        if(oldEmployeeFound.isPresent()) {
            Employee oldEmployee=oldEmployeeFound.get();
            Connection connection = DbConnectionPool.INSTANCE.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_EMPLOYEE)) {
                collectUpdateEmployeeQuery(statement, employee);
                statement.executeUpdate();
                updatePhoneNumbers(connection,employee,oldEmployee);
            } catch (SQLException e) {
                log.error("Error executing query update Employee", e);
                throw new DaoException("Error executing query update Employee", e);
            } finally {
                close(connection);
            }
        }
        return oldEmployeeFound;
    }

    private void collectCreateEmployeeQuery(PreparedStatement statement, Employee employee) throws SQLException {
        statement.setLong(1, employee.getId());
        if (employee.getLogin() != null && !employee.getLogin().isBlank()) {
            statement.setString(2, employee.getLogin());
        } else {
            statement.setNull(2, Types.VARCHAR);
        }
        if (employee.getPassword() != null && !employee.getPassword().isBlank()) {
            statement.setString(3, employee.getPassword());
        } else {
            statement.setNull(3, Types.VARCHAR);
        }
    }

    private void collectUpdateEmployeeQuery(PreparedStatement statement, Employee employee) throws SQLException {
        statement.setString(1, employee.getLogin());
        statement.setString(2, employee.getPassword());
        statement.setString(3, employee.getUserRole().name());
        statement.setString(4, employee.getFirstName());
        statement.setString(5, employee.getSecondName());
        statement.setString(6, employee.getPatronymic());
        statement.setString(7, employee.getEmail());
        statement.setString(8, employee.getAddress().getCountry());
        statement.setInt(9, employee.getAddress().getPostcode());
        statement.setString(10, employee.getAddress().getState());
        statement.setString(11, employee.getAddress().getRegion());
        statement.setString(12, employee.getAddress().getCity());
        statement.setString(13, employee.getAddress().getStreet());
        statement.setInt(14, employee.getAddress().getHouseNumber());
        statement.setInt(15, employee.getAddress().getApartmentNumber());
        statement.setLong(16, employee.getId());
    }
}


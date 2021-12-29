package by.epam.jwdsc.dao.impl;

import by.epam.jwdsc.dao.OrderDao;
import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.epam.jwdsc.dao.ColumnName.*;
import static by.epam.jwdsc.dao.ColumnName.ADDRESSES_APARTMENT_NUMBER;

public class OrderDaoImpl implements OrderDao {

    private static final String SQL_SELECT_ALL_ORDERS = "SELECT o.order_id,o.order_number,o.order_status, " +
            "o.creation_date,o.client,o.accepted_employee,o.device, o.company,o.model,o.serial_number," +
            "o.completed_employee,o.completion_date,o.issue_date, o.work_description,o.work_price,o.note,c.user_id," +
            "c.discount,u.first_name,u.second_name, u.patronymic,u.address,u.email,a.country,a.postcode,a.state," +
            "a.region,a.city,a.street, a.house_number, a.apartment_number, d.device_name, co.name, " +
            "co.is_service_contract, GROUP_CONCAT(p.phone_number) AS phone_number FROM orders AS o " +
            "JOIN clients AS c ON(o.client=c.user_id) JOIN users AS u ON(o.client=u.user_id) " +
            "JOIN addresses AS a ON(a.address_id = u.address) JOIN devices AS d ON(d.device_id=o.device) " +
            "JOIN companies AS co ON(co.company_id=o.company) JOIN phone_numbers AS p ON(o.client=p.user_id) " +
            "GROUP BY o.order_id";
    private static final String SQL_SELECT_EMPLOYEE_BY_ID = "SELECT e.user_id, e.login, e.password, e.role, " +
            "u.first_name, u.second_name, u.patronymic, u.email, a.address_id, a.country, a.postcode, a.state, " +
            "a.region, a.city, a.street, a.house_number, a.apartment_number, GROUP_CONCAT(p.phone_number) " +
            "AS phone_number FROM employees AS e JOIN users AS u USING (user_id) JOIN addresses AS a " +
            "ON (u.address=a.address_id) JOIN phone_numbers AS p ON(u.user_id = p.user_id) WHERE u.user_id=? " +
            "GROUP BY u.user_id";
    private static final String SQL_SELECT_PRICE_BY_ID = "SELECT p.id, p.device_id, p.repair_level, p.repair_cost " +
            "FROM prices AS p WHERE p.id=? ";


    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orders = new ArrayList<>();
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_ID);
             PreparedStatement preparedStatementPrice = connection.prepareStatement(SQL_SELECT_PRICE_BY_ID);
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ORDERS)) {
            while (resultSet.next()) {
                String orderStatus = resultSet.getString(ORDERS_STATUS);
                long acceptedEmployeeId = resultSet.getLong(ORDERS_ACCEPTED_EMPLOYEE);
                preparedStatement.setLong(1, acceptedEmployeeId);
                ResultSet employeeResultSet = preparedStatement.executeQuery();
                Employee acceptedEmployee = extractEmployee(employeeResultSet);
                Employee completedEmployee = null;
                PriceInfo priceInfo = null;
                if (OrderStatus.CLOSED.name().equals(orderStatus) || OrderStatus.ISSUED.name().equals(orderStatus)) {
                    long completedEmployeeId = resultSet.getLong(ORDERS_COMPLETED_EMPLOYEE);
                    preparedStatement.setLong(1, completedEmployeeId);
                    employeeResultSet = preparedStatement.executeQuery();
                    completedEmployee = extractEmployee(employeeResultSet);
                    long workPriceId = resultSet.getLong(ORDERS_WORK_PRICE);
                    preparedStatementPrice.setLong(1, workPriceId);
                    ResultSet priceResultSet = preparedStatementPrice.executeQuery();
                    priceInfo = extractPrice(priceResultSet);
                }
                Order order = extractOrder(resultSet, acceptedEmployee, completedEmployee, priceInfo);
                orders.add(order);
            }
        } catch (SQLException e) {
            log.error("Error executing query findAll from Orders", e);
            throw new DaoException("Error executing query findAll from Orders", e);
        } finally {
            close(connection);
        }
        return orders;
    }

    @Override
    public Optional<Order> findById(long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Order order) throws DaoException {
        return false;
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Order order) throws DaoException {
        return false;
    }

    @Override
    public Order update(Order order) throws DaoException {
        return null;
    }

    private Order extractOrder(ResultSet resultSet, Employee acceptedEmployee, Employee completedEmployee, PriceInfo priceInfo) throws SQLException {
        long id = resultSet.getLong(ORDERS_ID);
        String orderNumber = resultSet.getString(ORDERS_ORDER_NUMBER);
        String orderStatus = resultSet.getString(ORDERS_STATUS);
        LocalDateTime creationDate = LocalDateTime.from(resultSet.getDate(ORDERS_CREATION_DATE).toLocalDate());
        Client client = extractClient(resultSet);
        long deviceId = resultSet.getLong(ORDERS_DEVICE);
        String deviceName = resultSet.getString(DEVICES_NAME);
        Device device = new Device(deviceId, deviceName);
        long companyId = resultSet.getLong(COMPANIES_ID);
        String companyName = resultSet.getString(COMPANIES_NAME);
        boolean isServiceContract = resultSet.getBoolean(COMPANIES_IS_SERVICE_CONTRACT);
        Company company = new Company(companyId, companyName, isServiceContract);
        String model = resultSet.getString(ORDERS_MODEL);
        String serialNumber = resultSet.getString(ORDERS_SERIAL_NUMBER);
        String note = resultSet.getString(ORDERS_NOTE);
        boolean isOrderClosed = OrderStatus.CLOSED.name().equals(orderStatus);
        boolean isOrderIssued = OrderStatus.ISSUED.name().equals(orderStatus);
        LocalDateTime completedDate = isOrderClosed ? LocalDateTime.from(resultSet.getDate(ORDERS_COMPLETION_DATE).toLocalDate()) : null;
        String workDescription = isOrderClosed ? resultSet.getString(ORDERS_WORK_DESCRIPTION) : null;
        LocalDateTime issueDate = isOrderIssued ? LocalDateTime.from(resultSet.getDate(ORDERS_ISSUE_DATE).toLocalDate()) : null;
        return new Order.Builder(id, orderNumber, creationDate, client, acceptedEmployee, device)
                .orderStatus(OrderStatus.valueOf(orderStatus))
                .company(company)
                .model(model)
                .serialNumber(serialNumber)
                .note(note)
                .comletionDate(completedDate)
                .completedEmployee(completedEmployee)
                .workDescription(workDescription)
                .workPrice(priceInfo)
                .issueDate(issueDate)
                .build();
    }

    private Client extractClient(ResultSet resultSet) throws SQLException {
        Address address = extractAddress(resultSet);
        long id = resultSet.getLong(USERS_ID);
        String firstName = resultSet.getString(USERS_FIRST_NAME);
        String secondName = resultSet.getString(USERS_SECOND_NAME);
        String patronymic = resultSet.getString(USERS_PATRONYMIC);
        String email = resultSet.getString(USERS_EMAIL);
        int discount = resultSet.getInt(CLIENTS_DISCOUNT);
        String phones = resultSet.getString(PHONE_NUMBERS_NUMBER);
        List<String> phoneNumbers = extractPhones(phones);
        return UserBuilders.newClient()
                .id(id)
                .firstName(firstName)
                .secondName(secondName)
                .patronymic(patronymic)
                .address(address)
                .email(email)
                .discount(discount)
                .phones(phoneNumbers)
                .build();
    }

    private Employee extractEmployee(ResultSet resultSet) throws SQLException {
        Address address = extractAddress(resultSet);
        long id = resultSet.getLong(USERS_ID);
        String firstName = resultSet.getString(USERS_FIRST_NAME);
        String secondName = resultSet.getString(USERS_SECOND_NAME);
        String patronymic = resultSet.getString(USERS_PATRONYMIC);
        String email = resultSet.getString(USERS_EMAIL);
        String login = resultSet.getString(EMPLOYEES_LOGIN);
        String password = resultSet.getString(EMPLOYEES_PASSWORD);
        String role = resultSet.getString(EMPLOYEES_ROLE);
        String phones = resultSet.getString(PHONE_NUMBERS_NUMBER);
        List<String> phoneNumbers = extractPhones(phones);
        return UserBuilders.newEmployee()
                .id(id)
                .firstName(firstName)
                .secondName(secondName)
                .patronymic(patronymic)
                .address(address)
                .email(email)
                .login(login)
                .password(password)
                .role(EmployeeRole.valueOf(role))
                .phones(phoneNumbers)
                .build();
    }

    private Address extractAddress(ResultSet resultSet) throws SQLException {
        long addressId = resultSet.getLong(ADDRESSES_ID);
        String country = resultSet.getString(ADDRESSES_COUNTRY);
        int postcode = resultSet.getInt(ADDRESSES_POSTCODE);
        String state = resultSet.getString(ADDRESSES_STATE);
        String region = resultSet.getString(ADDRESSES_REGION);
        String city = resultSet.getString(ADDRESSES_CITY);
        String street = resultSet.getString(ADDRESSES_STREET);
        int houseNumber = resultSet.getInt(ADDRESSES_HOUSE_NUMBER);
        int apartmentNumber = resultSet.getInt(ADDRESSES_APARTMENT_NUMBER);
        return new Address.Builder(city, street, houseNumber)
                .id(addressId)
                .country(country)
                .postcode(postcode)
                .state(state)
                .region(region)
                .apartmentNumber(apartmentNumber)
                .build();
    }

    private List<String> extractPhones(String phoneNumbers) {
        return Arrays.stream(phoneNumbers.split(","))
                .map(String::trim)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private PriceInfo extractPrice(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(PRICES_ID);
        long deviceId = resultSet.getLong(DEVICES_ID);
        String repairLevelString = resultSet.getString(PRICES_REPAIR_LEVEL);
        RepairLevel repairLevel = RepairLevel.valueOf(repairLevelString);
        BigDecimal repairCost = resultSet.getBigDecimal(PRICES_REPAIR_COST);
        return new PriceInfo(id, deviceId, repairLevel, repairCost);
    }
}

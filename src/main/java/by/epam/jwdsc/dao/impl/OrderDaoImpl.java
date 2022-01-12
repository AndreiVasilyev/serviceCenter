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
    private static final String SQL_SELECT_ORDER_BY_ID = "SELECT o.order_id,o.order_number,o.order_status, " +
            "o.creation_date,o.client,o.accepted_employee,o.device, o.company,o.model,o.serial_number," +
            "o.completed_employee,o.completion_date,o.issue_date, o.work_description,o.work_price,o.note,c.user_id," +
            "c.discount,u.first_name,u.second_name, u.patronymic,u.address,u.email,a.country,a.postcode,a.state," +
            "a.region,a.city,a.street, a.house_number, a.apartment_number, d.device_name, co.name, " +
            "co.is_service_contract, GROUP_CONCAT(p.phone_number) AS phone_number FROM orders AS o " +
            "JOIN clients AS c ON(o.client=c.user_id) JOIN users AS u ON(o.client=u.user_id) " +
            "JOIN addresses AS a ON(a.address_id = u.address) JOIN devices AS d ON(d.device_id=o.device) " +
            "JOIN companies AS co ON(co.company_id=o.company) JOIN phone_numbers AS p ON(o.client=p.user_id) " +
            "WHERE o.order_id=? GROUP BY o.order_id";
    private static final String SQL_SELECT_EMPLOYEE_BY_ID = "SELECT e.user_id, e.login, e.password, e.role, " +
            "u.first_name, u.second_name, u.patronymic, u.email, a.address_id, a.country, a.postcode, a.state, " +
            "a.region, a.city, a.street, a.house_number, a.apartment_number, GROUP_CONCAT(p.phone_number) " +
            "AS phone_number FROM employees AS e JOIN users AS u USING (user_id) JOIN addresses AS a " +
            "ON (u.address=a.address_id) JOIN phone_numbers AS p ON(u.user_id = p.user_id) WHERE u.user_id=? " +
            "GROUP BY u.user_id";
    private static final String SQL_SELECT_PRICE_BY_ID = "SELECT p.id, p.device_id, p.repair_level, p.repair_cost " +
            "FROM prices AS p WHERE p.id=? ";
    public static final String SQL_SELECT_PARTS_BY_ORDER_ID = "SELECT s.id, s.part_number, s.name, s.description, s.cost " +
            "FROM spare_parts AS s JOIN orders_spare_parts AS os ON (s.id=os.spare_part_id) WHERE os.order_id=?";
    private static final String SQL_DELETE_ORDER_BY_ID = "DELETE o, os FROM orders AS o JOIN orders_spare_parts AS os " +
            "USING(order_id) WHERE o.order_id=?";
    private static final String SQL_CREATE_ORDER = "INSERT INTO orders(order_number, creation_date, client, " +
            "accepted_employee, device, company, model, serial_number, order_status, note)  VALUES(?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders AS o SET o.order_number=?, o.creation_date=?, " +
            "o.client=?, o.accepted_employee=?, o.device=?, o.company=?, o.model=?, o.serial_number=?, " +
            "o.order_status=?, o.note=?, o.completed_employee=?, o.completion_date=?, o.issue_date=?, " +
            "o.work_description=?, o.work_price=? WHERE o.order_id=?";
    private static final String SQL_ADD_SPARE_PART = "INSERT INTO orders_spare_parts(order_id, spare_part_id) VALUES(?,?)";
    private static final String SQL_DELETE_SPARE_PART = "DELETE FROM orders_spare_parts WHERE order_id=? AND spare_part_id=?";

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orders = new ArrayList<>();
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (Statement statement = connection.createStatement();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_ID);
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ORDERS)) {
            while (resultSet.next()) {
                String orderStatus = resultSet.getString(ORDERS_STATUS);
                long acceptedEmployeeId = resultSet.getLong(ORDERS_ACCEPTED_EMPLOYEE);
                preparedStatement.setLong(1, acceptedEmployeeId);
                try (ResultSet acceptedEmployeeResultSet = preparedStatement.executeQuery()) {
                    Employee acceptedEmployee = extractEmployee(acceptedEmployeeResultSet);
                    Employee completedEmployee = null;
                    PriceInfo priceInfo = null;
                    List<SparePart> spareParts = null;
                    if (OrderStatus.CLOSED.name().equals(orderStatus) || OrderStatus.ISSUED.name().equals(orderStatus)) {
                        try (PreparedStatement preparedStatementPrice = connection.prepareStatement(SQL_SELECT_PRICE_BY_ID);
                             PreparedStatement preparedStatementParts = connection.prepareStatement(SQL_SELECT_PARTS_BY_ORDER_ID)) {
                            long completedEmployeeId = resultSet.getLong(ORDERS_COMPLETED_EMPLOYEE);
                            preparedStatement.setLong(1, completedEmployeeId);
                            try (ResultSet completedEmployeeResultSet = preparedStatement.executeQuery()) {
                                completedEmployee = extractEmployee(completedEmployeeResultSet);
                                long workPriceId = resultSet.getLong(ORDERS_WORK_PRICE);
                                preparedStatementPrice.setLong(1, workPriceId);
                                preparedStatementParts.setLong(1, resultSet.getLong(ORDERS_ID));
                                try (ResultSet priceResultSet = preparedStatementPrice.executeQuery();
                                     ResultSet partsResultSet = preparedStatementParts.executeQuery()) {
                                    priceInfo = extractPrice(priceResultSet);
                                    spareParts = extractSpareParts(partsResultSet);
                                }
                            }
                            Order order = extractOrder(resultSet, acceptedEmployee)
                                    .completedEmployee(completedEmployee)
                                    .workPrice(priceInfo)
                                    .spareParts(spareParts)
                                    .build();
                            orders.add(order);
                        }
                    }
                }
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
        Optional<Order> order = Optional.empty();
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement preparedStatementOrder = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID);
             PreparedStatement preparedStatementEmployee = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_ID)) {
            preparedStatementOrder.setLong(1, id);
            try (ResultSet resultSet = preparedStatementOrder.executeQuery()) {
                if (resultSet.next()) {
                    String orderStatus = resultSet.getString(ORDERS_STATUS);
                    long acceptedEmployeeId = resultSet.getLong(ORDERS_ACCEPTED_EMPLOYEE);
                    preparedStatementEmployee.setLong(1, acceptedEmployeeId);
                    try (ResultSet acceptedEmployeeResultSet = preparedStatementEmployee.executeQuery()) {
                        Employee acceptedEmployee = extractEmployee(acceptedEmployeeResultSet);
                        Employee completedEmployee = null;
                        PriceInfo priceInfo = null;
                        List<SparePart> spareParts = null;
                        if (OrderStatus.CLOSED.name().equals(orderStatus) || OrderStatus.ISSUED.name().equals(orderStatus)) {
                            try (PreparedStatement preparedStatementPrice = connection.prepareStatement(SQL_SELECT_PRICE_BY_ID);
                                 PreparedStatement preparedStatementParts = connection.prepareStatement(SQL_SELECT_PARTS_BY_ORDER_ID)) {
                                long completedEmployeeId = resultSet.getLong(ORDERS_COMPLETED_EMPLOYEE);
                                preparedStatementEmployee.setLong(1, completedEmployeeId);
                                try (ResultSet completedEmployeeResultSet = preparedStatementEmployee.executeQuery()) {
                                    completedEmployee = extractEmployee(completedEmployeeResultSet);
                                    long workPriceId = resultSet.getLong(ORDERS_WORK_PRICE);
                                    preparedStatementPrice.setLong(1, workPriceId);
                                    preparedStatementParts.setLong(1, resultSet.getLong(ORDERS_ID));
                                    try (ResultSet priceResultSet = preparedStatementPrice.executeQuery();
                                         ResultSet partsResultSet = preparedStatementParts.executeQuery()) {
                                        priceInfo = extractPrice(priceResultSet);
                                        spareParts = extractSpareParts(partsResultSet);
                                    }
                                }
                            }
                            order = Optional.of(extractOrder(resultSet, acceptedEmployee)
                                    .completedEmployee(completedEmployee)
                                    .workPrice(priceInfo)
                                    .spareParts(spareParts)
                                    .build());
                        }
                    }
                }
            }
        } catch (SQLException e) {
            log.error("Error executing query findById from Orders", e);
            throw new DaoException("Error executing query findById from Orders", e);
        } finally {
            close(connection);
        }
        return order;

    }

    @Override
    public boolean delete(Order order) throws DaoException {
        return deleteById(order.getId());
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result = false;
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER_BY_ID)) {
            preparedStatement.setLong(1, id);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Error executing query deleteById from Orders", e);
            throw new DaoException("Error executing query deleteById from Orders", e);
        } finally {
            close(connection);
        }
        return result;
    }

    @Override
    public boolean create(Order order) throws DaoException {
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ORDER)) {
            collectCreateOrderQuery(preparedStatement, order);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing query create new Order", e);
            throw new DaoException("Error executing query create new Order", e);
        } finally {
            close(connection);
        }
        return true;
    }

    @Override
    public Order update(Order order) throws DaoException {
        Order oldOrder = findById(order.getId()).get();
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER)) {
            collectCreateOrderQuery(preparedStatement, order);
            collectUpdateOrderQuery(preparedStatement, order);
            preparedStatement.executeUpdate();
            if (!oldOrder.getSpareParts().isEmpty()) {
                try (PreparedStatement statementDeletePartFromOrder = connection.prepareStatement(SQL_DELETE_SPARE_PART)) {
                    for (SparePart sparePart : oldOrder.getSpareParts()) {
                        statementDeletePartFromOrder.setLong(1, oldOrder.getId());
                        statementDeletePartFromOrder.setLong(2, sparePart.getId());
                        statementDeletePartFromOrder.executeUpdate();
                    }
                }
            }
            if (order.getSpareParts() != null && !order.getSpareParts().isEmpty()) {
                try (PreparedStatement statementAddPartToOrder = connection.prepareStatement(SQL_ADD_SPARE_PART)) {
                    for (SparePart sparePart : order.getSpareParts()) {
                        statementAddPartToOrder.setLong(1, order.getId());
                        statementAddPartToOrder.setLong(2, sparePart.getId());
                        statementAddPartToOrder.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            log.error("Error executing query update Order", e);
            throw new DaoException("Error executing query update Order", e);
        } finally {
            close(connection);
        }
        return oldOrder;
    }

    private Order.Builder extractOrder(ResultSet resultSet, Employee acceptedEmployee) throws SQLException {
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
                .workDescription(workDescription)
                .issueDate(issueDate);
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
        String role = resultSet.getString(USERS_ROLE);
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
                .userRole(UserRole.valueOf(role))
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

    private List<SparePart> extractSpareParts(ResultSet resultSet) throws SQLException {
        List<SparePart> spareParts = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong(SPARE_PARTS_ID);
            String partNumber = resultSet.getString(SPARE_PARTS_PART_NUMBER);
            String name = resultSet.getString(SPARE_PARTS_NAME);
            String description = resultSet.getString(SPARE_PARTS_DESCRIPTION);
            BigDecimal cost = resultSet.getBigDecimal(SPARE_PARTS_COST);
            SparePart sparePart = new SparePart.Builder(name, cost)
                    .id(id)
                    .partNumber(partNumber)
                    .description(description)
                    .build();
            spareParts.add(sparePart);
        }
        return spareParts;
    }

    private void collectCreateOrderQuery(PreparedStatement statement, Order order) throws SQLException {
        statement.setString(1, order.getOrderNumber());
        statement.setDate(2, Date.valueOf(order.getCreationDate().toLocalDate()));
        statement.setLong(3, order.getClient().getId());
        statement.setLong(4, order.getAcceptedEmployee().getId());
        statement.setLong(5, order.getDevice().getId());
        if (order.getCompany() != null) {
            statement.setLong(6, order.getCompany().getId());
        } else {
            statement.setNull(6, Types.BIGINT);
        }
        if (order.getModel() != null && !order.getModel().isBlank()) {
            statement.setString(7, order.getModel());
        } else {
            statement.setNull(7, Types.VARCHAR);
        }
        if (order.getSerialNumber() != null && !order.getSerialNumber().isBlank()) {
            statement.setString(8, order.getSerialNumber());
        } else {
            statement.setNull(8, Types.VARCHAR);
        }
        statement.setString(9, order.getOrderStatus().name());
        if (order.getNote() != null && !order.getNote().isBlank()) {
            statement.setString(10, order.getNote());
        } else {
            statement.setNull(10, Types.VARCHAR);
        }
    }

    private void collectUpdateOrderQuery(PreparedStatement statement, Order order) throws SQLException {
        boolean isOrderClosed = OrderStatus.CLOSED.name().equals(order.getOrderStatus());
        boolean isOrderIssued = OrderStatus.ISSUED.name().equals(order.getOrderStatus());
        if (isOrderClosed || isOrderIssued) {
            statement.setLong(11, order.getCompletedEmployee().getId());
            statement.setDate(12, Date.valueOf(order.getComletionDate().toLocalDate()));
            if (isOrderIssued) {
                statement.setDate(13, Date.valueOf(order.getIssueDate().toLocalDate()));
            } else {
                statement.setNull(13, Types.DATE);
            }
            statement.setString(14, order.getWorkDescription());
            statement.setLong(15, order.getWorkPrice().getId());
        } else {
            statement.setNull(11, Types.BIGINT);
            statement.setNull(12, Types.DATE);
            statement.setNull(13, Types.DATE);
            statement.setNull(14, Types.VARCHAR);
            statement.setNull(15, Types.BIGINT);
        }
        statement.setLong(16, order.getId());
    }
}

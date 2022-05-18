package by.epam.jwdsc.dao.impl;

import by.epam.jwdsc.dao.OrderDao;
import by.epam.jwdsc.dao.QueryParametersMapper;
import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;
import org.apache.logging.log4j.util.Strings;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

import static by.epam.jwdsc.dao.ColumnName.*;
import static by.epam.jwdsc.dao.TableAliasName.*;

public class OrderDaoImpl implements OrderDao {

    private static final String SQL_SELECT_ORDERS_TEMPLATE = "SELECT o.order_id,o.order_number,o.order_status, " +
            "o.creation_date,o.client,o.accepted_employee,o.device, o.company,o.model,o.serial_number," +
            "o.completed_employee,o.completion_date,o.issue_date, o.work_description,o.work_price,o.note,c.user_id," +
            "c.discount,u.first_name,u.second_name, u.patronymic,u.address,u.email,a.address_id,a.country,a.postcode," +
            "a.state,a.region,a.city,a.street,a.house_number,a.apartment_number,d.device_name,co.company_id,co.name, " +
            "co.is_service_contract, GROUP_CONCAT(p.phone_number) AS phone_number, " +
            "(SELECT concat(ae.first_name,ae.second_name) FROM users AS ae WHERE o.accepted_employee=ae.user_id) AS accepted_names, " +
            "(SELECT concat(ce.first_name,ce.second_name) FROM users AS ce WHERE o.completed_employee=ce.user_id) AS completed_names, " +
            "(SELECT pl.repair_level FROM prices AS pl WHERE o.work_price=pl.id) AS repair_level, " +
            "(SELECT pc.repair_cost FROM prices AS pc WHERE o.work_price=pc.id) AS repair_cost " +
            "FROM orders AS o JOIN clients AS c ON(o.client=c.user_id) JOIN users AS u ON(o.client=u.user_id) " +
            "JOIN addresses AS a ON(a.address_id = u.address) JOIN devices AS d ON(d.device_id=o.device) " +
            "LEFT JOIN companies AS co ON(co.company_id=o.company) LEFT JOIN phone_numbers AS p ON(o.client=p.user_id) " +
            "%s GROUP BY o.order_id %s %s";
    private static final String SQL_COUNT_ORDERS_TEMPLATE = "SELECT COUNT(*) AS count FROM (SELECT COUNT(o.order_id) " +
            "FROM orders AS o JOIN clients AS c ON(o.client=c.user_id) JOIN users AS u ON(o.client=u.user_id) " +
            "JOIN addresses AS a ON(a.address_id = u.address) JOIN devices AS d ON(d.device_id=o.device) " +
            "JOIN companies AS co ON(co.company_id=o.company) LEFT JOIN phone_numbers AS p ON(o.client=p.user_id) " +
            "%s GROUP BY o.order_id) records";
    private static final String SQL_SELECT_EMPLOYEE_BY_ID = "SELECT e.user_id, e.login, e.password, u.user_role, " +
            "u.first_name, u.second_name, u.patronymic, u.email, a.address_id, a.country, a.postcode, a.state, " +
            "a.region, a.city, a.street, a.house_number, a.apartment_number, GROUP_CONCAT(p.phone_number) " +
            "AS phone_number FROM employees AS e JOIN users AS u USING (user_id) JOIN addresses AS a ON (u.address=a.address_id) " +
            "LEFT JOIN phone_numbers AS p ON (u.user_id=p.user_id) WHERE e.user_id=? GROUP BY e.user_id";
    private static final String SQL_SELECT_PRICE_BY_ID = "SELECT pr.id, pr.device_id, pr.repair_level, pr.repair_cost " +
            "FROM prices AS pr WHERE pr.id=? ";
    public static final String SQL_SELECT_PARTS_BY_ORDER_ID = "SELECT s.id, s.part_number, s.name, s.description, s.cost " +
            "FROM spare_parts AS s JOIN orders_spare_parts AS os ON (s.id=os.spare_part_id) WHERE os.order_id=?";
    private static final String SQL_DELETE_ORDER_BY_ID = "DELETE o, os FROM orders AS o LEFT JOIN orders_spare_parts AS os " +
            "USING(order_id) WHERE o.order_id=?";
    private static final String SQL_CREATE_ORDER = "INSERT INTO orders(order_number, creation_date, client, " +
            "accepted_employee, device, company, model, serial_number, order_status, note)  VALUES(?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders AS o SET o.order_number=?, o.creation_date=?, " +
            "o.client=?, o.accepted_employee=?, o.device=?, o.company=?, o.model=?, o.serial_number=?, " +
            "o.order_status=?, o.note=?, o.completed_employee=?, o.completion_date=?, o.issue_date=?, " +
            "o.work_description=?, o.work_price=? WHERE o.order_id=?";
    private static final String SQL_ADD_SPARE_PART = "INSERT INTO orders_spare_parts(order_id, spare_part_id) VALUES(?,?)";
    private static final String SQL_DELETE_SPARE_PART = "DELETE FROM orders_spare_parts WHERE order_id=? AND spare_part_id=?";
    private static final String SQL_SELECT_LAST_ORDER_BY_TYPE = "SELECT MAX(SUBSTR(order_number,2)) FROM orders WHERE order_number LIKE '%s'";
    private static final String ANY_SYMBOLS_WILDCARD = "%";

    @Override
    public List<Order> findAll() throws DaoException {
        String selectQuery = String.format(SQL_SELECT_ORDERS_TEMPLATE, Strings.EMPTY, Strings.EMPTY, Strings.EMPTY);
        return findOrders(selectQuery, Collections.emptyList());
    }

    @Override
    public Optional<Order> findById(long id) throws DaoException {
        QueryParametersMapper queryParametersMapper = QueryParametersMapper.getInstance();
        LinkedHashMap<String, Object> parameters = queryParametersMapper.mapParameter(SC_ORDERS, ORDERS_ID, id);
        String whereBlock = prepareWhereBlock(parameters.keySet());
        String selectQuery = String.format(SQL_SELECT_ORDERS_TEMPLATE, whereBlock, Strings.EMPTY, Strings.EMPTY);
        List<Order> orders = findOrders(selectQuery, parameters.values());
        return !orders.isEmpty() ? Optional.of(orders.get(0)) : Optional.empty();
    }

    @Override
    public long countOrdersByParams(LinkedHashMap<String, Object> parameters) throws DaoException {
        String whereBlock = prepareWhereBlock(parameters.keySet());
        String countQuery = String.format(SQL_COUNT_ORDERS_TEMPLATE, whereBlock);
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(countQuery)) {
            prepareStatement(preparedStatement, parameters.values());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            log.error("Error executing query count all orders by params", e);
            throw new DaoException("Error executing query count all orders by params", e);
        }
    }

    @Override
    public List<Order> findByParams(LinkedHashMap<String, Object> parameters) throws DaoException {
        String whereBlock = prepareWhereBlock(parameters.keySet());
        String selectQuery = String.format(SQL_SELECT_ORDERS_TEMPLATE, whereBlock, Strings.EMPTY, Strings.EMPTY);
        return findOrders(selectQuery, parameters.values());
    }

    @Override
    public List<Order> findByParamsWithSortAndPage(LinkedHashMap<String, Object> parameters, String sort, int pageNumber) throws DaoException {
        String whereBlock = prepareWhereBlock(parameters.keySet());
        String sortBlock = prepareSortBlock(sort);
        String pageBlock = preparePageBlock(pageNumber);
        String selectQuery = String.format(SQL_SELECT_ORDERS_TEMPLATE, whereBlock, sortBlock, pageBlock);
        return findOrders(selectQuery, parameters.values());
    }

    @Override
    public String findLastOrderNumber(String orderType) throws DaoException {
        if ("P".equals(orderType) || "S".equals(orderType)) {
            String sqlParameter = Strings.concat(orderType, ANY_SYMBOLS_WILDCARD);
            String sqlQuery = String.format(SQL_SELECT_LAST_ORDER_BY_TYPE, sqlParameter);
            try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sqlQuery)) {
                resultSet.next();
                return resultSet.getString(1);
            } catch (SQLException e) {
                log.error("Error executing query find last orderNumber", e);
                throw new DaoException("Error executing query find last orderNumber", e);
            }
        }
        log.error("Error executing query find last orderNumber because illegal argument order type: {}", orderType);
        throw new DaoException("Error executing query find last orderNumber because illegal argument order type");
    }

    @Override
    public boolean delete(Order order) throws DaoException {
        return deleteById(order.getId());
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result = false;
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER_BY_ID)) {
            preparedStatement.setLong(1, id);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Error executing query deleteById from Orders", e);
            throw new DaoException("Error executing query deleteById from Orders", e);
        }
        return result;
    }

    @Override
    public boolean create(Order order) throws DaoException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ORDER)) {
            collectCreateOrderQuery(preparedStatement, order);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing query create new Order", e);
            throw new DaoException("Error executing query create new Order", e);
        }
        return true;
    }

    @Override
    public Optional<Order> update(Order order) throws DaoException {
        Optional<Order> oldOrderFound = findById(order.getId());
        if (oldOrderFound.isPresent()) {
            Order oldOrder = oldOrderFound.get();
            Connection connection = DbConnectionPool.INSTANCE.getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER)) {
                connection.setAutoCommit(false);
                collectCreateOrderQuery(preparedStatement, order);
                collectUpdateOrderQuery(preparedStatement, order);
                preparedStatement.executeUpdate();
                if (oldOrder.getSpareParts() != null && !oldOrder.getSpareParts().isEmpty()) {
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
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    log.error("Transaction to update order not executed. Rollback changes not executed", ex);
                }
                log.error("Error executing query update Order", e);
                throw new DaoException("Error executing query update Order", e);
            } finally {
                close(connection);
            }
        }
        return oldOrderFound;
    }

    private List<Order> findOrders(String selectQuery, Collection<Object> parameters) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement orderStatement = connection.prepareStatement(selectQuery)) {
            prepareStatement(orderStatement, parameters);
            try (ResultSet orderResultSet = orderStatement.executeQuery()) {
                while (orderResultSet.next()) {
                    try (PreparedStatement employeePreparedStatement = connection.prepareStatement(SQL_SELECT_EMPLOYEE_BY_ID)) {
                        OrderStatus orderStatus = OrderStatus.valueOf(orderResultSet.getString(ORDERS_STATUS));
                        long acceptedEmployeeId = orderResultSet.getLong(ORDERS_ACCEPTED_EMPLOYEE);
                        employeePreparedStatement.setLong(1, acceptedEmployeeId);
                        try (ResultSet acceptedEmployeeResultSet = employeePreparedStatement.executeQuery()) {
                            Employee acceptedEmployee = acceptedEmployeeResultSet.next() ? extractEmployee(acceptedEmployeeResultSet) : null;
                            Employee completedEmployee = null;
                            PriceInfo priceInfo = null;
                            List<SparePart> spareParts = null;
                            if (orderStatus != OrderStatus.ACCEPTED) {
                                try (PreparedStatement pricePreparedStatement = connection.prepareStatement(SQL_SELECT_PRICE_BY_ID);
                                     PreparedStatement partsPreparedStatement = connection.prepareStatement(SQL_SELECT_PARTS_BY_ORDER_ID)) {
                                    long completedEmployeeId = orderResultSet.getLong(ORDERS_COMPLETED_EMPLOYEE);
                                    employeePreparedStatement.setLong(1, completedEmployeeId);
                                    try (ResultSet completedEmployeeResultSet = employeePreparedStatement.executeQuery()) {
                                        completedEmployee = completedEmployeeResultSet.next() ? extractEmployee(completedEmployeeResultSet) : null;
                                        long workPriceId = orderResultSet.getLong(ORDERS_WORK_PRICE);
                                        pricePreparedStatement.setLong(1, workPriceId);
                                        partsPreparedStatement.setLong(1, orderResultSet.getLong(ORDERS_ID));
                                        try (ResultSet priceResultSet = pricePreparedStatement.executeQuery();
                                             ResultSet partsResultSet = partsPreparedStatement.executeQuery()) {
                                            priceInfo = priceResultSet.next() ? extractPrice(priceResultSet) : null;
                                            spareParts = extractSpareParts(partsResultSet);
                                        }
                                    }
                                }
                            }
                            Order order = extractOrder(orderResultSet, acceptedEmployee)
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
            log.error("Error executing query find orders", e);
            throw new DaoException("Error executing query find orders", e);
        }
        return orders;
    }

    private Order.Builder extractOrder(ResultSet resultSet, Employee acceptedEmployee) throws SQLException {
        long id = resultSet.getLong(ORDERS_ID);
        String orderNumber = resultSet.getString(ORDERS_ORDER_NUMBER);
        String orderStatus = resultSet.getString(ORDERS_STATUS);
        LocalDateTime creationDate = resultSet.getTimestamp(ORDERS_CREATION_DATE).toLocalDateTime();
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
        LocalDateTime completedDate = isOrderClosed || isOrderIssued ? resultSet.getTimestamp(ORDERS_COMPLETION_DATE).toLocalDateTime() : null;
        String workDescription = isOrderClosed || isOrderIssued ? resultSet.getString(ORDERS_WORK_DESCRIPTION) : null;
        LocalDateTime issueDate = isOrderIssued ? resultSet.getTimestamp(ORDERS_ISSUE_DATE).toLocalDateTime() : null;
        return new Order.Builder(id, orderNumber, creationDate, client, acceptedEmployee, device)
                .orderStatus(OrderStatus.valueOf(orderStatus))
                .company(company)
                .model(model)
                .serialNumber(serialNumber)
                .note(note)
                .completionDate(completedDate)
                .workDescription(workDescription)
                .issueDate(issueDate);
    }

    private void collectCreateOrderQuery(PreparedStatement statement, Order order) throws SQLException {
        statement.setString(1, order.getOrderNumber());
        statement.setTimestamp(2, Timestamp.valueOf(order.getCreationDate()));
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
        if (order.getCompletedEmployee() != null) {
            statement.setLong(11, order.getCompletedEmployee().getId());
        } else {
            statement.setNull(11, Types.BIGINT);
        }
        if (order.getCompletionDate() != null) {
            statement.setTimestamp(12, Timestamp.valueOf(order.getCompletionDate()));
        } else {
            statement.setNull(12, Types.DATE);
        }
        if (order.getIssueDate() != null) {
            statement.setTimestamp(13, Timestamp.valueOf(order.getIssueDate()));
        } else {
            statement.setNull(13, Types.DATE);
        }
        if (order.getWorkDescription() != null && !order.getWorkDescription().isBlank()) {
            statement.setString(14, order.getWorkDescription());
        } else {
            statement.setNull(14, Types.VARCHAR);
        }
        if (order.getWorkPrice() != null) {
            statement.setLong(15, order.getWorkPrice().getId());
        } else {
            statement.setNull(15, Types.BIGINT);
        }
        statement.setLong(16, order.getId());
    }
}

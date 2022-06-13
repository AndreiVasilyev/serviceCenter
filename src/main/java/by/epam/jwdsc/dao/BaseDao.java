package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static by.epam.jwdsc.dao.ColumnName.*;
import static by.epam.jwdsc.dao.ColumnName.SPARE_PARTS_COST;

/**
 * The interface Base dao.
 *
 * @param <T> the type parameter
 */
public interface BaseDao<T extends CommonEntity> {
    /**
     * The constant log.
     */
    Logger log = LogManager.getLogger();
    /**
     * The constant PHONES_SEPARATOR.
     */
    String PHONES_SEPARATOR = ",";
    /**
     * The constant LOGIC_AND.
     */
    String LOGIC_AND = "AND ";
    /**
     * The constant WHERE_TEMPLATE.
     */
    String WHERE_TEMPLATE = "WHERE ";
    /**
     * The constant PAGE_TEMPLATE.
     */
    String PAGE_TEMPLATE = "LIMIT 10 OFFSET %d";
    /**
     * The constant SORT_TEMPLATE.
     */
    String SORT_TEMPLATE = "ORDER BY %s";
    /**
     * The constant START_PARAMETER_INDEX.
     */
    int START_PARAMETER_INDEX = 1;
    /**
     * The constant PAGE_SIZE.
     */
    int PAGE_SIZE = 10;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<T> findAll() throws DaoException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<T> findById(long id) throws DaoException;

    /**
     * Delete boolean.
     *
     * @param t the t
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean delete(T t) throws DaoException;

    /**
     * Delete by id boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean deleteById(long id) throws DaoException;

    /**
     * Create boolean.
     *
     * @param t the t
     * @return the boolean
     * @throws DaoException the dao exception
     */
    boolean create(T t) throws DaoException;

    /**
     * Update optional.
     *
     * @param t the t
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<T> update(T t) throws DaoException;

    /**
     * Close.
     *
     * @param statement the statement
     */
    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            log.error("Error when closing statement", e);
        }
    }

    /**
     * Close.
     *
     * @param connection the connection
     */
    default void close(Connection connection) {
        if (connection != null) {
            DbConnectionPool.INSTANCE.releaseConnection(connection);
        }
    }

    /**
     * Prepare where block string.
     *
     * @param parameterExpression the parameter expression
     * @return the string
     */
    default String prepareWhereBlock(Set<String> parameterExpression) {
        String whereBlock = Strings.EMPTY;
        if (parameterExpression != null && !parameterExpression.isEmpty()) {
            StringBuilder whereBlockBuilder = new StringBuilder(WHERE_TEMPLATE);
            for (String parameterName : parameterExpression) {
                whereBlockBuilder.append(parameterName);
                whereBlockBuilder.append(LOGIC_AND);
            }
            whereBlock = whereBlockBuilder.toString();
            whereBlock = whereBlock.substring(0, whereBlock.lastIndexOf(LOGIC_AND));
        }
        return whereBlock;
    }

    /**
     * Prepare sort block string.
     *
     * @param sort the sort
     * @return the string
     */
    default String prepareSortBlock(String sort) {
        String sortBlock = Strings.EMPTY;
        if (sort != null && !sort.isBlank()) {
            sortBlock = String.format(SORT_TEMPLATE, sort);
        }
        return sortBlock;
    }

    /**
     * Prepare page block string.
     *
     * @param pageNumber the page number
     * @return the string
     */
    default String preparePageBlock(int pageNumber) {
        String pageBlock = Strings.EMPTY;
        if (pageNumber > 0) {
            pageBlock = String.format(PAGE_TEMPLATE, (pageNumber - 1) * PAGE_SIZE);
        }
        return pageBlock;
    }

    /**
     * Prepare statement.
     *
     * @param preparedStatement the prepared statement
     * @param parameters        the parameters
     * @throws SQLException the sql exception
     */
    default void prepareStatement(PreparedStatement preparedStatement, Collection<Object> parameters) throws SQLException {
        if (parameters != null && !parameters.isEmpty()) {
            int parameterIndex = START_PARAMETER_INDEX;
            for (Object parameterValue : parameters) {
                preparedStatement.setObject(parameterIndex++, parameterValue);
            }
        }
    }

    /**
     * Extract client client.
     *
     * @param resultSet the result set
     * @return the client
     * @throws SQLException the sql exception
     */
    default Client extractClient(ResultSet resultSet) throws SQLException {
        int discount = resultSet.getInt(CLIENTS_DISCOUNT);
        Map<String, Object> userValues = extractUser(resultSet);
        return UserBuilders.newClient()
                .id((Long) userValues.get(USERS_ID))
                .firstName((String) userValues.get(USERS_FIRST_NAME))
                .secondName((String) userValues.get(USERS_SECOND_NAME))
                .patronymic((String) userValues.get(USERS_PATRONYMIC))
                .address((Address) userValues.get(USERS_ADDRESS))
                .email((String) userValues.get(USERS_EMAIL))
                .discount(discount)
                .phones((List<String>) userValues.get(PHONE_NUMBERS_NUMBER))
                .build();
    }

    /**
     * Extract employee employee.
     *
     * @param resultSet the result set
     * @return the employee
     * @throws SQLException the sql exception
     */
    default Employee extractEmployee(ResultSet resultSet) throws SQLException {
        Map<String, Object> userValues = extractUser(resultSet);
        String login = resultSet.getString(EMPLOYEES_LOGIN);
        String password = resultSet.getString(EMPLOYEES_PASSWORD);
        String userRole = (resultSet.getString(USERS_ROLE));
        return UserBuilders.newEmployee()
                .id((Long) userValues.get(USERS_ID))
                .firstName((String) userValues.get(USERS_FIRST_NAME))
                .secondName((String) userValues.get(USERS_SECOND_NAME))
                .patronymic((String) userValues.get(USERS_PATRONYMIC))
                .address((Address) userValues.get(USERS_ADDRESS))
                .email((String) userValues.get(USERS_EMAIL))
                .login(login)
                .password(password)
                .userRole(UserRole.valueOf(userRole))
                .phones((List<String>) userValues.get(PHONE_NUMBERS_NUMBER))
                .build();
    }

    /**
     * Extract user map.
     *
     * @param resultSet the result set
     * @return the map
     * @throws SQLException the sql exception
     */
    default Map<String, Object> extractUser(ResultSet resultSet) throws SQLException {
        Map<String, Object> result = new HashMap<>();
        result.put(USERS_ID, resultSet.getLong(USERS_ID));
        result.put(USERS_FIRST_NAME, resultSet.getString(USERS_FIRST_NAME));
        result.put(USERS_SECOND_NAME, resultSet.getString(USERS_SECOND_NAME));
        result.put(USERS_PATRONYMIC, resultSet.getString(USERS_PATRONYMIC));
        result.put(USERS_EMAIL, resultSet.getString(USERS_EMAIL));
        result.put(USERS_ADDRESS, extractAddress(resultSet));
        String phones = resultSet.getString(PHONE_NUMBERS_NUMBER);
        result.put(PHONE_NUMBERS_NUMBER, extractPhones(phones));
        return result;
    }

    /**
     * Extract address address.
     *
     * @param resultSet the result set
     * @return the address
     * @throws SQLException the sql exception
     */
    default Address extractAddress(ResultSet resultSet) throws SQLException {
        long addressId = resultSet.getLong(ADDRESSES_ID);
        if (addressId != 0) {
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
        } else {
            return null;
        }
    }

    /**
     * Extract phones list.
     *
     * @param phoneNumbers the phone numbers
     * @return the list
     */
    default List<String> extractPhones(String phoneNumbers) {
        if (phoneNumbers != null && !phoneNumbers.isBlank()) {
            return Arrays.stream(phoneNumbers.split(PHONES_SEPARATOR))
                    .map(String::trim)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return new ArrayList<>();
    }

    /**
     * Extract price price info.
     *
     * @param resultSet the result set
     * @return the price info
     * @throws SQLException the sql exception
     */
    default PriceInfo extractPrice(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(PRICES_ID);
        long deviceId = resultSet.getLong(DEVICES_ID);
        String repairLevelString = resultSet.getString(PRICES_REPAIR_LEVEL);
        RepairLevel repairLevel = RepairLevel.valueOf(repairLevelString);
        BigDecimal repairCost = resultSet.getBigDecimal(PRICES_REPAIR_COST);
        return new PriceInfo(id, deviceId, repairLevel, repairCost);
    }

    /**
     * Extract spare parts list.
     *
     * @param resultSet the result set
     * @return the list
     * @throws SQLException the sql exception
     */
    default List<SparePart> extractSpareParts(ResultSet resultSet) throws SQLException {
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
}

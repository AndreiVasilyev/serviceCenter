package by.epam.jwdsc.dao.impl;

import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.entity.Client;
import by.epam.jwdsc.dao.ClientDao;
import by.epam.jwdsc.entity.UserBuilders;
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

public class ClientDaoImpl implements ClientDao {

    private static final String SQL_SELECT_ALL_CLIENTS = "SELECT c.user_id, c.discount, u.first_name, u.second_name, " +
            "u.patronymic, u.email, a.address_id, a.country, a.postcode, a.state, a.region, a.city, a.street, " +
            "a.house_number, a.apartment_number, GROUP_CONCAT(p.phone_number) AS phone_number FROM clients AS c " +
            "JOIN users AS u USING (user_id) JOIN addresses AS a ON (u.address=a.address_id) JOIN phone_numbers AS p " +
            "ON(u.user_id = p.user_id) GROUP BY u.user_id";
    private static final String SQL_SELECT_CLIENT_BY_ID = "SELECT c.user_id, c.discount, u.first_name, u.second_name, " +
            "u.patronymic, u.email, a.address_id, a.country, a.postcode, a.state, a.region, a.city, a.street, " +
            "a.house_number, a.apartment_number, GROUP_CONCAT(p.phone_number) AS phone_number FROM clients AS c " +
            "JOIN users AS u USING (user_id) JOIN addresses AS a ON (u.address=a.address_id) JOIN phone_numbers AS p " +
            "ON(u.user_id = p.user_id) WHERE u.user_id=? GROUP BY u.user_id";
    private static final String SQL_DELETE_CLIENT_BY_ID = "DELETE c, u, a, p FROM clients AS c JOIN users AS u " +
            "USING (user_id) JOIN addresses AS a ON (u.address=a.address_id) JOIN phone_numbers AS p " +
            "ON(u.user_id = p.user_id) WHERE u.user_id=?";
    private static final String SQL_CREATE_ADDRESS = "INSERT INTO addresses(country, postcode, state, region, city," +
            "street, house_number, apartment_number) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_CREATE_USER = "INSERT INTO users(first_name, second_name, patronymic, address, " +
            "email) VALUES(?,?,?,?,?)";
    private static final String SQL_CREATE_CLIENT = "INSERT INTO clients(user_id, discount) VALUES(?,?)";
    private static final String SQL_CREATE_PHONE_NUMBER = "INSERT INTO phone_numbers(user_id, phone_number) VALUES(?,?)";
    private static final String SQL_UPDATE_CLIENT = "UPDATE clients AS c JOIN users AS u USING (user_id) " +
            "JOIN addresses AS a ON (u.address=a.address_id) SET c.discount=?, u.first_name=?, u.second_name=?, " +
            "u.patronymic=?, u.email=?, a.country=?, a.postcode=?, a.state=?, a.region=?, a.city=?, a.street=?, " +
            "a.house_number=?, a.apartment_number=? WHERE u.user_id=?";
    private static final String SQL_DELETE_PHONE_NUMBER = "DELETE FROM phone_numbers WHERE user_id=?";

    @Override
    public List<Client> findAll() throws DaoException {
        List<Client> clients = new ArrayList<>();
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CLIENTS)) {
            while (resultSet.next()) {
                Client client = extractClient(resultSet);
                clients.add(client);
            }
        } catch (SQLException e) {
            log.error("Error executing query findAll from Clients", e);
            throw new DaoException("Error executing query findAll from Clients", e);
        } finally {
            close(connection);
        }
        return clients;
    }

    @Override
    public Optional<Client> findById(long id) throws DaoException {
        Optional<Client> client;
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_CLIENT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                client = Optional.ofNullable(extractClient(resultSet));

            } else {
                client = Optional.empty();
            }
        } catch (SQLException e) {
            log.error("Error executing query findById from Clients", e);
            throw new DaoException("Error executing query findById from Clients", e);
        } finally {
            close(connection);
        }
        return client;
    }

    @Override
    public boolean delete(Client client) throws DaoException {
        return deleteById(client.getId());
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result = false;
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CLIENT_BY_ID)) {
            statement.setLong(1, id);
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Error executing query deleteById from Clients", e);
            throw new DaoException("Error executing query deleteById from Clients", e);
        } finally {
            close(connection);
        }
        return result;
    }

    @Override
    public boolean create(Client client) throws DaoException {
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statementNewAddress = connection.prepareStatement(SQL_CREATE_ADDRESS, RETURN_GENERATED_KEYS);
             PreparedStatement statementNewUser = connection.prepareStatement(SQL_CREATE_USER, RETURN_GENERATED_KEYS);
             PreparedStatement statementNewClient = connection.prepareStatement(SQL_CREATE_CLIENT);
             PreparedStatement statementNewPhones = connection.prepareStatement(SQL_CREATE_PHONE_NUMBER)
        ) {
            connection.setAutoCommit(false);
            collectCreateAddressQuery(statementNewAddress, client);
            statementNewAddress.executeUpdate();
            ResultSet generatedAddressKey = statementNewAddress.getGeneratedKeys();
            client.getAddress().setId(generatedAddressKey.getLong(1));
            collectCreateUserQuery(statementNewUser, client);
            statementNewUser.executeUpdate();
            ResultSet generatedUserKey = statementNewUser.getGeneratedKeys();
            client.setId(generatedUserKey.getLong(1));
            collectCreateClientQuery(statementNewClient, client);
            statementNewClient.executeUpdate();
            if (client.getPhones() != null && !client.getPhones().isEmpty()) {
                for (String phoneNumber : client.getPhones()) {
                    statementNewPhones.setLong(1, client.getId());
                    statementNewClient.setString(2, phoneNumber);
                    statementNewPhones.executeUpdate();
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                log.error("Transaction to create new Client not executed. Rollback changes not executed", ex);
                throw new DaoException("Transaction to create new Client not executed. Rollback changes not executed", ex);
            }
            log.error("Transaction to create new Client not executed. All changes canceled", e);
            throw new DaoException("Transaction to create new Client not executed. All changes canceled", e);
        } finally {
            close(connection);
        }
        return true;
    }

    @Override
    public Client update(Client client) throws DaoException {
        Client oldClient = findById(client.getId()).get();
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CLIENT);
             PreparedStatement statementAddPhone = connection.prepareStatement(SQL_CREATE_PHONE_NUMBER);
             PreparedStatement statementDeletePhone = connection.prepareStatement(SQL_DELETE_PHONE_NUMBER)
        ) {
            collectUpdateClientQuery(statement, client);
            statement.executeUpdate();
            if (client.getPhones() != null && !client.getPhones().isEmpty()) {
                for (String phoneNumber : client.getPhones()) {
                    if (!oldClient.getPhones().contains(phoneNumber)) {
                        statementAddPhone.setLong(1, client.getId());
                        statementAddPhone.setString(2, phoneNumber);
                        statementAddPhone.executeUpdate();
                    }
                }
                if (!oldClient.getPhones().isEmpty()) {
                    for (String phoneNumber : oldClient.getPhones()) {
                        if (!client.getPhones().contains(phoneNumber)) {
                            statementDeletePhone.setLong(1, client.getId());
                            statementDeletePhone.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            log.error("Error executing query update Client", e);
            throw new DaoException("Error executing query update Client", e);
        } finally {
            close(connection);
        }
        return oldClient;
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
                .map(phoneNumber -> phoneNumber.trim())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void collectCreateAddressQuery(PreparedStatement statement, Client client) throws SQLException {
        Address address = client.getAddress();
        if (address.getCountry() != null && !address.getCountry().isBlank()) {
            statement.setString(1, address.getCountry());
        } else {
            statement.setNull(1, Types.VARCHAR);
        }
        if (address.getPostcode() != 0) {
            statement.setInt(2, address.getPostcode());
        } else {
            statement.setNull(2, Types.INTEGER);
        }
        if (address.getState() != null && !address.getState().isBlank()) {
            statement.setString(3, address.getState());
        } else {
            statement.setNull(3, Types.VARCHAR);
        }
        if (address.getRegion() != null && !address.getRegion().isBlank()) {
            statement.setString(4, address.getRegion());
        } else {
            statement.setNull(4, Types.VARCHAR);
        }
        statement.setString(5, address.getCity());
        statement.setString(6, address.getStreet());
        statement.setInt(7, address.getHouseNumber());
        if (address.getApartmentNumber() != 0) {
            statement.setInt(8, address.getApartmentNumber());
        } else {
            statement.setNull(8, Types.INTEGER);
        }
    }

    private void collectCreateUserQuery(PreparedStatement statement, Client client) throws SQLException {
        statement.setString(1, client.getFirstName());
        statement.setString(2, client.getSecondName());
        if (client.getPatronymic() != null && !client.getPatronymic().isBlank()) {
            statement.setString(3, client.getPatronymic());
        } else {
            statement.setNull(3, Types.VARCHAR);
        }
        if (client.getAddress() != null) {
            statement.setLong(4, client.getAddress().getId());
        } else {
            statement.setNull(4, Types.BIGINT);
        }
        if (client.getEmail() != null && !client.getEmail().isBlank()) {
            statement.setString(5, client.getEmail());
        } else {
            statement.setNull(5, Types.VARCHAR);
        }
    }

    private void collectCreateClientQuery(PreparedStatement statement, Client client) throws SQLException {
        statement.setLong(1, client.getId());
        if (client.getDiscount() != 0) {
            statement.setInt(2, client.getDiscount());
        } else {
            statement.setNull(2, Types.INTEGER);
        }
    }

    private void collectUpdateClientQuery(PreparedStatement statement, Client client) throws SQLException {
        statement.setInt(1, client.getDiscount());
        statement.setString(2, client.getFirstName());
        statement.setString(3, client.getSecondName());
        statement.setString(4, client.getPatronymic());
        statement.setString(5, client.getEmail());
        statement.setString(6, client.getAddress().getCountry());
        statement.setInt(7, client.getAddress().getPostcode());
        statement.setString(8, client.getAddress().getState());
        statement.setString(9, client.getAddress().getRegion());
        statement.setString(10, client.getAddress().getCity());
        statement.setString(11, client.getAddress().getStreet());
        statement.setInt(12, client.getAddress().getHouseNumber());
        statement.setInt(13, client.getAddress().getApartmentNumber());
        statement.setLong(14, client.getId());
    }
}

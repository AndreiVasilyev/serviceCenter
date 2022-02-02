package by.epam.jwdsc.dao.impl;

import by.epam.jwdsc.dao.UserDao;
import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.entity.Client;
import by.epam.jwdsc.dao.ClientDao;
import by.epam.jwdsc.entity.UserBuilders;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import static by.epam.jwdsc.dao.ColumnName.*;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class ClientDaoImpl extends UserDao implements ClientDao {

    private static final String SQL_SELECT_ALL_CLIENTS = "SELECT c.user_id, c.discount, u.first_name, u.second_name, " +
            "u.patronymic, u.email, a.address_id, a.country, a.postcode, a.state, a.region, a.city, a.street, " +
            "a.house_number, a.apartment_number, GROUP_CONCAT(p.phone_number) AS phone_number FROM clients AS c " +
            "JOIN users AS u USING (user_id) JOIN addresses AS a ON (u.address=a.address_id) LEFT JOIN phone_numbers AS p " +
            "ON(u.user_id = p.user_id) GROUP BY u.user_id";
    private static final String SQL_SELECT_CLIENT_BY_ID = "SELECT c.user_id, c.discount, u.first_name, u.second_name, " +
            "u.patronymic, u.email, a.address_id, a.country, a.postcode, a.state, a.region, a.city, a.street, " +
            "a.house_number, a.apartment_number, GROUP_CONCAT(p.phone_number) AS phone_number FROM clients AS c " +
            "JOIN users AS u USING (user_id) JOIN addresses AS a ON (u.address=a.address_id) LEFT JOIN phone_numbers AS p " +
            "ON(u.user_id = p.user_id) WHERE u.user_id=? GROUP BY u.user_id";
    private static final String SQL_SELECT_CLIENTS_BY_PHONE = "SELECT c.user_id, c.discount, u.first_name, u.second_name, " +
            "u.patronymic, u.email, a.address_id, a.country, a.postcode, a.state, a.region, a.city, a.street, " +
            "a.house_number, a.apartment_number, GROUP_CONCAT(p.phone_number) AS phone_number FROM clients AS c " +
            "JOIN users AS u USING (user_id) JOIN addresses AS a ON (u.address=a.address_id) LEFT JOIN phone_numbers AS p " +
            "ON(u.user_id = p.user_id) WHERE u.user_id = ANY (SELECT pn.user_id FROM phone_numbers AS pn " +
            "WHERE pn.phone_number=?) GROUP BY u.user_id";
    private static final String SQL_DELETE_CLIENT_BY_ID = "DELETE c, u, a, p FROM clients AS c JOIN users AS u " +
            "USING (user_id) JOIN addresses AS a ON (u.address=a.address_id) JOIN phone_numbers AS p " +
            "ON(u.user_id = p.user_id) WHERE u.user_id=?";
    private static final String SQL_CREATE_ADDRESS = "INSERT INTO addresses(country, postcode, state, region, city," +
            "street, house_number, apartment_number) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_CREATE_USER = "INSERT INTO users(first_name, second_name, patronymic, address, " +
            "email) VALUES(?,?,?,?,?)";
    private static final String SQL_CREATE_CLIENT = "INSERT INTO clients(user_id, discount) VALUES(?,?)";
    private static final String SQL_UPDATE_CLIENT = "UPDATE clients AS c JOIN users AS u USING (user_id) " +
            "JOIN addresses AS a ON (u.address=a.address_id) SET c.discount=?, u.first_name=?, u.second_name=?, " +
            "u.patronymic=?, u.email=?, a.country=?, a.postcode=?, a.state=?, a.region=?, a.city=?, a.street=?, " +
            "a.house_number=?, a.apartment_number=? WHERE u.user_id=?";

    @Override
    public List<Client> findAll() throws DaoException {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CLIENTS)) {
            while (resultSet.next()) {
                Client client = extractClient(resultSet);
                clients.add(client);
            }
        } catch (SQLException e) {
            log.error("Error executing query findAll from Clients", e);
            throw new DaoException("Error executing query findAll from Clients", e);
        }
        return clients;
    }

    @Override
    public List<Client> findByPhone(String phoneNumber) throws DaoException {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_CLIENTS_BY_PHONE)) {
            statement.setString(1, phoneNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Client client = extractClient(resultSet);
                    clients.add(client);
                }
            }
        } catch (SQLException e) {
            log.error("Error executing query findByPhone from Clients", e);
            throw new DaoException("Error executing query findByPhone from Clients", e);
        }
        return clients;
    }

    @Override
    public Optional<Client> findById(long id) throws DaoException {
        Optional<Client> client = Optional.empty();
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_CLIENT_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    client = Optional.of(extractClient(resultSet));
                }
            }
        } catch (SQLException e) {
            log.error("Error executing query findById from Clients", e);
            throw new DaoException("Error executing query findById from Clients", e);
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
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CLIENT_BY_ID)) {
            statement.setLong(1, id);
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Error executing query deleteById from Clients", e);
            throw new DaoException("Error executing query deleteById from Clients", e);
        }
        return result;
    }

    @Override
    public boolean create(Client client) throws DaoException {
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statementNewAddress = connection.prepareStatement(SQL_CREATE_ADDRESS, RETURN_GENERATED_KEYS);
             PreparedStatement statementNewUser = connection.prepareStatement(SQL_CREATE_USER, RETURN_GENERATED_KEYS);
             PreparedStatement statementNewClient = connection.prepareStatement(SQL_CREATE_CLIENT);
             PreparedStatement statementNewPhones = connection.prepareStatement(SQL_CREATE_PHONE_NUMBER)) {
            connection.setAutoCommit(false);
            collectCreateAddressQuery(statementNewAddress, client);
            statementNewAddress.executeUpdate();
            try (ResultSet generatedAddressKey = statementNewAddress.getGeneratedKeys()) {
                client.getAddress().setId(generatedAddressKey.getLong(1));
                collectCreateUserQuery(statementNewUser, client);
                statementNewUser.executeUpdate();
                try (ResultSet generatedUserKey = statementNewUser.getGeneratedKeys()) {
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
                }
            }
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
    public Optional<Client> update(Client client) throws DaoException {
        Optional<Client> oldClientFound = findById(client.getId());
        if (oldClientFound.isPresent()) {
            Client oldClient = oldClientFound.get();
            try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_CLIENT)) {
                collectUpdateClientQuery(statement, client);
                statement.executeUpdate();
                updatePhoneNumbers(connection, client, oldClient);
            } catch (SQLException e) {
                log.error("Error executing query update Client", e);
                throw new DaoException("Error executing query update Client", e);
            }
        }
        return oldClientFound;
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

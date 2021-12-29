package by.epam.jwdsc.dao.impl;

import by.epam.jwdsc.dao.CompanyDao;
import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.jwdsc.dao.ColumnName.*;

public class CompanyDapImpl implements CompanyDao {

    private static final String SQL_SELECT_ALL_COMPANIES = "SELECT c.company_id, c.name, c.is_service_contract " +
            "FROM companies AS c";
    private static final String SQL_SELECT_COMPANY_BY_ID = "SELECT c.company_id, c.name, c.is_service_contract " +
            "FROM companies AS c WHERE c.company_id=?";
    private static final String SQL_DELETE_COMPANY_BY_ID = "DELETE c FROM companies AS c WHERE c.company_id=?";
    private static final String SQL_CREATE_COMPANY = "INSERT INTO companies(name, is_service_contract) VALUES(?,?)";
    private static final String SQL_UPDATE_COMPANY = "UPDATE companies AS c SET c.name=?, c.is_service_contract=? " +
            "WHERE c.company_id=?";

    @Override
    public List<Company> findAll() throws DaoException {
        List<Company> companies = new ArrayList<>();
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_COMPANIES)) {
            while (resultSet.next()) {
                Company company = extractCompany(resultSet);
                companies.add(company);
            }
        } catch (SQLException e) {
            log.error("Error executing query findAll from Companies", e);
            throw new DaoException("Error executing query findAll from Companies", e);
        } finally {
            close(connection);
        }
        return companies;
    }

    @Override
    public Optional<Company> findById(long id) throws DaoException {
        Optional<Company> company;
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COMPANY_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                company = Optional.ofNullable(extractCompany(resultSet));

            } else {
                company = Optional.empty();
            }
        } catch (SQLException e) {
            log.error("Error executing query findById from Companies", e);
            throw new DaoException("Error executing query findById from Companies", e);
        } finally {
            close(connection);
        }
        return company;
    }

    @Override
    public boolean delete(Company company) throws DaoException {
        return deleteById(company.getId());
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result = false;
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_COMPANY_BY_ID)) {
            statement.setLong(1, id);
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Error executing query deleteById from Companies", e);
            throw new DaoException("Error executing query deleteById from Companies", e);
        } finally {
            close(connection);
        }
        return result;
    }

    @Override
    public boolean create(Company company) throws DaoException {
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_COMPANY)) {
            statement.setString(1, company.getName());
            statement.setBoolean(2, company.isContract());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing query create new Company", e);
            throw new DaoException("Error executing query create new Company", e);
        } finally {
            close(connection);
        }
        return true;
    }

    @Override
    public Company update(Company company) throws DaoException {
        Company oldCompany = findById(company.getId()).get();
        Connection connection = DbConnectionPool.INSTANCE.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_COMPANY)) {
            statement.setString(1, company.getName());
            statement.setBoolean(2, company.isContract());
            statement.setLong(3, company.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing query update Device", e);
            throw new DaoException("Error executing query update Device", e);
        } finally {
            close(connection);
        }
        return oldCompany;
    }

    private Company extractCompany(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COMPANIES_ID);
        String name = resultSet.getString(COMPANIES_NAME);
        boolean isServiceContract = resultSet.getBoolean(COMPANIES_IS_SERVICE_CONTRACT);
        return new Company(id, name, isServiceContract);
    }
}

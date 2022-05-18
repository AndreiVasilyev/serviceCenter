package by.epam.jwdsc.dao.impl;

import by.epam.jwdsc.dao.CompanyDao;
import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.pool.DbConnectionPool;

import java.sql.*;
import java.util.*;

import static by.epam.jwdsc.dao.ColumnName.*;

public class CompanyDaoImpl implements CompanyDao {

    private static final String SQL_SELECT_ALL_COMPANIES = "SELECT c.company_id, c.name, c.is_service_contract " +
            "FROM companies AS c";
    private static final String SQL_SELECT_COMPANY_BY_ID = "SELECT c.company_id, c.name, c.is_service_contract " +
            "FROM companies AS c WHERE c.company_id=?";
    private static final String SQL_SELECT_COMPANIES_TEMPLATE = "SELECT co.company_id, co.name, co.is_service_contract" +
            " FROM companies AS co %s %s";
    private static final String SQL_DELETE_COMPANY_BY_ID = "DELETE c FROM companies AS c WHERE c.company_id=?";
    private static final String SQL_CREATE_COMPANY = "INSERT INTO companies(name, is_service_contract) VALUES(?,?)";
    private static final String SQL_UPDATE_COMPANY = "UPDATE companies AS c SET c.name=?, c.is_service_contract=? " +
            "WHERE c.company_id=?";

    @Override
    public List<Company> findAll() throws DaoException {
        List<Company> companies = new ArrayList<>();
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_COMPANIES)) {
            while (resultSet.next()) {
                Company company = extractCompany(resultSet);
                companies.add(company);
            }
        } catch (SQLException e) {
            log.error("Error executing query findAll from Companies", e);
            throw new DaoException("Error executing query findAll from Companies", e);
        }
        return companies;
    }

    @Override
    public Optional<Company> findById(long id) throws DaoException {
        Optional<Company> company = Optional.empty();
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_COMPANY_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    company = Optional.of(extractCompany(resultSet));
                }
            }
        } catch (SQLException e) {
            log.error("Error executing query findById from Companies", e);
            throw new DaoException("Error executing query findById from Companies", e);
        }
        return company;
    }

    @Override
    public List<Company> findByParametersWithSort(LinkedHashMap<String, Object> parameters, String sort) throws DaoException {
        String whereBlock = prepareWhereBlock(parameters.keySet());
        String sortBlock = prepareSortBlock(sort);
        String selectQuery = String.format(SQL_SELECT_COMPANIES_TEMPLATE, whereBlock, sortBlock);
        return findCompanies(selectQuery, parameters.values());
    }

    @Override
    public boolean delete(Company company) throws DaoException {
        return deleteById(company.getId());
    }

    @Override
    public boolean deleteById(long id) throws DaoException {
        boolean result = false;
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_COMPANY_BY_ID)) {
            statement.setLong(1, id);
            int updatedRows = statement.executeUpdate();
            if (updatedRows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            log.error("Error executing query deleteById from Companies", e);
            throw new DaoException("Error executing query deleteById from Companies", e);
        }
        return result;
    }

    @Override
    public boolean create(Company company) throws DaoException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_COMPANY)) {
            statement.setString(1, company.getName());
            statement.setBoolean(2, company.isContract());
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing query create new Company", e);
            throw new DaoException("Error executing query create new Company", e);
        }
        return true;
    }

    @Override
    public long createCompany(Company company) throws DaoException {
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_COMPANY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, company.getName());
            statement.setBoolean(2, company.isContract());
            statement.executeUpdate();
            try (ResultSet generatedCompanyKey = statement.getGeneratedKeys()) {
                generatedCompanyKey.next();
                return generatedCompanyKey.getLong(1);
            }
        } catch (SQLException e) {
            log.error("Error executing query create new Company", e);
            throw new DaoException("Error executing query create new Company", e);
        }
    }

    @Override
    public Optional<Company> update(Company company) throws DaoException {
        Optional<Company> oldCompanyFound = findById(company.getId());
        if (oldCompanyFound.isPresent()) {
            try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
                 PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_COMPANY)) {
                statement.setString(1, company.getName());
                statement.setBoolean(2, company.isContract());
                statement.setLong(3, company.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                log.error("Error executing query update Device", e);
                throw new DaoException("Error executing query update Device", e);
            }
        }
        return oldCompanyFound;
    }

    private List<Company> findCompanies(String selectQuery, Collection<Object> parameters) throws DaoException {
        List<Company> companies = new ArrayList<>();
        try (Connection connection = DbConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            prepareStatement(preparedStatement, parameters);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Company company = extractCompany(resultSet);
                    companies.add(company);
                }

            }
            return companies;
        } catch (SQLException e) {
            log.error("Error executing query find companies", e);
            throw new DaoException("Error executing query find companies", e);
        }
    }

    private Company extractCompany(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COMPANIES_ID);
        String name = resultSet.getString(COMPANIES_NAME);
        boolean isServiceContract = resultSet.getBoolean(COMPANIES_IS_SERVICE_CONTRACT);
        return new Company(id, name, isServiceContract);
    }
}

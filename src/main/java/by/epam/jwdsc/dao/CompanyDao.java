package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.exception.DaoException;

public interface CompanyDao extends BaseDao<Company> {
    long createCompany(Company company) throws DaoException;
}

package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.exception.DaoException;

import java.util.LinkedHashMap;
import java.util.List;

public interface CompanyDao extends BaseDao<Company> {
    List<Company> findByParametersWithSort(LinkedHashMap<String, Object> parameters, String sort) throws DaoException;

    long createCompany(Company company) throws DaoException;
}

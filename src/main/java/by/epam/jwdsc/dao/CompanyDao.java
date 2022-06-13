package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.exception.DaoException;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * The interface Company dao.
 */
public interface CompanyDao extends BaseDao<Company> {
    /**
     * Find by parameters with sort list.
     *
     * @param parameters the parameters
     * @param sort       the sort
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Company> findByParametersWithSort(LinkedHashMap<String, Object> parameters, String sort) throws DaoException;

    /**
     * Create company long.
     *
     * @param company the company
     * @return the long
     * @throws DaoException the dao exception
     */
    long createCompany(Company company) throws DaoException;
}

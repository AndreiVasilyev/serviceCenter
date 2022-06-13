package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.entity.dto.CompanyData;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;

/**
 * The interface Company service.
 */
public interface CompanyService {

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Company> findAll() throws ServiceException;

    /**
     * Find companies by parameters list.
     *
     * @param companyData the company data
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Company> findCompaniesByParameters(CompanyData companyData) throws ServiceException;

    /**
     * Create company long.
     *
     * @param name       the name
     * @param isContract the is contract
     * @return the long
     * @throws ServiceException the service exception
     */
    long createCompany(String name, boolean isContract) throws ServiceException;
}

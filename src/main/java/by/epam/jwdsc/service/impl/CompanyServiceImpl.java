package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.CompanyDao;
import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.dao.DeviceDao;
import by.epam.jwdsc.dao.QueryParametersMapper;
import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.entity.dto.CompanyData;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.CompanyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * The type Company service.
 */
public class CompanyServiceImpl implements CompanyService {

    private static final Logger log = LogManager.getLogger();
    private CompanyDao companyDao;

    /**
     * Instantiates a new Company service.
     */
    public CompanyServiceImpl() {
        this.companyDao = DaoProvider.getInstance().getCompanyDao();
    }

    /**
     * Instantiates a new Company service.
     *
     * @param companyDao the company dao
     */
    public CompanyServiceImpl(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @Override
    public List<Company> findAll() throws ServiceException {
        try {
            return companyDao.findAll();
        } catch (DaoException e) {
            log.error("Error in service find all companies", e);
            throw new ServiceException("Error in service find all companies", e);
        }
    }

    @Override
    public List<Company> findCompaniesByParameters(CompanyData companyData) throws ServiceException {
        QueryParametersMapper queryParametersMapper = QueryParametersMapper.getInstance();
        LinkedHashMap<String, Object> parameters = queryParametersMapper.mapCompanyParameters(companyData);
        String sort = queryParametersMapper.mapCompanySort(companyData);
        try {
            return companyDao.findByParametersWithSort(parameters, sort);
        } catch (DaoException e) {
            log.error("Error executing service find companies by parameters with sort");
            throw new ServiceException("Error executing service find companies by parameters with sort", e);
        }
    }

    @Override
    public long createCompany(String name, boolean isContract) throws ServiceException {
        try {
            return companyDao.createCompany(new Company(name, isContract));
        } catch (DaoException e) {
            log.error("Error executing service create company", e);
            throw new ServiceException("Error executing service create company", e);
        }
    }
}

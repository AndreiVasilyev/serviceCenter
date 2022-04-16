package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.CompanyDao;
import by.epam.jwdsc.dao.DaoProvider;
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

public class CompanyServiceImpl implements CompanyService {

    private static final Logger log = LogManager.getLogger();

    @Override
    public List<Company> findAll() throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        CompanyDao companyDao = daoProvider.getCompanyDao();
        try {
            return companyDao.findAll();
        } catch (DaoException e) {
            log.error("Error in service find all companies", e);
            throw new ServiceException("Error in service find all companies", e);
        }
    }

    @Override
    public List<Company> findCompaniesByParameters(CompanyData companyData) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        CompanyDao companyDao = daoProvider.getCompanyDao();
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
        DaoProvider daoProvider = DaoProvider.getInstance();
        CompanyDao companyDao = daoProvider.getCompanyDao();
        try {
            return companyDao.createCompany(new Company(name, isContract));
        } catch (DaoException e) {
            log.error("Error executing service create company", e);
            throw new ServiceException("Error executing service create company", e);
        }
    }
}

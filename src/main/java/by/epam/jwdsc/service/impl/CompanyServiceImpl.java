package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.CompanyDao;
import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.CompanyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
}

package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.entity.dto.CompanyData;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;

public interface CompanyService {

    List<Company> findAll() throws ServiceException;

    List<Company> findCompaniesByParameters(CompanyData companyData) throws ServiceException;

    long createCompany(String name, boolean isContract) throws ServiceException;
}

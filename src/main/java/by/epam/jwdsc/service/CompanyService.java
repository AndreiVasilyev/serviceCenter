package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;

public interface CompanyService {

    List<Company> findAll() throws ServiceException;
}

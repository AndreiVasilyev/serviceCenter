package by.epam.jwdsc.service;

import by.epam.jwdsc.dao.impl.CompanyDaoImpl;
import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.entity.dto.CompanyData;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.impl.CompanyServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {

    @Mock
    private CompanyDaoImpl companyDao;
    @InjectMocks
    private CompanyServiceImpl companyService = new CompanyServiceImpl();
    private Company testCompany;

    @Before
    public void setUp() throws DaoException {
        testCompany = new Company("testCompany", true);
    }

    @Test
    public void findAllPositiveResult() throws ServiceException, DaoException {
        when(companyDao.findAll()).thenReturn(new ArrayList<>(Collections.nCopies(5, testCompany)));
        List<Company> foundCompanies = companyService.findAll();
        assertThat(foundCompanies)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .element(0)
                .hasFieldOrPropertyWithValue("name", "testCompany")
                .hasFieldOrPropertyWithValue("isContract", true);
    }

    @Test(expected = ServiceException.class)
    public void findAllNegativeResult() throws ServiceException, DaoException {
        when(companyDao.findAll()).thenThrow(DaoException.class);
        companyService.findAll();
    }

    @Test
    public void findCompaniesByParametersPositiveResult() throws ServiceException, DaoException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("co.name LIKE ? ", "%testName%");
        CompanyData companyData = new CompanyData();
        companyData.setName("testName");
        when(companyDao.findByParametersWithSort(params, "")).thenReturn(new ArrayList<>(Collections.nCopies(5, testCompany)));
        List<Company> foundCompanies = companyService.findCompaniesByParameters(companyData);
        assertThat(foundCompanies)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .element(0)
                .hasFieldOrPropertyWithValue("name", "testCompany")
                .hasFieldOrPropertyWithValue("isContract", true);
    }

    @Test
    public void findCompaniesByParametersNegativeResult() throws ServiceException, DaoException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("c.name LIKE ? ", "%testName%");
        CompanyData companyData = new CompanyData();
        companyData.setName("test");
        when(companyDao.findByParametersWithSort(params, "")).thenReturn(new ArrayList<>());
        List<Company> foundCompanies = companyService.findCompaniesByParameters(companyData);
        assertThat(foundCompanies)
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void createCompanyPositiveResult() throws ServiceException, DaoException {
        when(companyDao.createCompany(testCompany)).thenReturn(5L);
        long createdCompanyId = companyService.createCompany("testCompany", true);
        assertThat(createdCompanyId)
                .isNotZero()
                .isPositive()
                .isEqualTo(5L);
    }

    @Test(expected = ServiceException.class)
    public void createCompanyNegativeResult() throws ServiceException, DaoException {
        when(companyDao.createCompany(testCompany)).thenThrow(DaoException.class);
        companyService.createCompany("testCompany", true);
    }


}

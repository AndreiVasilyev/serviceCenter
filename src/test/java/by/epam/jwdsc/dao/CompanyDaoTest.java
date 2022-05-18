package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.exception.DaoException;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class CompanyDaoTest {
    private CompanyDao companyDao;
    private Company testCompany;

    @Before
    public void setUp() {
        companyDao = DaoProvider.getInstance().getCompanyDao();
        testCompany = new Company("testCompany", true);
    }

    @Test
    public void findAll() throws DaoException {
        List<Company> foundCompanies = companyDao.findAll();
        assertThat(foundCompanies)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(10);
    }

    @Test
    public void findByIdPositiveResult() throws DaoException {
        Optional<Company> foundCompany = companyDao.findById(1);
        assertThat(foundCompany)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("name", "Samsung")
                .hasFieldOrPropertyWithValue("isContract", true);
    }

    @Test
    public void findByIdNegativeResult() throws DaoException {
        Optional<Company> foundCompany = companyDao.findById(0);
        assertThat(foundCompany)
                .isEmpty();
    }

    @Test
    public void findByParametersWithSortPositiveResult() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("co.name LIKE ? ", "%a%");
        String sortParam = "co.name";
        List<Company> foundCompanies = companyDao.findByParametersWithSort(parameters, "");
        List<Company> foundCompaniesWithSort = companyDao.findByParametersWithSort(parameters, sortParam);
        assertThat(foundCompanies)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1);
        assertThat(foundCompaniesWithSort)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1)
                .hasSize(foundCompanies.size())
                .isSortedAccordingTo(Comparator.comparing(Company::getName));
        sortParam = "co.name DESC";
        foundCompaniesWithSort = companyDao.findByParametersWithSort(parameters, sortParam);
        assertThat(foundCompaniesWithSort)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1)
                .hasSize(foundCompanies.size())
                .containsAll(foundCompaniesWithSort)
                .isSortedAccordingTo((o1, o2) -> o2.getName().compareTo(o1.getName()));
    }

    @Test(expected = DaoException.class)
    public void findByParametersWithSortWithException() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("co.name", "f");
        String sortParam = "co.name";
        companyDao.findByParametersWithSort(parameters, sortParam);
    }

    @Test
    public void create() throws DaoException {
        boolean isCreatedCompany = companyDao.create(testCompany);
        assertThat(isCreatedCompany)
                .isTrue();
        List<Company> createdCompany = companyDao.findByParametersWithSort(new LinkedHashMap<>(Map.of("co.name LIKE ? ", "testCompany")), "");
        assertThat(createdCompany)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .element(0)
                .hasFieldOrPropertyWithValue("name", "testCompany");
        companyDao.delete(createdCompany.get(0));
    }

    @Test
    public void createDevice() throws DaoException {
        long createdCompanyId = companyDao.createCompany(testCompany);
        assertThat(createdCompanyId)
                .isNotZero()
                .isPositive();
        Optional<Company> createdCompany = companyDao.findById(createdCompanyId);
        assertThat(createdCompany)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("name", "testCompany");
        companyDao.delete(createdCompany.get());
    }

    @Test
    public void update() throws DaoException {
        long createdCompanyId = companyDao.createCompany(testCompany);
        Company createdCompany = companyDao.findById(createdCompanyId).get();
        createdCompany.setName("updatedName");
        Optional<Company> oldCompany = companyDao.update(createdCompany);
        assertThat(oldCompany)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("name", "testCompany");
        Optional<Company> updatedCompany = companyDao.findById(createdCompanyId);
        assertThat(updatedCompany)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("name", "updatedName");
        companyDao.deleteById(createdCompanyId);
    }

    @Test
    public void delete() throws DaoException {
        long createdCompanyId = companyDao.createCompany(testCompany);
        Company createdCompany = companyDao.findById(createdCompanyId).get();
        boolean isDeleted = companyDao.delete(createdCompany);
        assertThat(isDeleted)
                .isTrue();
    }

    @Test
    public void deleteById() throws DaoException {
        long createdCompanyId = companyDao.createCompany(testCompany);
        boolean isDeleted = companyDao.deleteById(createdCompanyId);
        assertThat(isDeleted)
                .isTrue();
    }
}

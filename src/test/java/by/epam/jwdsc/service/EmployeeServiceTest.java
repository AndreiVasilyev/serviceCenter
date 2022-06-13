package by.epam.jwdsc.service;

import by.epam.jwdsc.dao.impl.EmployeeDaoImpl;
import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.entity.dto.EmployeeParameters;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.impl.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * The type Employee service test.
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeDaoImpl employeeDao;
    @InjectMocks
    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
    private Employee testEmployee;

    /**
     * Sets up.
     *
     * @throws DaoException the dao exception
     */
    @Before
    public void setUp() throws DaoException {
        Address testAddress = new Address.Builder("testCity", "testStreet", 1111)
                .state("testState")
                .region("testRegion")
                .country("testCountry")
                .postcode(111111)
                .apartmentNumber(1111)
                .build();
        testEmployee = UserBuilders.newEmployee()
                .firstName("testFirstName")
                .userRole(UserRole.MANAGER)
                .secondName("testSecondName")
                .patronymic("testPatronymicName")
                .address(testAddress)
                .email("testEmail")
                .login("testLogin")
                .password("testPassword")
                .phones(List.of("375111111111", "375222222222"))
                .build();
    }

    /**
     * Find all positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findAllPositiveResult() throws ServiceException, DaoException {
        when(employeeDao.findAll()).thenReturn(new ArrayList<>(Collections.nCopies(10, testEmployee)));
        List<Employee> foundEmployees = employeeService.findAll();
        assertThat(foundEmployees)
                .isNotNull()
                .isNotEmpty()
                .hasSize(10)
                .element(0)
                .hasFieldOrPropertyWithValue("secondName", "testSecondName")
                .hasFieldOrPropertyWithValue("login", "testLogin");
    }

    /**
     * Find by id positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findByIdPositiveResult() throws ServiceException, DaoException {
        when(employeeDao.findById(5L)).thenReturn(Optional.ofNullable(testEmployee));
        Optional<Employee> foundEmployee = employeeService.findById(5L);
        assertThat(foundEmployee)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("secondName", "testSecondName")
                .hasFieldOrPropertyWithValue("login", "testLogin");
    }

    /**
     * Find by id negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findByIdNegativeResult() throws ServiceException, DaoException {
        when(employeeDao.findById(-5L)).thenReturn(Optional.empty());
        Optional<Employee> foundEmployee = employeeService.findById(-5L);
        assertThat(foundEmployee)
                .isEmpty();
    }

    /**
     * Find employees by parameters positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findEmployeesByParametersPositiveResult() throws ServiceException, DaoException {
        EmployeeParameters employeeParameters = new EmployeeParameters();
        employeeParameters.setSecondName("а");
        employeeParameters.setSortByName("u.first_name");
        when(employeeDao.findByParamsWithSort(any(), anyString())).thenReturn(new ArrayList<>(Collections.nCopies(5, testEmployee)));
        List<Employee> foundResult = employeeService.findEmployeesByParameters(employeeParameters);
        assertThat(foundResult)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .element(0)
                .hasFieldOrPropertyWithValue("secondName", "testSecondName")
                .hasFieldOrPropertyWithValue("login", "testLogin");
    }

    /**
     * Find employees by parameters negative result.
     *
     * @throws ServiceException the service exception
     */
    @Test
    public void findEmployeesByParametersNegativeResult() throws ServiceException {
        EmployeeParameters employeeParameters = new EmployeeParameters();
        employeeParameters.setSortByName("first_name");
        List<Employee> foundResult = employeeService.findEmployeesByParameters(employeeParameters);
        assertThat(foundResult)
                .isEmpty();
    }

    /**
     * Authorize positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void authorizePositiveResult() throws ServiceException, DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("e.login =? ", "testLogin");
        testEmployee.setPassword("a9bed6e9ea4acbe1187eee5f3a16ad44:7e6c619ccb30b46bb7bc6905e54626f7fe35638b27d12b0297adafa54c67c30f");
        when(employeeDao.findByParams(parameters)).thenReturn(new ArrayList<>(List.of(testEmployee)));
        Optional<Employee> authorizedEmployee = employeeService.authorize("testLogin", "superroot");
        assertThat(authorizedEmployee)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("secondName", "testSecondName")
                .hasFieldOrPropertyWithValue("login", "testLogin");
        testEmployee.setPassword("password");
        authorizedEmployee = employeeService.authorize("testLogin", "superroot");
        assertThat(authorizedEmployee)
                .isEmpty();
    }

    /**
     * Authorize negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test(expected = ServiceException.class)
    public void authorizeNegativeResult() throws ServiceException, DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("e.login =? ", "testLogin");
        testEmployee.setPassword("a9bed6e9ea4acbe1187eee5f3a16ad44:7e6c619ccb30b46bb7bc6905e54626f7fe35638b27d12b0297adafa54c67c30f");
        when(employeeDao.findByParams(parameters)).thenThrow(DaoException.class);
        employeeService.authorize("testLogin", "superroot");
    }

    /**
     * Find registered employee positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findRegisteredEmployeePositiveResult() throws ServiceException, DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("e.login =? ", "testLogin");
        testEmployee.setUserRole(UserRole.ADMIN);
        testEmployee.setPassword("");
        when(employeeDao.findByParams(parameters)).thenReturn(new ArrayList<>(List.of(testEmployee)));
        Optional<Employee> foundEmployee = employeeService.findRegisteredEmployee("testLogin", "ADMIN");
        assertThat(foundEmployee)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("secondName", "testSecondName")
                .hasFieldOrPropertyWithValue("login", "testLogin");
        testEmployee.setPassword("password");
        foundEmployee = employeeService.findRegisteredEmployee("testLogin", "ADMIN");
        assertThat(foundEmployee)
                .isEmpty();
    }

    /**
     * Find registered employee negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test(expected = ServiceException.class)
    public void findRegisteredEmployeeNegativeResult() throws ServiceException, DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("e.login =? ", "testLogin");
        when(employeeDao.findByParams(parameters)).thenThrow(DaoException.class);
        employeeService.findRegisteredEmployee("testLogin", "ADMIN");
    }

    /**
     * Update employee positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void updateEmployeePositiveResult() throws ServiceException, DaoException {
        when(employeeDao.update(testEmployee)).thenReturn(Optional.of(testEmployee));
        Optional<Employee> updatedEmployee = employeeService.updateEmployee(testEmployee);
        assertThat(updatedEmployee)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("secondName", "testSecondName")
                .hasFieldOrPropertyWithValue("login", "testLogin");
    }

    /**
     * Update employee negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test(expected = ServiceException.class)
    public void updateEmployeeNegativeResult() throws ServiceException, DaoException {
        when(employeeDao.update(testEmployee)).thenThrow(DaoException.class);
        employeeService.updateEmployee(testEmployee);
    }

    /**
     * Create employee positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void createEmployeePositiveResult() throws ServiceException, DaoException {
        when(employeeDao.create(testEmployee)).thenReturn(true);
        boolean isCreated = employeeService.createEmployee(testEmployee);
        assertThat(isCreated)
                .isTrue();
    }

    /**
     * Create employee negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test(expected = ServiceException.class)
    public void createEmployeeNegativeResult() throws ServiceException, DaoException {
        when(employeeDao.create(testEmployee)).thenThrow(DaoException.class);
        employeeService.createEmployee(testEmployee);
    }

    /**
     * Check login positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void checkLoginPositiveResult() throws ServiceException, DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("e.login =? ", "testLogin");
        when(employeeDao.findByParams(parameters)).thenReturn(new ArrayList<>(List.of(testEmployee)));
        boolean isLoginRegistered = employeeService.checkLogin("testLogin");
        assertThat(isLoginRegistered)
                .isTrue();
    }

    /**
     * Check login negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void checkLoginNegativeResult() throws ServiceException, DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("e.login =? ", "login");
        when(employeeDao.findByParams(parameters)).thenReturn(new ArrayList<>());
        boolean isLoginRegistered = employeeService.checkLogin("testLogin");
        assertThat(isLoginRegistered)
                .isFalse();
    }

    /**
     * Registration employee positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void registrationEmployeePositiveResult() throws ServiceException, DaoException {
        EmployeeParameters employeeParameters = new EmployeeParameters();
        employeeParameters.setId("0");
        employeeParameters.setHouseNumber("0");
        employeeParameters.setUserRole("ADMIN");
        employeeParameters.setPhones("");
        employeeParameters.setPostcode("");
        employeeParameters.setApartmentNumber("");
        when(employeeDao.update(any())).thenReturn(Optional.of(testEmployee));
        Optional<Employee> oldEmployee = employeeService.registrationEmployee(employeeParameters, "password");
        assertThat(oldEmployee)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("secondName", "testSecondName")
                .hasFieldOrPropertyWithValue("login", "testLogin");
    }

    /**
     * Registration employee negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test(expected = ServiceException.class)
    public void registrationEmployeeNegativeResult() throws ServiceException, DaoException {
        EmployeeParameters employeeParameters = new EmployeeParameters();
        when(employeeDao.update(any())).thenReturn(Optional.of(testEmployee));
        employeeService.registrationEmployee(employeeParameters, "password");
    }

}

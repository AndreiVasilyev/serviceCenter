package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.exception.DaoException;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeDaoTest {
    private EmployeeDao employeeDao;
    private Employee testEmployee;

    @Before
    public void setUp() {
        employeeDao = DaoProvider.getInstance().getEmployeeDao();
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

    @Test
    public void findAll() throws DaoException {
        List<Employee> foundEmployees = employeeDao.findAll();
        assertThat(foundEmployees)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(10);
    }

    @Test
    public void findByIdPositiveResult() throws DaoException {
        Optional<Employee> foundEmployee = employeeDao.findById(1);
        assertThat(foundEmployee)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("id", 1L);
    }

    @Test
    public void findByIdNegativeResult() throws DaoException {
        Optional<Employee> foundEmployee = employeeDao.findById(0);
        assertThat(foundEmployee)
                .isEmpty();
    }

    @Test
    public void findByParamsPositiveResult() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("a.city LIKE ? ", "%н%");
        parameters.put("u.user_role LIKE ? ", "%manager%");
        parameters.put("u.first_name LIKE ?", "%а%");
        List<Employee> foundEmployees = employeeDao.findByParams(parameters);
        assertThat(foundEmployees)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1)
                .element(0)
                .hasFieldOrPropertyWithValue("userRole", UserRole.MANAGER);
    }

    @Test
    public void findByParamsNegativeResult() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("a.city LIKE ? ", "%x%");
        List<Employee> foundEmployees = employeeDao.findByParams(parameters);
        assertThat(foundEmployees)
                .isNotNull()
                .isEmpty();
    }

    @Test(expected = DaoException.class)
    public void findByParamsWithException() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("a.city", "%x%");
        employeeDao.findByParams(parameters);
    }

    @Test
    public void findByParamsWithSortPositiveResult() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("a.city LIKE ? ", "%а%");
        String sortParam = "u.first_name";
        List<Employee> foundEmployees = employeeDao.findByParams(parameters);
        List<Employee> foundEmployeesWithSort = employeeDao.findByParamsWithSort(parameters, sortParam);
        assertThat(foundEmployees)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1);
        assertThat(foundEmployeesWithSort)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1)
                .hasSize(foundEmployees.size())
                .containsAll(foundEmployees)
                .isSortedAccordingTo(Comparator.comparing(AbstractUser::getFirstName));
        sortParam = "u.first_name DESC";
        foundEmployeesWithSort = employeeDao.findByParamsWithSort(parameters, sortParam);
        assertThat(foundEmployeesWithSort)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1)
                .hasSize(foundEmployees.size())
                .containsAll(foundEmployees)
                .isSortedAccordingTo((o1, o2) -> o2.getFirstName().compareTo(o1.getFirstName()));
    }

    @Test(expected = DaoException.class)
    public void findByParamsWithSortWithException() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("a.city", "t");
        String sortParam = "u.first_name";
        employeeDao.findByParamsWithSort(parameters, sortParam);
    }

    @Test
    public void create() throws DaoException {
        boolean isCreatedEmployee = employeeDao.create(testEmployee);
        assertThat(isCreatedEmployee)
                .isTrue();
        List<Employee> createdEmployee = employeeDao.findByParams(new LinkedHashMap<>(Map.of("u.first_name LIKE ? ", "testFirstName")));
        assertThat(createdEmployee)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .element(0)
                .hasFieldOrPropertyWithValue("firstName", "testFirstName");
        employeeDao.delete(createdEmployee.get(0));
    }

    @Test
    public void update() throws DaoException {
        employeeDao.create(testEmployee);
        Employee createdEmployee = employeeDao.findByParams(new LinkedHashMap<>(Map.of("u.first_name LIKE ? ", "testFirstName"))).get(0);
        createdEmployee.setEmail("updatedEmail");
        createdEmployee.getAddress().setCity("updatedCity");
        Optional<Employee> oldEmployee = employeeDao.update(createdEmployee);
        assertThat(oldEmployee)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("email", "testEmail")
                .hasFieldOrPropertyWithValue("id", createdEmployee.getId())
                .extracting("address")
                .hasFieldOrPropertyWithValue("city", "testCity");
        Optional<Employee> updatedEmployee = employeeDao.findById(createdEmployee.getId());
        assertThat(updatedEmployee)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("email", "updatedEmail")
                .hasFieldOrPropertyWithValue("id", createdEmployee.getId())
                .extracting("address")
                .hasFieldOrPropertyWithValue("city", "updatedCity");
        employeeDao.deleteById(createdEmployee.getId());
    }

    @Test
    public void delete() throws DaoException {
        employeeDao.create(testEmployee);
        Employee createdEmployee = employeeDao.findByParams(new LinkedHashMap<>(Map.of("u.first_name LIKE ? ", "testFirstName"))).get(0);
        boolean isDeleted = employeeDao.delete(createdEmployee);
        assertThat(isDeleted)
                .isTrue();
    }

    @Test
    public void deleteById() throws DaoException {
        employeeDao.create(testEmployee);
        Employee createdEmployee = employeeDao.findByParams(new LinkedHashMap<>(Map.of("u.first_name LIKE ? ", "testFirstName"))).get(0);
        boolean isDeleted = employeeDao.deleteById(createdEmployee.getId());
        assertThat(isDeleted)
                .isTrue();
    }
}

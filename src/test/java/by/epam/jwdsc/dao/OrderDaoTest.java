package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.exception.DaoException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderDaoTest {

    private OrderDao orderDao;
    private Order testOrder;

    @Before
    public void setUp() throws DaoException {
        orderDao = DaoProvider.getInstance().getOrderDao();
        ClientDao clientDao = DaoProvider.getInstance().getClientDao();
        EmployeeDao employeeDao = DaoProvider.getInstance().getEmployeeDao();
        DeviceDao deviceDao = DaoProvider.getInstance().getDeviceDao();
        Employee testEmployee = employeeDao.findById(1L).orElse(null);
        Client testClient = clientDao.findById(2L).orElse(null);
        Device testDevice = deviceDao.findById(1L).orElse(null);
        testOrder = new Order.Builder(0, "P0", LocalDateTime.now(), testClient, testEmployee, testDevice)
                .orderStatus(OrderStatus.ACCEPTED)
                .build();
    }

    @Test
    public void findAll() throws DaoException {
        List<Order> foundOrders = orderDao.findAll();
        assertThat(foundOrders)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(10);
    }

    @Test
    public void findByIdPositiveResult() throws DaoException {
        Optional<Order> foundOrder = orderDao.findById(2);
        assertThat(foundOrder)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("id", 2L);
    }

    @Test
    public void findByIdNegativeResult() throws DaoException {
        Optional<Order> foundOrder = orderDao.findById(0);
        assertThat(foundOrder)
                .isEmpty();
    }

    @Test
    public void findByParamsPositiveResult() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("o.creation_date LIKE ? ", "%2022-02-05%");
        parameters.put("o.order_number LIKE ?", "%P%");
        List<Order> foundOrders = orderDao.findByParams(parameters);
        assertThat(foundOrders)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(2)
                .element(0)
                .hasFieldOrPropertyWithValue("orderNumber", "P3");
    }

    @Test
    public void findByParamsNegativeResult() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("o.creation_date LIKE ? ", "%1022-02-05%");
        parameters.put("o.order_number LIKE ?", "%P%");
        List<Order> foundOrders = orderDao.findByParams(parameters);
        assertThat(foundOrders)
                .isNotNull()
                .isEmpty();
    }

    @Test(expected = DaoException.class)
    public void findByParamsWithException() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("o.creation_date", "%1022-02-05%");
        orderDao.findByParams(parameters);
    }

    @Test
    public void findByParamsWithSortAndPagePositiveResult() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        String sortParam = "o.model";
        int pageNumber = 2;
        List<Order> foundOrders = orderDao.findByParams(parameters);
        List<Order> foundOrdersWithSortAndPage = orderDao.findByParamsWithSortAndPage(parameters, sortParam, pageNumber);
        int ordersCount = foundOrdersWithSortAndPage.size();
        assertThat(foundOrders)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(5)
                .containsAll(foundOrdersWithSortAndPage);
        assertThat(foundOrdersWithSortAndPage)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(5)
                .hasSizeLessThanOrEqualTo(10)
                .isSortedAccordingTo(Comparator.comparing(Order::getModel));
        sortParam = "o.model DESC";
        List<Order> foundOrdersWithDescSortAndPage = orderDao.findByParamsWithSortAndPage(parameters, sortParam, pageNumber);
        assertThat(foundOrdersWithDescSortAndPage)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(5)
                .hasSizeLessThanOrEqualTo(10)
                .hasSize(ordersCount)
                .doesNotContainSequence(foundOrdersWithSortAndPage)
                .isSortedAccordingTo((o1, o2) -> {
                    if (o1.getModel() == null && o2.getModel() == null) {
                        return 0;
                    }
                    return o1.getModel() == null ? 1 : o2.getModel() == null ? -1 : o2.getModel().compareTo(o1.getModel());
                });
    }

    @Test(expected = DaoException.class)
    public void findByParamsWithSortAndPageWithException() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("o.creation_date", "%1022-02-05%");
        String sortParam = "o.model";
        int pageNumber = 2;
        orderDao.findByParamsWithSortAndPage(parameters, sortParam, pageNumber);
    }

    @Test
    public void countOrdersByParamsPositiveResult() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("o.creation_date LIKE ? ", "%2022-02-05%");
        parameters.put("o.order_number LIKE ?", "%P%");
        long ordersCount = orderDao.countOrdersByParams(parameters);
        assertThat(ordersCount)
                .isNotZero()
                .isPositive()
                .isGreaterThanOrEqualTo(2L);
    }

    @Test
    public void countOrdersByParamsNegativeResult() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("o.creation_date LIKE ? ", "%2020-02-05%");
        parameters.put("o.order_number LIKE ?", "%P%");
        long ordersCount = orderDao.countOrdersByParams(parameters);
        assertThat(ordersCount)
                .isZero();
    }

    @Test(expected = DaoException.class)
    public void countOrdersByParamsWithException() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("o.creation_date", "%2022-02-05%");
        parameters.put("o.order_number LIKE ?", "%P%");
        orderDao.countOrdersByParams(parameters);
    }

    @Test
    public void findLastOrderNumberPositiveResult() throws DaoException {
        String orderType = "P";
        String lastOrderNumber = orderDao.findLastOrderNumber(orderType);
        assertThat(lastOrderNumber)
                .isNotNull()
                .doesNotContain("S", "P")
                .matches(Pattern.compile("\\d+"));
    }

    @Test(expected = DaoException.class)
    public void findLastOrderNumberWithException() throws DaoException {
        String orderType = "R";
        orderDao.findLastOrderNumber(orderType);
    }

    @Test
    public void create() throws DaoException {
        boolean isOrderCreated = orderDao.create(testOrder);
        assertThat(isOrderCreated)
                .isTrue();
        List<Order> createdOrder = orderDao.findByParams(new LinkedHashMap<>(Map.of("o.order_number LIKE ?", "%P0%")));
        assertThat(createdOrder)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .element(0)
                .hasFieldOrPropertyWithValue("orderNumber", "P0");
        orderDao.delete(createdOrder.get(0));
    }

    @Test
    public void update() throws DaoException {
        orderDao.create(testOrder);
        Order createdOrder = orderDao.findByParams(new LinkedHashMap<>(Map.of("o.order_number LIKE ?", "%P0%"))).get(0);
        createdOrder.setCompany(new Company(2, "Panasonic", false));
        createdOrder.setModel("testModel");
        createdOrder.setSerialNumber("testSerial");
        Optional<Order> oldEmployee = orderDao.update(createdOrder);
        assertThat(oldEmployee)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("company", new Company(0, null, false))
                .hasFieldOrPropertyWithValue("model", null)
                .hasFieldOrPropertyWithValue("serialNumber", null)
                .extracting("company")
                .hasFieldOrPropertyWithValue("isContract", false);
        Optional<Order> updatedOrder = orderDao.findById(createdOrder.getId());
        assertThat(updatedOrder)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("company", new Company(2, "Panasonic", true))
                .hasFieldOrPropertyWithValue("model", "testModel")
                .hasFieldOrPropertyWithValue("serialNumber", "testSerial")
                .extracting("company")
                .hasFieldOrPropertyWithValue("isContract", true);
        orderDao.deleteById(createdOrder.getId());
    }

    @Test
    public void delete() throws DaoException {
        orderDao.create(testOrder);
        Order createdOrder = orderDao.findByParams(new LinkedHashMap<>(Map.of("o.order_number LIKE ?", "%P0%"))).get(0);
        boolean isDeleted = orderDao.delete(createdOrder);
        assertThat(isDeleted)
                .isTrue();
    }

    @Test
    public void deleteById() throws DaoException {
        orderDao.create(testOrder);
        Order createdOrder = orderDao.findByParams(new LinkedHashMap<>(Map.of("o.order_number LIKE ?", "%P0%"))).get(0);
        boolean isDeleted = orderDao.deleteById(createdOrder.getId());
        assertThat(isDeleted)
                .isTrue();
    }
}

package by.epam.jwdsc.service;

import by.epam.jwdsc.dao.impl.OrderDaoImpl;
import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.entity.dto.OrderData;
import by.epam.jwdsc.entity.dto.OrderParameters;
import by.epam.jwdsc.entity.dto.OrdersWithPagination;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Order service test.
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private OrderDaoImpl orderDao;
    @InjectMocks
    private OrderServiceImpl orderService = new OrderServiceImpl();
    private Order testOrder;

    /**
     * Sets up.
     *
     * @throws DaoException the dao exception
     */
    @Before
    public void setUp() throws DaoException {
        testOrder = new Order.Builder(0, "P0", LocalDateTime.now(), null, null, null)
                .orderStatus(OrderStatus.ACCEPTED)
                .build();
    }

    /**
     * Find order by id positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findOrderByIdPositiveResult() throws ServiceException, DaoException {
        when(orderDao.findById(5L)).thenReturn(Optional.ofNullable(testOrder));
        Optional<Order> foundOrder = orderService.findOrderById(5L);
        assertThat(foundOrder)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("orderNumber", "P0")
                .hasFieldOrPropertyWithValue("client", null);
    }

    /**
     * Find order by id negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findOrderByIdNegativeResult() throws ServiceException, DaoException {
        when(orderDao.findById(-5L)).thenReturn(Optional.empty());
        Optional<Order> foundOrder = orderService.findOrderById(-5L);
        assertThat(foundOrder)
                .isEmpty();
    }

    /**
     * Find order by order number positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findOrderByOrderNumberPositiveResult() throws ServiceException, DaoException {
        when(orderDao.findByParams(new LinkedHashMap<>(Map.of("o.order_number =? ", "P2")))).thenReturn(new ArrayList<>(List.of(testOrder)));
        Optional<Order> foundOrder = orderService.findOrderByOrderNumber("P2");
        assertThat(foundOrder)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("orderNumber", "P0")
                .hasFieldOrPropertyWithValue("client", null);
    }

    /**
     * Find order by order number negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findOrderByOrderNumberNegativeResult() throws ServiceException, DaoException {
        when(orderDao.findByParams(new LinkedHashMap<>(Map.of("o.order_number =? ", "A2")))).thenReturn(new ArrayList<>());
        Optional<Order> foundOrder = orderService.findOrderByOrderNumber("A2");
        assertThat(foundOrder)
                .isEmpty();
    }

    /**
     * Find orders by client email positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findOrdersByClientEmailPositiveResult() throws ServiceException, DaoException {
        when(orderDao.findByParams(new LinkedHashMap<>(Map.of("u.email =? ", "test@test.com")))).thenReturn(new ArrayList<>(List.of(testOrder)));
        List<Order> foundOrder = orderService.findOrdersByClientEmail("test@test.com");
        assertThat(foundOrder)
                .isNotNull()
                .isNotEmpty()
                .element(0)
                .hasFieldOrPropertyWithValue("orderNumber", "P0")
                .hasFieldOrPropertyWithValue("client", null);
    }

    /**
     * Find orders by client email negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findOrdersByClientEmailNegativeResult() throws ServiceException, DaoException {
        when(orderDao.findByParams(new LinkedHashMap<>(Map.of("u.email =? ", "test.com")))).thenReturn(new ArrayList<>());
        List<Order> foundOrder = orderService.findOrdersByClientEmail("test.com");
        assertThat(foundOrder)
                .isNotNull()
                .isEmpty();
    }

    /**
     * Find orders by parameters positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findOrdersByParametersPositiveResult() throws ServiceException, DaoException {
        OrderParameters orderParameters = new OrderParameters();
        orderParameters.setCreationDate("10");
        orderParameters.setSortByName("o.order_number");
        orderParameters.setPageNumber("1");
        when(orderDao.countOrdersByParams(any())).thenReturn(100L);
        when(orderDao.findByParamsWithSortAndPage(any(), anyString(), eq(1))).thenReturn(new ArrayList<>(Collections.nCopies(10, testOrder)));
        when(orderDao.findByParamsWithSortAndPage(any(), any(), eq(2))).thenReturn(new ArrayList<>(Collections.nCopies(5, testOrder)));
        OrdersWithPagination foundResult = orderService.findOrdersByParameters(orderParameters);
        assertThat(foundResult)
                .isNotNull()
                .hasFieldOrPropertyWithValue("totalOrdersQuantity", 100L)
                .hasFieldOrPropertyWithValue("currentPage", 1L)
                .extracting("orders")
                .isNotNull();
        assertThat(foundResult.getOrders())
                .isNotEmpty()
                .hasSize(10)
                .element(0)
                .isNotNull()
                .hasFieldOrPropertyWithValue("orderNumber", "P0")
                .hasFieldOrPropertyWithValue("client", null);
        orderParameters.setPageNumber("2");
        OrdersWithPagination foundResultSecond = orderService.findOrdersByParameters(orderParameters);
        assertThat(foundResultSecond)
                .isNotNull()
                .hasFieldOrPropertyWithValue("totalOrdersQuantity", 100L)
                .hasFieldOrPropertyWithValue("currentPage", 2L)
                .extracting("orders")
                .isNotNull();
        assertThat(foundResultSecond.getOrders())
                .isNotEmpty()
                .hasSize(5)
                .element(0)
                .isNotNull()
                .hasFieldOrPropertyWithValue("orderNumber", "P0")
                .hasFieldOrPropertyWithValue("client", null);
    }

    /**
     * Find orders by parameters negative result.
     *
     * @throws ServiceException the service exception
     */
    @Test(expected = ServiceException.class)
    public void findOrdersByParametersNegativeResult() throws ServiceException {
        OrderParameters orderParameters = new OrderParameters();
        orderParameters.setPageNumber("s");
        orderService.findOrdersByParameters(orderParameters);
    }

    /**
     * Create new order positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void createNewOrderPositiveResult() throws ServiceException, DaoException {
        NewOrderData newOrderData = new NewOrderData();
        newOrderData.setOrderNumber("P");
        newOrderData.setClientId("2");
        newOrderData.setDeviceId("1");
        newOrderData.setCompanyId("1");
        when(orderDao.create(any())).thenReturn(true);
        when(orderDao.findLastOrderNumber(any())).thenReturn("100");
        boolean isCreated = orderService.createNewOrder(newOrderData, 1L);
        assertThat(isCreated)
                .isTrue();

    }

    /**
     * Create new order negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test(expected = ServiceException.class)
    public void createNewOrderNegativeResult() throws ServiceException, DaoException {
        NewOrderData newOrderData = new NewOrderData();
        when(orderDao.create(any())).thenReturn(true);
        when(orderDao.findLastOrderNumber(any())).thenReturn("100");
        orderService.createNewOrder(newOrderData, 1L);
    }

    /**
     * Update order positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void updateOrderPositiveResult() throws ServiceException, DaoException {
        OrderData orderData = new OrderData();
        orderData.setId("1");
        orderData.setOrderNumber("P");
        orderData.setAcceptedEmployeeId("1");
        orderData.setCreationDate("02 06 2022 12:12:10");
        orderData.setClientId("2");
        orderData.setDeviceId("1");
        orderData.setCompanyId("1");
        orderData.setOrderStatus(OrderStatus.ACCEPTED.name());
        when(orderDao.update(any())).thenReturn(Optional.of(testOrder));
        Optional<Order> oldOlder = orderService.updateOrder(orderData, 1L);
        assertThat(oldOlder)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("orderNumber", "P0")
                .hasFieldOrPropertyWithValue("client", null);

    }

    /**
     * Update order negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test(expected = ServiceException.class)
    public void updateOrderNegativeResult() throws ServiceException, DaoException {
        OrderData orderData = new OrderData();
        when(orderDao.update(any())).thenReturn(Optional.of(testOrder));
        orderService.updateOrder(orderData, 1L);
    }

    /**
     * Update positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void updatePositiveResult() throws ServiceException, DaoException {
        testOrder.setOrderStatus(OrderStatus.ACCEPTED);
        when(orderDao.update(testOrder)).thenReturn(Optional.of(testOrder));
        Optional<Order> oldOlder = orderService.updateOrder(testOrder);
        assertThat(oldOlder)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("orderNumber", "P0")
                .hasFieldOrPropertyWithValue("client", null);
    }

    /**
     * Update negative result.
     *
     * @throws ServiceException the service exception
     */
    @Test
    public void updateNegativeResult() throws ServiceException {
        Optional<Order> oldOlder = orderService.updateOrder(testOrder);
        assertThat(oldOlder)
                .isEmpty();
    }

    /**
     * Remove order by id positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void removeOrderByIdPositiveResult() throws ServiceException, DaoException {
        when(orderDao.deleteById(1L)).thenReturn(true);
        when(orderDao.deleteById(0)).thenReturn(false);
        boolean isDeleted = orderService.removeOrderById(1L);
        assertThat(isDeleted)
                .isTrue();
        isDeleted = orderService.removeOrderById(0);
        assertThat(isDeleted)
                .isFalse();
    }
}

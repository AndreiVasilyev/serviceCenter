package by.epam.jwdsc.service;

import by.epam.jwdsc.dao.impl.AddressDaoImpl;
import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.entity.dto.OrderData;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.impl.AddressServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * The type Address service test.
 */
@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {

    @Mock
    private AddressDaoImpl addressDao;
    @InjectMocks
    private AddressServiceImpl addressService = new AddressServiceImpl();
    private Address testAddress;

    /**
     * Sets up.
     *
     * @throws DaoException the dao exception
     */
    @Before
    public void setUp() throws DaoException {
        testAddress = new Address.Builder("testCity", "testStreet", 1111)
                .state("testState")
                .region("testRegion")
                .country("testCountry")
                .postcode(111111)
                .apartmentNumber(1111)
                .build();
    }

    /**
     * Find addresses by params positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findAddressesByParamsPositiveResult() throws ServiceException, DaoException {
        NewOrderData newOrderData = new NewOrderData();
        newOrderData.setClientId("5");
        newOrderData.setHouseNumber("1");
        newOrderData.setPostcode("111111");
        newOrderData.setApartmentNumber("1");
        when(addressDao.findByAllParams(any())).thenReturn(new ArrayList<>(Collections.nCopies(5, testAddress)));
        List<Address> foundAddresses = addressService.findAddressesByParams(newOrderData);
        assertThat(foundAddresses)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .element(0)
                .hasFieldOrPropertyWithValue("region", "testRegion")
                .hasFieldOrPropertyWithValue("postcode", 111111);
    }

    /**
     * Find addresses by params negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findAddressesByParamsNegativeResult() throws ServiceException, DaoException {
        NewOrderData newOrderData = new NewOrderData();
        newOrderData.setClientId("5");
        newOrderData.setHouseNumber("1");
        newOrderData.setPostcode("111111");
        newOrderData.setApartmentNumber("1");
        when(addressDao.findByAllParams(any())).thenReturn(new ArrayList<>());
        List<Address> foundAddresses = addressService.findAddressesByParams(newOrderData);
        assertThat(foundAddresses)
                .isNotNull()
                .isEmpty();
    }

    /**
     * Create address positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void createAddressPositiveResult() throws ServiceException, DaoException {
        NewOrderData newOrderData = new NewOrderData();
        newOrderData.setClientId("5");
        newOrderData.setHouseNumber("1");
        newOrderData.setPostcode("111111");
        newOrderData.setApartmentNumber("1");
        when(addressDao.createAddress(any())).thenReturn(5L);
        long createdAddressId = addressService.createAddress(newOrderData);
        assertThat(createdAddressId)
                .isNotZero()
                .isPositive()
                .isEqualTo(5L);
    }

    /**
     * Create address negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test(expected = ServiceException.class)
    public void createAddressNegativeResult() throws ServiceException, DaoException {
        NewOrderData newOrderData = new NewOrderData();
        newOrderData.setClientId("5");
        newOrderData.setHouseNumber("1");
        newOrderData.setPostcode("111111");
        newOrderData.setApartmentNumber("1");
        when(addressDao.findByAllParams(any())).thenThrow(DaoException.class);
        addressService.findAddressesByParams(newOrderData);
    }

    /**
     * Find by id positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findByIdPositiveResult() throws ServiceException, DaoException {
        when(addressDao.findById(5L)).thenReturn(Optional.of(testAddress));
        Optional<Address> foundAddress = addressService.findById(5L);
        assertThat(foundAddress)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("region", "testRegion")
                .hasFieldOrPropertyWithValue("postcode", 111111);
    }

    /**
     * Find by id negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void findByIdNegativeResult() throws ServiceException, DaoException {
        when(addressDao.findById(-5L)).thenReturn(Optional.empty());
        Optional<Address> foundAddress = addressService.findById(-5L);
        assertThat(foundAddress)
                .isEmpty();
    }

    /**
     * Update address positive result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void updateAddressPositiveResult() throws ServiceException, DaoException {
        OrderData orderData = new OrderData();
        orderData.setClientId("5");
        orderData.setHouseNumber("1");
        orderData.setPostcode("111111");
        orderData.setApartmentNumber("1");
        orderData.setAddressId("1");
        when(addressDao.update(any())).thenReturn(Optional.ofNullable(testAddress));
        Optional<Address> oldAddress = addressService.updateAddress(orderData);
        assertThat(oldAddress)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("region", "testRegion")
                .hasFieldOrPropertyWithValue("postcode", 111111);
    }

    /**
     * Update address negative result.
     *
     * @throws ServiceException the service exception
     * @throws DaoException     the dao exception
     */
    @Test
    public void updateAddressNegativeResult() throws ServiceException, DaoException {
        OrderData orderData = new OrderData();
        orderData.setClientId("5");
        orderData.setHouseNumber("1");
        orderData.setPostcode("111111");
        orderData.setApartmentNumber("1");
        orderData.setAddressId("1");
        when(addressDao.update(any())).thenReturn(Optional.empty());
        Optional<Address> oldAddress = addressService.updateAddress(orderData);
        assertThat(oldAddress)
                .isEmpty();
    }

}

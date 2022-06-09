package by.epam.jwdsc.service;

import by.epam.jwdsc.dao.impl.ClientDaoImpl;
import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.entity.dto.OrderData;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.impl.ClientServiceImpl;
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

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    private ClientDaoImpl clientDao;
    @InjectMocks
    private ClientServiceImpl clientService = new ClientServiceImpl();
    private Client testClient;
    private Address testAddress;

    @Before
    public void setUp() throws DaoException {
        testAddress = new Address.Builder("testCity", "testStreet", 1111)
                .state("testState")
                .region("testRegion")
                .country("testCountry")
                .postcode(111111)
                .apartmentNumber(1111)
                .build();
        testClient = UserBuilders.newClient()
                .firstName("testFirstName")
                .userRole(UserRole.CLIENT)
                .secondName("testSecondName")
                .patronymic("testPatronymicName")
                .address(testAddress)
                .email("testEmail")
                .discount(1)
                .phones(List.of("375111111111", "375222222222"))
                .build();
    }

    @Test
    public void findClientsByPhonePositiveResult() throws ServiceException, DaoException {
        when(clientDao.findByPhone("375111111111")).thenReturn(new ArrayList<>(Collections.nCopies(5, testClient)));
        List<Client> foundClients = clientService.findClientsByPhone("375111111111");
        assertThat(foundClients)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .element(0)
                .hasFieldOrPropertyWithValue("secondName", "testSecondName")
                .hasFieldOrPropertyWithValue("discount", 1);
    }

    @Test
    public void findClientsByPhoneNegativeResult() throws ServiceException, DaoException {
        when(clientDao.findByPhone("-375111111111")).thenReturn(new ArrayList<>());
        List<Client> foundClients = clientService.findClientsByPhone("-375111111111");
        assertThat(foundClients)
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void findClientByIdPositiveResult() throws ServiceException, DaoException {
        when(clientDao.findById(5L)).thenReturn(Optional.ofNullable(testClient));
        Optional<Client> foundClient = clientService.findClientById(5L);
        assertThat(foundClient)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("secondName", "testSecondName")
                .hasFieldOrPropertyWithValue("discount", 1);
    }

    @Test
    public void findClientByIdNegativeResult() throws ServiceException, DaoException {
        when(clientDao.findById(-5L)).thenReturn(Optional.empty());
        Optional<Client> foundClient = clientService.findClientById(-5L);
        assertThat(foundClient)
                .isEmpty();
    }

    @Test
    public void createClientPositiveResult() throws ServiceException, DaoException {
        when(clientDao.createClient(any())).thenReturn(5L);
        long isCreated = clientService.createClient(new NewOrderData(), testAddress, new ArrayList<>());
        assertThat(isCreated)
                .isPositive()
                .isNotZero()
                .isEqualTo(5L);
    }

    @Test(expected = ServiceException.class)
    public void createClientNegativeResult() throws ServiceException, DaoException {
        when(clientDao.createClient(any())).thenThrow(DaoException.class);
        clientService.createClient(new NewOrderData(), testAddress, new ArrayList<>());
    }

    @Test
    public void updateNewClientPositiveResult() throws ServiceException, DaoException {
        NewOrderData newOrderData = new NewOrderData();
        newOrderData.setClientId("5");
        newOrderData.setHouseNumber("1");
        newOrderData.setPostcode("111111");
        newOrderData.setApartmentNumber("1");
        when(clientDao.findById(5L)).thenReturn(Optional.ofNullable(testClient));
        when(clientDao.update(any())).thenReturn(Optional.ofNullable(testClient));
        Optional<Client> updatedClient = clientService.updateClient(newOrderData, new ArrayList<>());
        assertThat(updatedClient)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("secondName", "testSecondName")
                .hasFieldOrPropertyWithValue("discount", 1);
    }

    @Test(expected = ServiceException.class)
    public void updateNewClientNegativeResult() throws ServiceException, DaoException {
        NewOrderData newOrderData = new NewOrderData();
        newOrderData.setClientId("5");
        newOrderData.setHouseNumber("1");
        newOrderData.setPostcode("111111");
        newOrderData.setApartmentNumber("1");
        when(clientDao.findById(5L)).thenReturn(Optional.ofNullable(testClient));
        when(clientDao.update(any())).thenThrow(DaoException.class);
        clientService.updateClient(newOrderData, new ArrayList<>());
    }

    @Test
    public void updateClientPositiveResult() throws ServiceException, DaoException {
        OrderData orderData = new OrderData();
        orderData.setClientId("5");
        orderData.setHouseNumber("1");
        orderData.setPostcode("111111");
        orderData.setApartmentNumber("1");
        orderData.setAddressId("1");
        when(clientDao.update(any())).thenReturn(Optional.ofNullable(testClient));
        Optional<Client> updatedClient = clientService.updateClient(orderData, new ArrayList<>());
        assertThat(updatedClient)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("secondName", "testSecondName")
                .hasFieldOrPropertyWithValue("discount", 1);
    }

    @Test(expected = ServiceException.class)
    public void updateClientNegativeResult() throws ServiceException, DaoException {
        OrderData orderData = new OrderData();
        orderData.setClientId("5");
        orderData.setHouseNumber("1");
        orderData.setPostcode("111111");
        orderData.setApartmentNumber("1");
        orderData.setAddressId("1");
        when(clientDao.update(any())).thenThrow(DaoException.class);
        clientService.updateClient(orderData, new ArrayList<>());
    }

}

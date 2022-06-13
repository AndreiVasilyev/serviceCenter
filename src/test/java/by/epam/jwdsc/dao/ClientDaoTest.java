package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.exception.DaoException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * The type Client dao test.
 */
public class ClientDaoTest {

    private ClientDao clientDao;
    private Client testClient;
    private Address testAddress;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        clientDao = DaoProvider.getInstance().getClientDao();
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

    /**
     * Find all.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findAll() throws DaoException {
        List<Client> foundClients = clientDao.findAll();
        assertThat(foundClients)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(10);
    }

    /**
     * Find by phone positive result.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findByPhonePositiveResult() throws DaoException {
        List<Client> foundClients = clientDao.findByPhone("+375171111121");
        assertThat(foundClients)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1)
                .element(0)
                .matches(client -> client.getPhones().contains("+375171111121"));
    }

    /**
     * Find by phone negative result.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findByPhoneNegativeResult() throws DaoException {
        List<Client> foundClients = clientDao.findByPhone("+375000000000");
        assertThat(foundClients)
                .isNotNull()
                .isEmpty();
    }

    /**
     * Find by id positive result.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findByIdPositiveResult() throws DaoException {
        Optional<Client> foundClient = clientDao.findById(2);
        assertThat(foundClient)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("id", 2L);
    }

    /**
     * Find by id negative result.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findByIdNegativeResult() throws DaoException {
        Optional<Client> foundClient = clientDao.findById(0);
        assertThat(foundClient)
                .isEmpty();
    }

    /**
     * Create with new address.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void createWithNewAddress() throws DaoException {
        boolean isCreatedClient = clientDao.createWithNewAddress(testClient);
        assertThat(isCreatedClient)
                .isTrue();
        List<Client> createdClient = clientDao.findByPhone(testClient.getPhones().get(0));
        assertThat(createdClient)
                .isNotNull()
                .isNotEmpty();
        assertThat(createdClient.get(0))
                .hasFieldOrPropertyWithValue("firstName", "testFirstName");
        clientDao.delete(createdClient.get(0));
    }

    /**
     * Create.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void create() throws DaoException {
        AddressDao addressDao = DaoProvider.getInstance().getAddressDao();
        addressDao.create(testAddress);
        Address createdAddress = addressDao.findByAllParams(testAddress).get(0);
        testClient.setAddress(createdAddress);
        boolean isCreatedClient = clientDao.create(testClient);
        assertThat(isCreatedClient)
                .isTrue();
        List<Client> createdClient = clientDao.findByPhone(testClient.getPhones().get(0));
        assertThat(createdClient)
                .isNotNull()
                .isNotEmpty();
        assertThat(createdClient.get(0))
                .hasFieldOrPropertyWithValue("firstName", "testFirstName")
                .hasFieldOrPropertyWithValue("address", createdAddress);
        clientDao.delete(createdClient.get(0));
    }

    /**
     * Create client.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void createClient() throws DaoException {
        AddressDao addressDao = DaoProvider.getInstance().getAddressDao();
        addressDao.create(testAddress);
        Address createdAddress = addressDao.findByAllParams(testAddress).get(0);
        testClient.setAddress(createdAddress);
        long createdClientId = clientDao.createClient(testClient);
        assertThat(createdClientId)
                .isNotZero()
                .isPositive();
        Optional<Client> createdClient = clientDao.findById(createdClientId);
        assertThat(createdClient)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("firstName", "testFirstName")
                .hasFieldOrPropertyWithValue("address", createdAddress);
        clientDao.delete(createdClient.get());
    }

    /**
     * Update.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void update() throws DaoException {
        AddressDao addressDao = DaoProvider.getInstance().getAddressDao();
        addressDao.create(testAddress);
        Address createdAddress = addressDao.findByAllParams(testAddress).get(0);
        testClient.setAddress(createdAddress);
        long createdClientId = clientDao.createClient(testClient);
        Optional<Client> createdClient = clientDao.findById(createdClientId);
        createdClient.get().setEmail("updatedEmail");
        createdClient.get().getAddress().setCity("updatedCity");
        Optional<Client> oldClient = clientDao.update(createdClient.get());
        assertThat(oldClient)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("email", "testEmail")
                .hasFieldOrPropertyWithValue("id", createdClientId)
                .extracting("address")
                .hasFieldOrPropertyWithValue("city", "testCity");
        Optional<Client> updatedClient = clientDao.findById(createdClientId);
        assertThat(updatedClient)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("email", "updatedEmail")
                .hasFieldOrPropertyWithValue("id", createdClientId)
                .extracting("address")
                .hasFieldOrPropertyWithValue("city", "updatedCity");
        clientDao.deleteById(createdClientId);
    }

    /**
     * Delete.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void delete() throws DaoException {
        clientDao.createWithNewAddress(testClient);
        Client createdClient = clientDao.findByPhone(testClient.getPhones().get(0)).get(0);
        boolean isDeleted = clientDao.delete(createdClient);
        assertThat(isDeleted)
                .isTrue();
    }

    /**
     * Delete by id.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void deleteById() throws DaoException {
        clientDao.createWithNewAddress(testClient);
        Client createdClient = clientDao.findByPhone(testClient.getPhones().get(0)).get(0);
        boolean isDeleted = clientDao.deleteById(createdClient.getId());
        assertThat(isDeleted)
                .isTrue();
    }

}
package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.exception.DaoException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

/**
 * The type Address dao test.
 */
public class AddressDaoTest {

    private AddressDao addressDao;
    private Address testAddress;


    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        addressDao = DaoProvider.getInstance().getAddressDao();
        testAddress = new Address.Builder("testCity", "testStreet", 1111)
                .state("testState")
                .region("testRegion")
                .country("testCountry")
                .postcode(111111)
                .apartmentNumber(1111)
                .build();
    }

    /**
     * Find by id positive result.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findById_positiveResult() throws DaoException {
        Optional<Address> foundAddress = addressDao.findById(2);
        assertThat(foundAddress).isPresent()
                .get()
                .hasFieldOrProperty("city")
                .hasFieldOrPropertyWithValue("city", "Минск");
    }

    /**
     * Find by id negative result.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findById_negativeResult() throws DaoException {
        Optional<Address> foundAddress = addressDao.findById(-2);
        assertThat(foundAddress).isEmpty();
    }

    /**
     * Find all.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findAll() throws DaoException {
        List<Address> foundAddresses = addressDao.findAll();
        assertThat(foundAddresses)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(19);
    }

    /**
     * Find by params.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void findByParams() throws DaoException {
        Address addressTemplate = new Address.Builder("Минск", "пр.Джержинского", 112)
                .country("Беларусь")
                .postcode(220006)
                .apartmentNumber(43)
                .build();
        List<Address> foundAddresses = addressDao.findByAllParams(addressTemplate);
        assertThat(foundAddresses)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        assertThat(foundAddresses.get(0))
                .hasFieldOrPropertyWithValue("id", 2L);
    }

    /**
     * Create address.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void createAddress() throws DaoException {
        long createdAddressId = addressDao.createAddress(testAddress);
        assertThat(createdAddressId)
                .isNotZero()
                .isPositive();
        addressDao.deleteById(createdAddressId);
    }

    /**
     * Create.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void create() throws DaoException {
        boolean isCreatedAddress = addressDao.create(testAddress);
        assertThat(isCreatedAddress)
                .isTrue();
        Address createdAddress = addressDao.findByAllParams(testAddress).get(0);
        addressDao.deleteById(createdAddress.getId());
    }

    /**
     * Update.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void update() throws DaoException {
        long createdAddressId = addressDao.createAddress(testAddress);
        testAddress.setId(createdAddressId);
        testAddress.setStreet("updatedStreet");
        Optional<Address> oldAddress = addressDao.update(testAddress);
        Optional<Address> newAddress = addressDao.findById(createdAddressId);
        assertThat(oldAddress)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("street", "testStreet");
        assertThat(newAddress)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("street", "updatedStreet");
        addressDao.deleteById(testAddress.getId());
    }

    /**
     * Delete.
     *
     * @throws DaoException the dao exception
     */
    @Test
    public void delete() throws DaoException {
        long createdAddressId = addressDao.createAddress(testAddress);
        testAddress.setId(createdAddressId);
        boolean isDeleted = addressDao.delete(testAddress);
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
        long createdAddressId = addressDao.createAddress(testAddress);
        testAddress.setId(createdAddressId);
        boolean isDeleted = addressDao.deleteById(testAddress.getId());
        assertThat(isDeleted)
                .isTrue();
    }
}
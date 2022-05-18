package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.exception.DaoException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class AddressDaoTest {

    private AddressDao addressDao;
    private Address testAddress;


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

    @Test
    public void findById_positiveResult() throws DaoException {
        Optional<Address> foundAddress = addressDao.findById(2);
        assertThat(foundAddress).isPresent()
                .get()
                .hasFieldOrProperty("city")
                .hasFieldOrPropertyWithValue("city", "Минск");
    }

    @Test
    public void findById_negativeResult() throws DaoException {
        Optional<Address> foundAddress = addressDao.findById(-2);
        assertThat(foundAddress).isEmpty();
    }

    @Test
    public void findAll() throws DaoException {
        List<Address> foundAddresses = addressDao.findAll();
        assertThat(foundAddresses)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(19);
    }

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

    @Test
    public void createAddress() throws DaoException {
        long createdAddressId = addressDao.createAddress(testAddress);
        assertThat(createdAddressId)
                .isNotZero()
                .isPositive();
        addressDao.deleteById(createdAddressId);
    }

    @Test
    public void create() throws DaoException {
        boolean isCreatedAddress = addressDao.create(testAddress);
        assertThat(isCreatedAddress)
                .isTrue();
        Address createdAddress = addressDao.findByAllParams(testAddress).get(0);
        addressDao.deleteById(createdAddress.getId());
    }

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

    @Test
    public void delete() throws DaoException {
        long createdAddressId = addressDao.createAddress(testAddress);
        testAddress.setId(createdAddressId);
        boolean isDeleted = addressDao.delete(testAddress);
        assertThat(isDeleted)
                .isTrue();
    }

    @Test
    public void deleteById() throws DaoException {
        long createdAddressId = addressDao.createAddress(testAddress);
        testAddress.setId(createdAddressId);
        boolean isDeleted = addressDao.deleteById(testAddress.getId());
        assertThat(isDeleted)
                .isTrue();
    }
}
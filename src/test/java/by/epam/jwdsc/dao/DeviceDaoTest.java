package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.*;
import by.epam.jwdsc.exception.DaoException;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class DeviceDaoTest {

    private DeviceDao deviceDao;
    private Device testDevice;

    @Before
    public void setUp() {
        deviceDao = DaoProvider.getInstance().getDeviceDao();
        testDevice = new Device("testDevice");
    }

    @Test
    public void findAll() throws DaoException {
        List<Device> foundDevices = deviceDao.findAll();
        assertThat(foundDevices)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(10);
    }

    @Test
    public void findByIdPositiveResult() throws DaoException {
        Optional<Device> foundDevice = deviceDao.findById(1);
        assertThat(foundDevice)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("name", "Смартфон");
    }

    @Test
    public void findByIdNegativeResult() throws DaoException {
        Optional<Device> foundDevice = deviceDao.findById(0);
        assertThat(foundDevice)
                .isEmpty();
    }

    @Test
    public void findByParametersWithSortPositiveResult() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("d.device_name LIKE ? ", "%о%");
        String sortParam = "d.device_name";
        List<Device> foundDevices = deviceDao.findByParametersWithSort(parameters, "");
        List<Device> foundDevicesWithSort = deviceDao.findByParametersWithSort(parameters, sortParam);
        assertThat(foundDevices)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1);
        assertThat(foundDevicesWithSort)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1)
                .hasSize(foundDevices.size())
                .isSortedAccordingTo(Comparator.comparing(Device::getName));
        sortParam = "d.device_name DESC";
        foundDevicesWithSort = deviceDao.findByParametersWithSort(parameters, sortParam);
        assertThat(foundDevicesWithSort)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(1)
                .hasSize(foundDevices.size())
                .containsAll(foundDevicesWithSort)
                .isSortedAccordingTo((o1, o2) -> o2.getName().compareTo(o1.getName()));
    }

    @Test(expected = DaoException.class)
    public void findByParametersWithSortWithException() throws DaoException {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("d.device", "f");
        String sortParam = "d.device_name";
        deviceDao.findByParametersWithSort(parameters, sortParam);
    }

    @Test
    public void create() throws DaoException {
        boolean isCreatedDevice = deviceDao.create(testDevice);
        assertThat(isCreatedDevice)
                .isTrue();
        List<Device> createdDevice = deviceDao.findByParametersWithSort(new LinkedHashMap<>(Map.of("d.device_name LIKE ? ", "testDevice")), "");
        assertThat(createdDevice)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1)
                .element(0)
                .hasFieldOrPropertyWithValue("name", "testDevice");
        deviceDao.delete(createdDevice.get(0));
    }

    @Test
    public void createDevice() throws DaoException {
        long createdDeviceId = deviceDao.createDevice(testDevice);
        assertThat(createdDeviceId)
                .isNotZero()
                .isPositive();
        Optional<Device> createdDevice = deviceDao.findById(createdDeviceId);
        assertThat(createdDevice)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("name", "testDevice");
        deviceDao.delete(createdDevice.get());
    }

    @Test
    public void update() throws DaoException {
        long createdDeviceId = deviceDao.createDevice(testDevice);
        Device createdDevice = deviceDao.findById(createdDeviceId).get();
        createdDevice.setName("updatedName");
        Optional<Device> oldDevice = deviceDao.update(createdDevice);
        assertThat(oldDevice)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("name", "testDevice");
        Optional<Device> updatedDevice = deviceDao.findById(createdDeviceId);
        assertThat(updatedDevice)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("name", "updatedName");
        deviceDao.deleteById(createdDeviceId);
    }

    @Test
    public void delete() throws DaoException {
        long createdDeviceId = deviceDao.createDevice(testDevice);
        Device createdDevice = deviceDao.findById(createdDeviceId).get();
        boolean isDeleted = deviceDao.delete(createdDevice);
        assertThat(isDeleted)
                .isTrue();
    }

    @Test
    public void deleteById() throws DaoException {
        long createdDeviceId = deviceDao.createDevice(testDevice);
        boolean isDeleted = deviceDao.deleteById(createdDeviceId);
        assertThat(isDeleted)
                .isTrue();
    }
}

package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.PriceInfo;
import by.epam.jwdsc.entity.RepairLevel;
import by.epam.jwdsc.exception.DaoException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceInfoDaoTest {
    private PriceInfoDao priceInfoDao;
    private PriceInfo testPriceInfo;


    @Before
    public void setUp() {
        priceInfoDao = DaoProvider.getInstance().getPriceInfoDao();
        testPriceInfo = new PriceInfo(5, RepairLevel.REPAIR_LEVEL_3, new BigDecimal("80"));
    }

    @Test
    public void findById_positiveResult() throws DaoException {
        Optional<PriceInfo> foundPriceInfo = priceInfoDao.findById(2L);
        assertThat(foundPriceInfo).isPresent()
                .get()
                .hasFieldOrPropertyWithValue("device", 2L);
    }

    @Test
    public void findById_negativeResult() throws DaoException {
        Optional<PriceInfo> foundPriceInfo = priceInfoDao.findById(40);
        assertThat(foundPriceInfo).isEmpty();
    }

    @Test
    public void findAll() throws DaoException {
        List<PriceInfo> foundPricesInfo = priceInfoDao.findAll();
        assertThat(foundPricesInfo)
                .isNotNull()
                .isNotEmpty()
                .hasSizeGreaterThanOrEqualTo(10);
    }

    @Test
    public void findByDeviceAndLevelPositiveResult() throws DaoException {
        long deviceId = 1L;
        String repairLevel = "REPAIR_LEVEL_1";
        Optional<PriceInfo> foundPriceInfo = priceInfoDao.findByDeviceAndLevel(deviceId, repairLevel);
        assertThat(foundPriceInfo)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("repairCost", new BigDecimal("120.00"));
    }

    @Test
    public void findByDeviceAndLevelNegativeResult() throws DaoException {
        long deviceId = 1L;
        String repairLevel = "";
        Optional<PriceInfo> foundPriceInfo = priceInfoDao.findByDeviceAndLevel(deviceId, repairLevel);
        assertThat(foundPriceInfo)
                .isEmpty();
    }

    @Test
    public void findCostByDeviceAndLevelPositiveResult() throws DaoException {
        long deviceId = 1L;
        String repairLevel = "REPAIR_LEVEL_1";
        Optional<BigDecimal> foundCost = priceInfoDao.findCostByDeviceAndLevel(deviceId, repairLevel);
        assertThat(foundCost)
                .isPresent()
                .get()
                .isNotNull()
                .isEqualTo(new BigDecimal("120.00"));
    }

    @Test
    public void findCostByDeviceAndLevelNegativeResult() throws DaoException {
        long deviceId = 1L;
        String repairLevel = "";
        Optional<BigDecimal> foundCost = priceInfoDao.findCostByDeviceAndLevel(deviceId, repairLevel);
        assertThat(foundCost)
                .isEmpty();
    }

    @Test
    public void findCostsByDevicePositiveResult() throws DaoException {
        long deviceId = 1L;
        List<PriceInfo> foundPricesInfo = priceInfoDao.findCostsByDevice(deviceId);
        assertThat(foundPricesInfo)
                .isNotNull()
                .isNotEmpty()
                .hasSize(6)
                .element(0)
                .isNotNull()
                .hasFieldOrPropertyWithValue("device", 1L);
    }

    @Test
    public void findCostsByDeviceNegativeResult() throws DaoException {
        long deviceId = 0L;
        List<PriceInfo> foundPricesInfo = priceInfoDao.findCostsByDevice(deviceId);
        assertThat(foundPricesInfo)
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void create() throws DaoException {
        boolean isCreatedPricesInfo = priceInfoDao.create(testPriceInfo);
        assertThat(isCreatedPricesInfo)
                .isTrue();
        Optional<PriceInfo> createdPricesInfo = priceInfoDao.findByDeviceAndLevel(testPriceInfo.getDevice(), testPriceInfo.getRepairLevel().name());
        priceInfoDao.delete(createdPricesInfo.get());
    }

    @Test
    public void update() throws DaoException {
        priceInfoDao.create(testPriceInfo);
        PriceInfo createdPriceInfo = priceInfoDao.findByDeviceAndLevel(testPriceInfo.getDevice(), testPriceInfo.getRepairLevel().name()).get();
        createdPriceInfo.setRepairCost(new BigDecimal("100.00"));
        Optional<PriceInfo> oldPriceInfo = priceInfoDao.update(createdPriceInfo);
        PriceInfo newPriceInfo = priceInfoDao.findByDeviceAndLevel(testPriceInfo.getDevice(), testPriceInfo.getRepairLevel().name()).get();
        assertThat(oldPriceInfo)
                .isPresent()
                .get()
                .hasFieldOrPropertyWithValue("repairCost", new BigDecimal("80.00"));
        assertThat(newPriceInfo)
                .isNotNull()
                .hasFieldOrPropertyWithValue("repairCost", new BigDecimal("100.00"))
                .isEqualTo(createdPriceInfo);
        priceInfoDao.delete(newPriceInfo);
    }

    @Test
    public void delete() throws DaoException {
        priceInfoDao.create(testPriceInfo);
        PriceInfo createdPriceInfo = priceInfoDao.findByDeviceAndLevel(testPriceInfo.getDevice(), testPriceInfo.getRepairLevel().name()).get();
        boolean isDeleted = priceInfoDao.delete(createdPriceInfo);
        assertThat(isDeleted)
                .isTrue();
    }

    @Test
    public void deleteById() throws DaoException {
        priceInfoDao.create(testPriceInfo);
        PriceInfo createdPriceInfo = priceInfoDao.findByDeviceAndLevel(testPriceInfo.getDevice(), testPriceInfo.getRepairLevel().name()).get();
        boolean isDeleted = priceInfoDao.deleteById(createdPriceInfo.getId());
        assertThat(isDeleted)
                .isTrue();
    }
}

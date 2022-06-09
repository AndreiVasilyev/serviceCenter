package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.CompanyDao;
import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.dao.PriceInfoDao;
import by.epam.jwdsc.entity.PriceInfo;
import by.epam.jwdsc.entity.RepairLevel;
import by.epam.jwdsc.entity.dto.PricesData;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.PriceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PriceServiceImpl implements PriceService {

    private static final Logger log = LogManager.getLogger();
    private PriceInfoDao priceInfoDao;

    public PriceServiceImpl() {
        this.priceInfoDao = DaoProvider.getInstance().getPriceInfoDao();
    }

    public PriceServiceImpl(PriceInfoDao priceInfoDao) {
        this.priceInfoDao = priceInfoDao;
    }

    @Override
    public Optional<BigDecimal> findCostByDeviceAndLevel(long deviceId, String repairLevel) throws ServiceException {
        try {
            return priceInfoDao.findCostByDeviceAndLevel(deviceId, repairLevel);
        } catch (DaoException e) {
            log.error("Error executing service find work cost by device id and repair level");
            throw new ServiceException("Error executing service find work cost by device id and repair level", e);
        }
    }

    @Override
    public List<PriceInfo> findPricesByDevice(long deviceId) throws ServiceException {
        try {
            return priceInfoDao.findCostsByDevice(deviceId);
        } catch (DaoException e) {
            log.error("Error executing service find work costs by device id");
            throw new ServiceException("Error executing service find work costs by device id", e);
        }
    }

    @Override
    public boolean savePricesByDevice(PricesData pricesData) throws ServiceException {
        long deviceId = Long.parseLong(pricesData.getDevice());
        try {
            String diagnosticCost = pricesData.getDiagnostic();
            savePriceByDevice(deviceId, RepairLevel.DIAGNOSTIC, diagnosticCost);
            String maintenanceCost = pricesData.getMaintenance();
            savePriceByDevice(deviceId, RepairLevel.MAINTENANCE, maintenanceCost);
            String repairLevel1Cost = pricesData.getRepairLevel1();
            savePriceByDevice(deviceId, RepairLevel.REPAIR_LEVEL_1, repairLevel1Cost);
            String repairLevel2Cost = pricesData.getRepairLevel2();
            savePriceByDevice(deviceId, RepairLevel.REPAIR_LEVEL_2, repairLevel2Cost);
            String repairLevel3Cost = pricesData.getRepairLevel3();
            savePriceByDevice(deviceId, RepairLevel.REPAIR_LEVEL_3, repairLevel3Cost);
            String technicalConclusionCost = pricesData.getTechnicalConclusion();
            savePriceByDevice(deviceId, RepairLevel.TECHNICAL_CONCLUSION, technicalConclusionCost);
            return true;
        } catch (DaoException e) {
            log.error("Error executing service save prices by device id");
            throw new ServiceException("Error executing service save prices by device id", e);
        }
    }

    private void savePriceByDevice(long deviceId, RepairLevel repairLevel, String repairCost) throws DaoException {
        BigDecimal currentCost = new BigDecimal(repairCost);
        Optional<PriceInfo> savedPriceInfo = priceInfoDao.findByDeviceAndLevel(deviceId, repairLevel.name());
        if (savedPriceInfo.isPresent()) {
            if (!savedPriceInfo.get().getRepairCost().equals(currentCost)) {
                savedPriceInfo.get().setRepairCost(currentCost);
                priceInfoDao.update(savedPriceInfo.get());
            }
        } else {
            PriceInfo priceInfo = new PriceInfo(deviceId, repairLevel, currentCost);
            priceInfoDao.create(priceInfo);
        }
    }
}

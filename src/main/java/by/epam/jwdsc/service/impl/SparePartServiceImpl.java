package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.*;
import by.epam.jwdsc.entity.SparePart;
import by.epam.jwdsc.entity.dto.SparePartData;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.SparePartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * The type Spare part service.
 */
public class SparePartServiceImpl implements SparePartService {

    private static final Logger log = LogManager.getLogger();
    private SparePartDao sparePartDao;

    /**
     * Instantiates a new Spare part service.
     */
    public SparePartServiceImpl() {
        this.sparePartDao = DaoProvider.getInstance().getSparePartDao();
    }

    /**
     * Instantiates a new Spare part service.
     *
     * @param sparePartDao the spare part dao
     */
    public SparePartServiceImpl(SparePartDao sparePartDao) {
        this.sparePartDao = sparePartDao;
    }

    @Override
    public List<SparePart> findPartsByParam(String param) throws ServiceException {
        try {
            return sparePartDao.findByParam(param);
        } catch (DaoException e) {
            log.error("Error executing service find parts by parameter");
            throw new ServiceException("Error executing service find parts by parameter", e);
        }
    }

    @Override
    public List<SparePart> findPartsByParameters(SparePartData sparePartData) throws ServiceException {
        QueryParametersMapper queryParametersMapper = QueryParametersMapper.getInstance();
        LinkedHashMap<String, Object> parameters = queryParametersMapper.mapSparePartParameters(sparePartData);
        String sort = queryParametersMapper.mapSparePartSort(sparePartData);
        try {
            return sparePartDao.findByParametersWithSort(parameters, sort);
        } catch (DaoException e) {
            log.error("Error executing service find parts by parameters with sort");
            throw new ServiceException("Error executing service find parts by parameters with sort", e);
        }
    }

    @Override
    public boolean addNewPart(SparePartData sparePartData) throws ServiceException {
        BigDecimal partCost = new BigDecimal(sparePartData.getCost());
        SparePart sparePart = new SparePart.Builder(sparePartData.getName(), partCost)
                .partNumber(sparePartData.getPartNumber())
                .description(sparePartData.getDescription())
                .build();
        try {
            return sparePartDao.create(sparePart);
        } catch (DaoException e) {
            log.error("Error executing service add new part");
            throw new ServiceException("Error executing service add new part", e);
        }
    }
}

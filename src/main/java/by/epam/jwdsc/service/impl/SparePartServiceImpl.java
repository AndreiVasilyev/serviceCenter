package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.dao.EmployeeDao;
import by.epam.jwdsc.dao.QueryParametersMapper;
import by.epam.jwdsc.dao.SparePartDao;
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

public class SparePartServiceImpl implements SparePartService {

    private static final Logger log = LogManager.getLogger();

    @Override
    public List<SparePart> findPartsByParam(String param) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        SparePartDao sparePartDao = daoProvider.getSparePartDao();
        try {
            return sparePartDao.findByParam(param);
        } catch (DaoException e) {
            log.error("Error executing service find parts by parameter");
            throw new ServiceException("Error executing service find parts by parameter", e);
        }
    }

    @Override
    public List<SparePart> findPartsByParameters(SparePartData sparePartData) throws ServiceException {
        DaoProvider daoProvider = DaoProvider.getInstance();
        SparePartDao sparePartDao = daoProvider.getSparePartDao();
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
        DaoProvider daoProvider = DaoProvider.getInstance();
        SparePartDao sparePartDao = daoProvider.getSparePartDao();
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

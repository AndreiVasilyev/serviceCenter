package by.epam.jwdsc.service.impl;

import by.epam.jwdsc.dao.DaoProvider;
import by.epam.jwdsc.dao.SparePartDao;
import by.epam.jwdsc.entity.SparePart;
import by.epam.jwdsc.exception.DaoException;
import by.epam.jwdsc.exception.ServiceException;
import by.epam.jwdsc.service.SparePartService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

}

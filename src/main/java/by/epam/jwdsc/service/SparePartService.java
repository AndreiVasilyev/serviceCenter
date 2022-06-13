package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.SparePart;
import by.epam.jwdsc.entity.dto.SparePartData;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;

/**
 * The interface Spare part service.
 */
public interface SparePartService {
    /**
     * Find parts by param list.
     *
     * @param param the param
     * @return the list
     * @throws ServiceException the service exception
     */
    List<SparePart> findPartsByParam(String param) throws ServiceException;

    /**
     * Find parts by parameters list.
     *
     * @param sparePartData the spare part data
     * @return the list
     * @throws ServiceException the service exception
     */
    List<SparePart> findPartsByParameters(SparePartData sparePartData) throws ServiceException;

    /**
     * Add new part boolean.
     *
     * @param sparePartData the spare part data
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addNewPart(SparePartData sparePartData) throws ServiceException;
}

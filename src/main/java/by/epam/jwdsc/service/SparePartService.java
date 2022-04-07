package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.SparePart;
import by.epam.jwdsc.entity.dto.SparePartData;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;

public interface SparePartService {
    List<SparePart> findPartsByParam(String param) throws ServiceException;

    List<SparePart> findPartsByParameters(SparePartData sparePartData) throws ServiceException;

    boolean addNewPart(SparePartData sparePartData) throws ServiceException;
}

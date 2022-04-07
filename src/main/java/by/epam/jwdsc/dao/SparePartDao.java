package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.SparePart;
import by.epam.jwdsc.exception.DaoException;

import java.util.LinkedHashMap;
import java.util.List;

public interface SparePartDao extends BaseDao<SparePart> {
    List<SparePart> findByParam(String param) throws DaoException;

    List<SparePart> findByParametersWithSort(LinkedHashMap<String, Object> parameters, String sort) throws DaoException;
}

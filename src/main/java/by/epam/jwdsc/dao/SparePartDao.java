package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.SparePart;
import by.epam.jwdsc.exception.DaoException;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * The interface Spare part dao.
 */
public interface SparePartDao extends BaseDao<SparePart> {
    /**
     * Find by param list.
     *
     * @param param the param
     * @return the list
     * @throws DaoException the dao exception
     */
    List<SparePart> findByParam(String param) throws DaoException;

    /**
     * Find by parameters with sort list.
     *
     * @param parameters the parameters
     * @param sort       the sort
     * @return the list
     * @throws DaoException the dao exception
     */
    List<SparePart> findByParametersWithSort(LinkedHashMap<String, Object> parameters, String sort) throws DaoException;
}

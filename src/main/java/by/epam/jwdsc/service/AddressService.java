package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.entity.dto.OrderData;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface Address service.
 */
public interface AddressService {

    /**
     * Find addresses by params list.
     *
     * @param newOrderData the new order data
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Address> findAddressesByParams(NewOrderData newOrderData) throws ServiceException;

    /**
     * Create address long.
     *
     * @param newOrderData the new order data
     * @return the long
     * @throws ServiceException the service exception
     */
    long createAddress(NewOrderData newOrderData) throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Address> findById(long id) throws ServiceException;

    /**
     * Update address optional.
     *
     * @param orderData the order data
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Address> updateAddress(OrderData orderData) throws ServiceException;
}

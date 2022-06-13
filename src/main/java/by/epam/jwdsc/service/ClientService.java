package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.entity.Client;
import by.epam.jwdsc.entity.dto.NewOrderData;
import by.epam.jwdsc.entity.dto.OrderData;
import by.epam.jwdsc.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface Client service.
 */
public interface ClientService {

    /**
     * Find clients by phone list.
     *
     * @param phoneNumber the phone number
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Client> findClientsByPhone(String phoneNumber) throws ServiceException;

    /**
     * Find client by id optional.
     *
     * @param userId the user id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Client> findClientById(long userId) throws ServiceException;

    /**
     * Create client long.
     *
     * @param newOrderData the new order data
     * @param addressId    the address id
     * @param phones       the phones
     * @return the long
     * @throws ServiceException the service exception
     */
    long createClient(NewOrderData newOrderData, Address addressId, List<String> phones) throws ServiceException;

    /**
     * Update client optional.
     *
     * @param newOrderData the new order data
     * @param phones       the phones
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Client> updateClient(NewOrderData newOrderData, List<String> phones) throws ServiceException;

    /**
     * Update client optional.
     *
     * @param orderData the order data
     * @param phones    the phones
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Client> updateClient(OrderData orderData, List<String> phones) throws ServiceException;
}

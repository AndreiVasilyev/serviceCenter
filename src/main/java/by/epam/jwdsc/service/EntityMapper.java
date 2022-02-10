package by.epam.jwdsc.service;

import by.epam.jwdsc.entity.Address;
import by.epam.jwdsc.entity.Client;
import by.epam.jwdsc.entity.UserBuilders;
import by.epam.jwdsc.entity.dto.NewOrderData;

import java.util.List;

public class EntityMapper {

    private static EntityMapper instance;

    private EntityMapper() {
    }

    public static EntityMapper getInstance() {
        if (instance == null) {
            instance = new EntityMapper();
        }
        return instance;
    }

    public Client mapClient(NewOrderData newOrderData, Address address, List<String> phones) {
        return UserBuilders.newClient()
                .firstName(newOrderData.getFirstName())
                .secondName(newOrderData.getSecondName())
                .patronymic(newOrderData.getPatronymic())
                .email(newOrderData.getEmail())
                .address(address)
                .phones(phones)
                .build();
    }

    public Address mapAddress(NewOrderData newOrderData) {

        int houseNumber = Integer.parseInt(newOrderData.getHouseNumber());
        Address address = new Address.Builder(newOrderData.getCity(), newOrderData.getStreet(), houseNumber)
                .country(newOrderData.getCountry())
                .state(newOrderData.getState())
                .region(newOrderData.getRegion())
                .build();
        if (!newOrderData.getPostcode().isBlank()) {
            int postcode = Integer.parseInt(newOrderData.getPostcode());
            address.setPostcode(postcode);
        }
        if (!newOrderData.getApartmentNumber().isBlank()) {
            int apartmentNumber = Integer.parseInt(newOrderData.getApartmentNumber());
            address.setApartmentNumber(apartmentNumber);
        }
        return address;
    }
}

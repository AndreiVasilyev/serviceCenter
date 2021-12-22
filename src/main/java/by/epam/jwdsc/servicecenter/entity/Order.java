package by.epam.jwdsc.servicecenter.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    private long id;
    private String orderNumber;
    private LocalDateTime creationDate;
    private Client client;
    private Employee acceptedEmployee;
    private Device device;
    private Company company;
    private String model;
    private String serialNumber;
    private Employee completedEmployee;
    private LocalDateTime comletionDate;
    private String workDescription;
    private String sparePartList;
    private BigDecimal sparePartTotalCost;
    private OrderStatus orderStatus;
}

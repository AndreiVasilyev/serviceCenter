package by.epam.jwdsc.dao;

public final class ColumnName {
    //for table Orders
    public static final String ORDERS_ID = "order_id";
    public static final String ORDERS_ORDER_NUMBER = "order_number";
    public static final String ORDERS_CREATION_DATE = "creation_date";
    public static final String ORDERS_CLIENT = "client";
    public static final String ORDERS_ACCEPTED_EMPLOYEE = "accepted_employee";
    public static final String ORDERS_DEVICE = "device";
    public static final String ORDERS_COMPANY = "company";
    public static final String ORDERS_MODEL = "model";
    public static final String ORDERS_SERIAL_NUMBER = "serial_number";
    public static final String ORDERS_COMPLETED_EMPLOYEE = "completed_employee";
    public static final String ORDERS_COMPLETION_DATE = "completion_date";
    public static final String ORDERS_ISSUE_DATE = "issue_date";
    public static final String ORDERS_WORK_DESCRIPTION = "work_description";
    public static final String ORDERS_WORK_PRICE = "work_price";
    public static final String ORDERS_STATUS = "status";
    public static final String ORDERS_NOTE = "note";
    //for table Users
    public static final String USERS_ID = "user_id";
    public static final String USERS_FIRST_NAME = "first_name";
    public static final String USERS_SECOND_NAME = "second_name";
    public static final String USERS_PATRONYMIC = "patronymic";
    public static final String USERS_ADDRESS = "address";
    public static final String USERS_EMAIL = "email";
    //for table Addresses
    public static final String ADDRESSES_ID = "address_id";
    public static final String ADDRESSES_COUNTRY = "country";
    public static final String ADDRESSES_POSTCODE = "postcode";
    public static final String ADDRESSES_STATE = "state";
    public static final String ADDRESSES_REGION = "region";
    public static final String ADDRESSES_CITY = "city";
    public static final String ADDRESSES_STREET = "street";
    public static final String ADDRESSES_HOUSE_NUMBER = "house_number";
    public static final String ADDRESSES_APARTMENT_NUMBER = "apartment_number";
    //for table Clients
    public static final String CLIENTS_ID = "id";
    public static final String CLIENTS_USER_ID = "user_id";
    public static final String CLIENTS_DISCOUNT = "discount";
    //for table Employees
    public static final String EMPLOYEES_ID = "id";
    public static final String EMPLOYEES_USER_ID = "user_id";
    public static final String EMPLOYEES_LOGIN = "login";
    public static final String EMPLOYEES_PASSWORD = "password";
    public static final String EMPLOYEES_ROLE = "role";
    //for table Companies
    public static final String COMPANIES_ID = "company_id";
    public static final String COMPANIES_NAME = "name";
    public static final String COMPANIES_IS_SERVICE_CONTRACT = "is_service_contract";
    //for table Devices
    public static final String DEVICES_ID = "device_id";
    public static final String DEVICES_NAME = "device_name";
    //for table SpareParts
    public static final String SPARE_PARTS_ID = "id";
    public static final String SPARE_PARTS_PART_NUMBER = "part_number";
    public static final String SPARE_PARTS_NAME = "name";
    public static final String SPARE_PARTS_DESCRIPTION = "description";
    public static final String SPARE_PARTS_COST = "cost";
    //for table Prices
    public static final String PRICES_ID = "id";
    public static final String PRICES_DEVICE_ID = "device_id";
    public static final String PRICES_REPAIR_LEVEL = "repair_level";
    public static final String PRICES_REPAIR_COST = "repair_cost";
    //for table PhoneNumbers
    public static final String PHONE_NUMBERS_ID = "phone_number_id";
    public static final String PHONE_NUMBERS_NUMBER = "phone_number";
    public static final String PHONE_NUMBERS_USER_ID = "user_id";
    //for table OrdersSpareParts
    public static final String ORDERS_SPARE_PARTS_ORDER_ID = "order_id";
    public static final String ORDERS_SPARE_PARTS_PART_ID = "spare_part_id";

    private ColumnName() {
    }
}

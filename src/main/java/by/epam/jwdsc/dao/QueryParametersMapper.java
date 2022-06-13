package by.epam.jwdsc.dao;

import by.epam.jwdsc.entity.dto.*;
import org.apache.logging.log4j.util.Strings;

import java.util.LinkedHashMap;

import static by.epam.jwdsc.dao.ColumnName.*;
import static by.epam.jwdsc.dao.TableAliasName.*;

/**
 * The type Query parameters mapper.
 */
public final class QueryParametersMapper {

    private static QueryParametersMapper instance;
    private static final String ALIAS_SEPARATOR = ".";
    private static final String SORT_SEPARATOR = ",";
    private static final String LIKE_WILDCARD_SYMBOL = "%";
    private static final String CONCAT_TEMPLATE = "CONCAT_WS(' ',u.first_name,u.second_name)";
    private static final String PARAMETER_TEMPLATE = " =? ";
    private static final String LIKE_PARAMETER_TEMPLATE = " LIKE ? ";
    /**
     * The constant EMPLOYEE_TEMPLATE.
     */
    public static final String EMPLOYEE_TEMPLATE = "o.%s IN (SELECT u.user_id FROM users AS u WHERE CONCAT_WS(' ',u.first_name,u.second_name) %s)";
    /**
     * The constant PRICES_TEMPLATE.
     */
    public static final String PRICES_TEMPLATE = "o.work_price IN (SELECT prs.id FROM prices AS prs WHERE prs.%s %s)";
    /**
     * The constant REVERSE_SORT.
     */
    public static final String REVERSE_SORT = " DESC";
    /**
     * The constant MULTI_REVERSE_SORT.
     */
    public static final String MULTI_REVERSE_SORT = " DESC,";

    private QueryParametersMapper() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static QueryParametersMapper getInstance() {
        if (instance == null) {
            instance = new QueryParametersMapper();
        }
        return instance;
    }

    /**
     * Map parameter linked hash map.
     *
     * @param table  the table
     * @param column the column
     * @param value  the value
     * @return the linked hash map
     */
    public LinkedHashMap<String, Object> mapParameter(String table, String column, Object value) {
        String parameterName = Strings.concat(table, ALIAS_SEPARATOR).concat(column).concat(PARAMETER_TEMPLATE);
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put(parameterName, value);
        return parameters;
    }

    /**
     * Map order parameters linked hash map.
     *
     * @param orderParameters the order parameters
     * @return the linked hash map
     */
    public LinkedHashMap<String, Object> mapOrderParameters(OrderParameters orderParameters) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parseLikeParameter(SC_ORDERS, ORDERS_ORDER_NUMBER, orderParameters.getOrderNumber(), parameters);
        parseLikeParameter(SC_ORDERS, ORDERS_STATUS, orderParameters.getOrderStatus(), parameters);
        parseLikeParameter(SC_ORDERS, ORDERS_CREATION_DATE, orderParameters.getCreationDate(), parameters);
        parseClientParameter(orderParameters.getClient(), parameters);
        parseLikeParameter(SC_DEVICES, DEVICES_NAME, orderParameters.getDevice(), parameters);
        parseLikeParameter(SC_COMPANIES, COMPANIES_NAME, orderParameters.getCompany(), parameters);
        parseLikeParameter(SC_ORDERS, ORDERS_MODEL, orderParameters.getModel(), parameters);
        parseLikeParameter(SC_ORDERS, ORDERS_SERIAL_NUMBER, orderParameters.getSerialNumber(), parameters);
        parseEmployeeParameter(ORDERS_ACCEPTED_EMPLOYEE, orderParameters.getAcceptedEmployee(), parameters);
        parseEmployeeParameter(ORDERS_COMPLETED_EMPLOYEE, orderParameters.getCompletedEmployee(), parameters);
        parseLikeParameter(SC_ORDERS, ORDERS_COMPLETION_DATE, orderParameters.getCompletionDate(), parameters);
        parseLikeParameter(SC_ORDERS, ORDERS_ISSUE_DATE, orderParameters.getIssueDate(), parameters);
        parseLikeParameter(SC_ORDERS, ORDERS_WORK_DESCRIPTION, orderParameters.getWorkDescription(), parameters);
        parsePriceParameter(PRICES_REPAIR_LEVEL, orderParameters.getRepairLevel(), parameters);
        parsePriceParameter(PRICES_REPAIR_COST, orderParameters.getRepairCost(), parameters);
        parseLikeParameter(SC_ORDERS, ORDERS_NOTE, orderParameters.getNote(), parameters);
        return parameters;
    }

    /**
     * Map employee parameters linked hash map.
     *
     * @param employeeParameters the employee parameters
     * @return the linked hash map
     */
    public LinkedHashMap<String, Object> mapEmployeeParameters(EmployeeParameters employeeParameters) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parseLikeParameter(SC_USERS, USERS_ID, employeeParameters.getId(), parameters);
        parseLikeParameter(SC_USERS, USERS_ROLE, employeeParameters.getUserRole(), parameters);
        parseLikeParameter(SC_EMPLOYEES, EMPLOYEES_LOGIN, employeeParameters.getLogin(), parameters);
        parseLikeParameter(SC_USERS, USERS_SECOND_NAME, employeeParameters.getSecondName(), parameters);
        parseLikeParameter(SC_USERS, USERS_FIRST_NAME, employeeParameters.getFirstName(), parameters);
        parseLikeParameter(SC_USERS, USERS_PATRONYMIC, employeeParameters.getPatronymic(), parameters);
        parseLikeParameter(SC_USERS, USERS_EMAIL, employeeParameters.getEmail(), parameters);
        parseLikeParameter(SC_ADDRESSES, ADDRESSES_POSTCODE, employeeParameters.getPostcode(), parameters);
        parseLikeParameter(SC_ADDRESSES, ADDRESSES_COUNTRY, employeeParameters.getCountry(), parameters);
        parseLikeParameter(SC_ADDRESSES, ADDRESSES_STATE, employeeParameters.getState(), parameters);
        parseLikeParameter(SC_ADDRESSES, ADDRESSES_REGION, employeeParameters.getRegion(), parameters);
        parseLikeParameter(SC_ADDRESSES, ADDRESSES_CITY, employeeParameters.getCity(), parameters);
        parseLikeParameter(SC_ADDRESSES, ADDRESSES_STREET, employeeParameters.getStreet(), parameters);
        parseLikeParameter(SC_ADDRESSES, ADDRESSES_HOUSE_NUMBER, employeeParameters.getHouseNumber(), parameters);
        parseLikeParameter(SC_ADDRESSES, ADDRESSES_APARTMENT_NUMBER, employeeParameters.getApartmentNumber(), parameters);
        if (employeeParameters.getPhones() != null && !employeeParameters.getPhones().isBlank()) {
            String paramName = Strings.concat(PHONE_NUMBERS_NUMBER, LIKE_PARAMETER_TEMPLATE);
            String paramValue = Strings.concat(LIKE_WILDCARD_SYMBOL, employeeParameters.getPhones()).concat(LIKE_WILDCARD_SYMBOL);
            parameters.put(paramName, paramValue);
        }
        return parameters;
    }

    /**
     * Map spare part parameters linked hash map.
     *
     * @param sparePartData the spare part data
     * @return the linked hash map
     */
    public LinkedHashMap<String, Object> mapSparePartParameters(SparePartData sparePartData) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parseLikeParameter(SC_SPARE_PARTS, SPARE_PARTS_ID, sparePartData.getId(), parameters);
        parseLikeParameter(SC_SPARE_PARTS, SPARE_PARTS_PART_NUMBER, sparePartData.getPartNumber(), parameters);
        parseLikeParameter(SC_SPARE_PARTS, SPARE_PARTS_NAME, sparePartData.getName(), parameters);
        parseLikeParameter(SC_SPARE_PARTS, SPARE_PARTS_DESCRIPTION, sparePartData.getDescription(), parameters);
        parseLikeParameter(SC_SPARE_PARTS, SPARE_PARTS_COST, sparePartData.getCost(), parameters);
        return parameters;
    }

    /**
     * Map device parameters linked hash map.
     *
     * @param deviceData the device data
     * @return the linked hash map
     */
    public LinkedHashMap<String, Object> mapDeviceParameters(DeviceData deviceData) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parseLikeParameter(SC_DEVICES, DEVICES_ID, deviceData.getId(), parameters);
        parseLikeParameter(SC_DEVICES, DEVICES_NAME, deviceData.getName(), parameters);
        return parameters;
    }

    /**
     * Map company parameters linked hash map.
     *
     * @param companyData the company data
     * @return the linked hash map
     */
    public LinkedHashMap<String, Object> mapCompanyParameters(CompanyData companyData) {
        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parseLikeParameter(SC_COMPANIES, COMPANIES_ID, companyData.getId(), parameters);
        parseLikeParameter(SC_COMPANIES, COMPANIES_NAME, companyData.getName(), parameters);
        parseLikeParameter(SC_COMPANIES, COMPANIES_IS_SERVICE_CONTRACT, companyData.getIsContract(), parameters);
        return parameters;
    }

    /**
     * Map order sort string.
     *
     * @param orderParameters the order parameters
     * @return the string
     */
    public String mapOrderSort(OrderParameters orderParameters) {
        String sort = Strings.EMPTY;
        if (orderParameters.getSortByName() != null && !orderParameters.getSortByName().isBlank()) {
            sort = orderParameters.getSortByName();
            if (REVERSE_SORT.trim().equalsIgnoreCase(orderParameters.getSortDirection())) {
                sort = sort.replace(SORT_SEPARATOR, MULTI_REVERSE_SORT).concat(REVERSE_SORT);
            }
        }
        return sort;
    }

    /**
     * Map employee sort string.
     *
     * @param employeeParameters the employee parameters
     * @return the string
     */
    public String mapEmployeeSort(EmployeeParameters employeeParameters) {
        String sort = Strings.EMPTY;
        if (employeeParameters.getSortByName() != null && !employeeParameters.getSortByName().isBlank()) {
            sort = employeeParameters.getSortByName();
            if (REVERSE_SORT.trim().equalsIgnoreCase(employeeParameters.getSortDirection())) {
                sort = sort.replace(SORT_SEPARATOR, MULTI_REVERSE_SORT).concat(REVERSE_SORT);
            }
        }
        return sort;
    }

    /**
     * Map spare part sort string.
     *
     * @param sparePartData the spare part data
     * @return the string
     */
    public String mapSparePartSort(SparePartData sparePartData) {
        String sort = Strings.EMPTY;
        if (sparePartData.getSortByName() != null && !sparePartData.getSortByName().isBlank()) {
            sort = sparePartData.getSortByName();
            if (REVERSE_SORT.trim().equalsIgnoreCase(sparePartData.getSortDirection())) {
                sort = sort.replace(SORT_SEPARATOR, MULTI_REVERSE_SORT).concat(REVERSE_SORT);
            }
        }
        return sort;
    }

    /**
     * Map device sort string.
     *
     * @param deviceData the device data
     * @return the string
     */
    public String mapDeviceSort(DeviceData deviceData) {
        String sort = Strings.EMPTY;
        if (deviceData.getSortByName() != null && !deviceData.getSortByName().isBlank()) {
            sort = deviceData.getSortByName();
            if (REVERSE_SORT.trim().equalsIgnoreCase(deviceData.getSortDirection())) {
                sort = sort.replace(SORT_SEPARATOR, MULTI_REVERSE_SORT).concat(REVERSE_SORT);
            }
        }
        return sort;
    }

    /**
     * Map company sort string.
     *
     * @param companyData the company data
     * @return the string
     */
    public String mapCompanySort(CompanyData companyData) {
        String sort = Strings.EMPTY;
        if (companyData.getSortByName() != null && !companyData.getSortByName().isBlank()) {
            sort = companyData.getSortByName();
            if (REVERSE_SORT.trim().equalsIgnoreCase(companyData.getSortDirection())) {
                sort = sort.replace(SORT_SEPARATOR, MULTI_REVERSE_SORT).concat(REVERSE_SORT);
            }
        }
        return sort;
    }

    private void parsePriceParameter(String columnName, String value, LinkedHashMap<String, Object> parameters) {
        if (value != null && !value.isBlank()) {
            String paramName = String.format(PRICES_TEMPLATE, columnName, LIKE_PARAMETER_TEMPLATE);
            String paramValue = Strings.concat(LIKE_WILDCARD_SYMBOL, value).concat(LIKE_WILDCARD_SYMBOL);
            parameters.put(paramName, paramValue);
        }
    }

    private void parseClientParameter(String value, LinkedHashMap<String, Object> parameters) {
        if (value != null && !value.isBlank()) {
            String paramName = Strings.concat(CONCAT_TEMPLATE, LIKE_PARAMETER_TEMPLATE);
            String paramValue = Strings.concat(LIKE_WILDCARD_SYMBOL, value).concat(LIKE_WILDCARD_SYMBOL);
            parameters.put(paramName, paramValue);
        }
    }

    private void parseEmployeeParameter(String columnName, String value, LinkedHashMap<String, Object> parameters) {
        if (value != null && !value.isBlank()) {
            String paramName = String.format(EMPLOYEE_TEMPLATE, columnName, LIKE_PARAMETER_TEMPLATE);
            String paramValue = Strings.concat(LIKE_WILDCARD_SYMBOL, value).concat(LIKE_WILDCARD_SYMBOL);
            parameters.put(paramName, paramValue);
        }
    }

    private void parseLikeParameter(String tableName, String columnName, String value, LinkedHashMap<String, Object> parameters) {
        if (value != null && !value.isBlank()) {
            String paramName = Strings.concat(tableName, ALIAS_SEPARATOR).concat(columnName).concat(LIKE_PARAMETER_TEMPLATE);
            String paramValue = Strings.concat(LIKE_WILDCARD_SYMBOL, value).concat(LIKE_WILDCARD_SYMBOL);
            parameters.put(paramName, paramValue);
        }
    }
}

package by.epam.jwdsc.entity.dto;

public class NewOrderData {

    private String orderNumber;
    private String deviceName;
    private String deviceId;
    private String companyName;
    private String companyId;
    private String model;
    private String serial;
    private String clientId;
    private String email;
    private String firstName;
    private String secondName;
    private String patronymic;
    private String phoneFirst;
    private String phoneSecond;
    private String phoneThird;
    private String country;
    private String postcode;
    private String state;
    private String region;
    private String city;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String note;

    public NewOrderData() {
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoneFirst() {
        return phoneFirst;
    }

    public void setPhoneFirst(String phoneFirst) {
        this.phoneFirst = phoneFirst;
    }

    public String getPhoneSecond() {
        return phoneSecond;
    }

    public void setPhoneSecond(String phoneSecond) {
        this.phoneSecond = phoneSecond;
    }

    public String getPhoneThird() {
        return phoneThird;
    }

    public void setPhoneThird(String phoneThird) {
        this.phoneThird = phoneThird;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewOrderData that = (NewOrderData) o;

        if (orderNumber != null ? !orderNumber.equals(that.orderNumber) : that.orderNumber != null) return false;
        if (deviceName != null ? !deviceName.equals(that.deviceName) : that.deviceName != null) return false;
        if (deviceId != null ? !deviceId.equals(that.deviceId) : that.deviceId != null) return false;
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (serial != null ? !serial.equals(that.serial) : that.serial != null) return false;
        if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;
        if (phoneFirst != null ? !phoneFirst.equals(that.phoneFirst) : that.phoneFirst != null) return false;
        if (phoneSecond != null ? !phoneSecond.equals(that.phoneSecond) : that.phoneSecond != null) return false;
        if (phoneThird != null ? !phoneThird.equals(that.phoneThird) : that.phoneThird != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (postcode != null ? !postcode.equals(that.postcode) : that.postcode != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (houseNumber != null ? !houseNumber.equals(that.houseNumber) : that.houseNumber != null) return false;
        if (apartmentNumber != null ? !apartmentNumber.equals(that.apartmentNumber) : that.apartmentNumber != null)
            return false;
        return note != null ? note.equals(that.note) : that.note == null;
    }

    @Override
    public int hashCode() {
        int result = orderNumber != null ? orderNumber.hashCode() : 0;
        result = 31 * result + (deviceName != null ? deviceName.hashCode() : 0);
        result = 31 * result + (deviceId != null ? deviceId.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (companyId != null ? companyId.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (serial != null ? serial.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (phoneFirst != null ? phoneFirst.hashCode() : 0);
        result = 31 * result + (phoneSecond != null ? phoneSecond.hashCode() : 0);
        result = 31 * result + (phoneThird != null ? phoneThird.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (apartmentNumber != null ? apartmentNumber.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NewOrderData{");
        sb.append("orderNumber='").append(orderNumber).append('\'');
        sb.append(", deviceName='").append(deviceName).append('\'');
        sb.append(", deviceId='").append(deviceId).append('\'');
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append(", companyId='").append(companyId).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", serial='").append(serial).append('\'');
        sb.append(", clientId='").append(clientId).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", phoneFirst='").append(phoneFirst).append('\'');
        sb.append(", phoneSecond='").append(phoneSecond).append('\'');
        sb.append(", phoneThird='").append(phoneThird).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", postcode='").append(postcode).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", houseNumber='").append(houseNumber).append('\'');
        sb.append(", apartmentNumber='").append(apartmentNumber).append('\'');
        sb.append(", note='").append(note).append('\'');
        sb.append('}');
        return sb.toString();
    }


}

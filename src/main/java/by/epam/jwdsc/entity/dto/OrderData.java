package by.epam.jwdsc.entity.dto;

public class OrderData {
    private String id;
    private String orderNumber;
    private String orderStatus;
    private String creationDate;
    private String acceptedEmployeeId;
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
    private String addressId;
    private String postcode;
    private String state;
    private String region;
    private String city;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String completedEmployeeId;
    private String completionDate;
    private String issueDate;
    private String workPriceId;
    private String repairLevel;
    private String workDescription;
    private String spareParts;
    private String note;

    public OrderData() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getAcceptedEmployeeId() {
        return acceptedEmployeeId;
    }

    public void setAcceptedEmployeeId(String acceptedEmployeeId) {
        this.acceptedEmployeeId = acceptedEmployeeId;
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

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
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

    public String getCompletedEmployeeId() {
        return completedEmployeeId;
    }

    public void setCompletedEmployeeId(String completedEmployeeId) {
        this.completedEmployeeId = completedEmployeeId;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getWorkPriceId() {
        return workPriceId;
    }

    public void setWorkPriceId(String workPriceId) {
        this.workPriceId = workPriceId;
    }

    public String getRepairLevel() {
        return repairLevel;
    }

    public void setRepairLevel(String repairLevel) {
        this.repairLevel = repairLevel;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public String getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(String spareParts) {
        this.spareParts = spareParts;
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

        OrderData orderData = (OrderData) o;

        if (id != null ? !id.equals(orderData.id) : orderData.id != null) return false;
        if (orderNumber != null ? !orderNumber.equals(orderData.orderNumber) : orderData.orderNumber != null)
            return false;
        if (orderStatus != null ? !orderStatus.equals(orderData.orderStatus) : orderData.orderStatus != null)
            return false;
        if (creationDate != null ? !creationDate.equals(orderData.creationDate) : orderData.creationDate != null)
            return false;
        if (acceptedEmployeeId != null ? !acceptedEmployeeId.equals(orderData.acceptedEmployeeId) : orderData.acceptedEmployeeId != null)
            return false;
        if (deviceName != null ? !deviceName.equals(orderData.deviceName) : orderData.deviceName != null) return false;
        if (deviceId != null ? !deviceId.equals(orderData.deviceId) : orderData.deviceId != null) return false;
        if (companyName != null ? !companyName.equals(orderData.companyName) : orderData.companyName != null)
            return false;
        if (companyId != null ? !companyId.equals(orderData.companyId) : orderData.companyId != null) return false;
        if (model != null ? !model.equals(orderData.model) : orderData.model != null) return false;
        if (serial != null ? !serial.equals(orderData.serial) : orderData.serial != null) return false;
        if (clientId != null ? !clientId.equals(orderData.clientId) : orderData.clientId != null) return false;
        if (email != null ? !email.equals(orderData.email) : orderData.email != null) return false;
        if (firstName != null ? !firstName.equals(orderData.firstName) : orderData.firstName != null) return false;
        if (secondName != null ? !secondName.equals(orderData.secondName) : orderData.secondName != null) return false;
        if (patronymic != null ? !patronymic.equals(orderData.patronymic) : orderData.patronymic != null) return false;
        if (phoneFirst != null ? !phoneFirst.equals(orderData.phoneFirst) : orderData.phoneFirst != null) return false;
        if (phoneSecond != null ? !phoneSecond.equals(orderData.phoneSecond) : orderData.phoneSecond != null)
            return false;
        if (phoneThird != null ? !phoneThird.equals(orderData.phoneThird) : orderData.phoneThird != null) return false;
        if (country != null ? !country.equals(orderData.country) : orderData.country != null) return false;
        if (addressId != null ? !addressId.equals(orderData.addressId) : orderData.addressId != null) return false;
        if (postcode != null ? !postcode.equals(orderData.postcode) : orderData.postcode != null) return false;
        if (state != null ? !state.equals(orderData.state) : orderData.state != null) return false;
        if (region != null ? !region.equals(orderData.region) : orderData.region != null) return false;
        if (city != null ? !city.equals(orderData.city) : orderData.city != null) return false;
        if (street != null ? !street.equals(orderData.street) : orderData.street != null) return false;
        if (houseNumber != null ? !houseNumber.equals(orderData.houseNumber) : orderData.houseNumber != null)
            return false;
        if (apartmentNumber != null ? !apartmentNumber.equals(orderData.apartmentNumber) : orderData.apartmentNumber != null)
            return false;
        if (completedEmployeeId != null ? !completedEmployeeId.equals(orderData.completedEmployeeId) : orderData.completedEmployeeId != null)
            return false;
        if (completionDate != null ? !completionDate.equals(orderData.completionDate) : orderData.completionDate != null)
            return false;
        if (issueDate != null ? !issueDate.equals(orderData.issueDate) : orderData.issueDate != null) return false;
        if (workPriceId != null ? !workPriceId.equals(orderData.workPriceId) : orderData.workPriceId != null)
            return false;
        if (repairLevel != null ? !repairLevel.equals(orderData.repairLevel) : orderData.repairLevel != null)
            return false;
        if (workDescription != null ? !workDescription.equals(orderData.workDescription) : orderData.workDescription != null)
            return false;
        if (spareParts != null ? !spareParts.equals(orderData.spareParts) : orderData.spareParts != null) return false;
        return note != null ? note.equals(orderData.note) : orderData.note == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (acceptedEmployeeId != null ? acceptedEmployeeId.hashCode() : 0);
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
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (apartmentNumber != null ? apartmentNumber.hashCode() : 0);
        result = 31 * result + (completedEmployeeId != null ? completedEmployeeId.hashCode() : 0);
        result = 31 * result + (completionDate != null ? completionDate.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        result = 31 * result + (workPriceId != null ? workPriceId.hashCode() : 0);
        result = 31 * result + (repairLevel != null ? repairLevel.hashCode() : 0);
        result = 31 * result + (workDescription != null ? workDescription.hashCode() : 0);
        result = 31 * result + (spareParts != null ? spareParts.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderData{");
        sb.append("id='").append(id).append('\'');
        sb.append(", orderNumber='").append(orderNumber).append('\'');
        sb.append(", orderStatus='").append(orderStatus).append('\'');
        sb.append(", creationDate='").append(creationDate).append('\'');
        sb.append(", acceptedEmployeeId='").append(acceptedEmployeeId).append('\'');
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
        sb.append(", addressId='").append(addressId).append('\'');
        sb.append(", postcode='").append(postcode).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", houseNumber='").append(houseNumber).append('\'');
        sb.append(", apartmentNumber='").append(apartmentNumber).append('\'');
        sb.append(", completedEmployeeId='").append(completedEmployeeId).append('\'');
        sb.append(", completionDate='").append(completionDate).append('\'');
        sb.append(", issueDate='").append(issueDate).append('\'');
        sb.append(", workPriceId='").append(workPriceId).append('\'');
        sb.append(", repairLevel='").append(repairLevel).append('\'');
        sb.append(", workDescription='").append(workDescription).append('\'');
        sb.append(", spareParts='").append(spareParts).append('\'');
        sb.append(", note='").append(note).append('\'');
        sb.append('}');
        return sb.toString();
    }


}

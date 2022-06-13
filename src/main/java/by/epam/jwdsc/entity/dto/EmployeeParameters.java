package by.epam.jwdsc.entity.dto;

/**
 * The type Employee parameters.
 */
public class EmployeeParameters {
    private String id;
    private String userRole;
    private String login;
    private String firstName;
    private String secondName;
    private String patronymic;
    private String email;
    private String postcode;
    private String country;
    private String state;
    private String region;
    private String city;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String phones;
    private String sortByName;
    private String sortDirection;

    /**
     * Instantiates a new Employee parameters.
     */
    public EmployeeParameters() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets user role.
     *
     * @return the user role
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * Sets user role.
     *
     * @param userRole the user role
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets second name.
     *
     * @return the second name
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     * Sets second name.
     *
     * @param secondName the second name
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * Gets patronymic.
     *
     * @return the patronymic
     */
    public String getPatronymic() {
        return patronymic;
    }

    /**
     * Sets patronymic.
     *
     * @param patronymic the patronymic
     */
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets postcode.
     *
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets postcode.
     *
     * @param postcode the postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets region.
     *
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets region.
     *
     * @param region the region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets house number.
     *
     * @return the house number
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets house number.
     *
     * @param houseNumber the house number
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Gets apartment number.
     *
     * @return the apartment number
     */
    public String getApartmentNumber() {
        return apartmentNumber;
    }

    /**
     * Sets apartment number.
     *
     * @param apartmentNumber the apartment number
     */
    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    /**
     * Gets phones.
     *
     * @return the phones
     */
    public String getPhones() {
        return phones;
    }

    /**
     * Sets phones.
     *
     * @param phones the phones
     */
    public void setPhones(String phones) {
        this.phones = phones;
    }

    /**
     * Gets sort by name.
     *
     * @return the sort by name
     */
    public String getSortByName() {
        return sortByName;
    }

    /**
     * Sets sort by name.
     *
     * @param sortByName the sort by name
     */
    public void setSortByName(String sortByName) {
        this.sortByName = sortByName;
    }

    /**
     * Gets sort direction.
     *
     * @return the sort direction
     */
    public String getSortDirection() {
        return sortDirection;
    }

    /**
     * Sets sort direction.
     *
     * @param sortDirection the sort direction
     */
    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeParameters that = (EmployeeParameters) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userRole != null ? !userRole.equals(that.userRole) : that.userRole != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (postcode != null ? !postcode.equals(that.postcode) : that.postcode != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (houseNumber != null ? !houseNumber.equals(that.houseNumber) : that.houseNumber != null) return false;
        if (apartmentNumber != null ? !apartmentNumber.equals(that.apartmentNumber) : that.apartmentNumber != null)
            return false;
        if (phones != null ? !phones.equals(that.phones) : that.phones != null) return false;
        if (sortByName != null ? !sortByName.equals(that.sortByName) : that.sortByName != null) return false;
        return sortDirection != null ? sortDirection.equals(that.sortDirection) : that.sortDirection == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (apartmentNumber != null ? apartmentNumber.hashCode() : 0);
        result = 31 * result + (phones != null ? phones.hashCode() : 0);
        result = 31 * result + (sortByName != null ? sortByName.hashCode() : 0);
        result = 31 * result + (sortDirection != null ? sortDirection.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmployeeParameters{");
        sb.append("id='").append(id).append('\'');
        sb.append(", userRole='").append(userRole).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", secondName='").append(secondName).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", postcode='").append(postcode).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", houseNumber='").append(houseNumber).append('\'');
        sb.append(", apartmentNumber='").append(apartmentNumber).append('\'');
        sb.append(", phones='").append(phones).append('\'');
        sb.append(", sortByName='").append(sortByName).append('\'');
        sb.append(", sortDirection='").append(sortDirection).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

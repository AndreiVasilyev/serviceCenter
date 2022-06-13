package by.epam.jwdsc.entity;

/**
 * The type Address.
 */
public class Address extends CommonEntity {
    private long id;
    private String country;
    private int postcode;
    private String state;
    private String region;
    private String city;
    private String street;
    private int houseNumber;
    private int apartmentNumber;

    private Address(long id, String country, int postcode, String state, String region, String city,
                    String street, int houseNumber, int apartmentNumber) {
        this.id = id;
        this.country = country;
        this.postcode = postcode;
        this.state = state;
        this.region = region;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
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
     * Gets postcode.
     *
     * @return the postcode
     */
    public int getPostcode() {
        return postcode;
    }

    /**
     * Sets postcode.
     *
     * @param postcode the postcode
     */
    public void setPostcode(int postcode) {
        this.postcode = postcode;
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
    public int getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets house number.
     *
     * @param houseNumber the house number
     */
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Gets apartment number.
     *
     * @return the apartment number
     */
    public int getApartmentNumber() {
        return apartmentNumber;
    }

    /**
     * Sets apartment number.
     *
     * @param apartmentNumber the apartment number
     */
    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (id != address.id) return false;
        if (postcode != address.postcode) return false;
        if (houseNumber != address.houseNumber) return false;
        if (apartmentNumber != address.apartmentNumber) return false;
        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        if (state != null ? !state.equals(address.state) : address.state != null) return false;
        if (region != null ? !region.equals(address.region) : address.region != null) return false;
        if (!city.equals(address.city)) return false;
        return street.equals(address.street);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + postcode;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + city.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + houseNumber;
        result = 31 * result + apartmentNumber;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("id=").append(id);
        sb.append(", country='").append(country).append('\'');
        sb.append(", postcode=").append(postcode);
        sb.append(", state='").append(state).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", houseNumber=").append(houseNumber);
        sb.append(", apartmentNumber=").append(apartmentNumber);
        sb.append('}');
        return sb.toString();
    }

    /**
     * The type Builder.
     */
    public static class Builder {

        private long id;
        private String country;
        private int postcode;
        private String state;
        private String region;
        private final String city;
        private final String street;
        private final int houseNumber;
        private int apartmentNumber;

        /**
         * Instantiates a new Builder.
         *
         * @param city        the city
         * @param street      the street
         * @param houseNumber the house number
         */
        public Builder(String city, String street, int houseNumber) {
            this.city = city;
            this.street = street;
            this.houseNumber = houseNumber;
        }

        /**
         * Id builder.
         *
         * @param id the id
         * @return the builder
         */
        public Builder id(long id) {
            this.id = id;
            return this;
        }

        /**
         * Country builder.
         *
         * @param country the country
         * @return the builder
         */
        public Builder country(String country) {
            this.country = country;
            return this;
        }

        /**
         * Postcode builder.
         *
         * @param postcode the postcode
         * @return the builder
         */
        public Builder postcode(int postcode) {
            this.postcode = postcode;
            return this;
        }

        /**
         * State builder.
         *
         * @param state the state
         * @return the builder
         */
        public Builder state(String state) {
            this.state = state;
            return this;
        }

        /**
         * Region builder.
         *
         * @param region the region
         * @return the builder
         */
        public Builder region(String region) {
            this.region = region;
            return this;
        }

        /**
         * Apartment number builder.
         *
         * @param apartmentNumber the apartment number
         * @return the builder
         */
        public Builder apartmentNumber(int apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
            return this;
        }

        /**
         * Build address.
         *
         * @return the address
         */
        public Address build() {
            return new Address(this.id, this.country, this.postcode, this.state, this.region, this.city,
                    this.street, this.houseNumber, this.apartmentNumber);
        }
    }
}

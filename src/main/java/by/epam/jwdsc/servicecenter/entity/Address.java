package by.epam.jwdsc.servicecenter.entity;

public class Address {
    private final long id;
    private final String country;
    private final int postcode;
    private final String state;
    private final String region;
    private final String city;
    private final String street;
    private final int houseNumber;
    private final int apartmentNumber;

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

    public long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getState() {
        return state;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
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

    public static class Builder {

        private final long id;
        private String country;
        private int postcode;
        private String state;
        private String region;
        private final String city;
        private final String street;
        private final int houseNumber;
        private int apartmentNumber;

        public Builder(long id, String city, String street, int houseNumber) {
            this.id = id;
            this.city = city;
            this.street = street;
            this.houseNumber = houseNumber;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder postcode(int postcode) {
            this.postcode = postcode;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }

        public Builder apartmentNumber(int apartmentNumber) {
            this.apartmentNumber = apartmentNumber;
            return this;
        }

        public Address build() {
            return new Address(this.id, this.country, this.postcode, this.state, this.region, this.city,
                    this.street, this.houseNumber, this.apartmentNumber);
        }
    }
}

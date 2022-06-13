package by.epam.jwdsc.entity.dto;

import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.entity.Device;

import java.util.List;

/**
 * The type Companies and devices.
 */
public class CompaniesAndDevices {
    private List<Company> companies;
    private List<Device> devices;

    /**
     * Instantiates a new Companies and devices.
     */
    public CompaniesAndDevices() {

    }

    /**
     * Gets companies.
     *
     * @return the companies
     */
    public List<Company> getCompanies() {
        return companies;
    }

    /**
     * Sets companies.
     *
     * @param companies the companies
     */
    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    /**
     * Gets devices.
     *
     * @return the devices
     */
    public List<Device> getDevices() {
        return devices;
    }

    /**
     * Sets devices.
     *
     * @param devices the devices
     */
    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompaniesAndDevices that = (CompaniesAndDevices) o;

        if (companies != null ? !companies.equals(that.companies) : that.companies != null) return false;
        return devices != null ? devices.equals(that.devices) : that.devices == null;
    }

    @Override
    public int hashCode() {
        int result = companies != null ? companies.hashCode() : 0;
        result = 31 * result + (devices != null ? devices.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompaniesAndDevices{");
        sb.append("companies=").append(companies);
        sb.append(", devices=").append(devices);
        sb.append('}');
        return sb.toString();
    }
}

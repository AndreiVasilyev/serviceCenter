package by.epam.jwdsc.entity.dto;

import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.entity.Device;

import java.util.List;

public class CompaniesAndDevices {
    private List<Company> companies;
    private List<Device> devices;

    public CompaniesAndDevices() {

    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public List<Device> getDevices() {
        return devices;
    }

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

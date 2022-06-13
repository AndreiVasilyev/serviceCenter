package by.epam.jwdsc.entity.dto;

import by.epam.jwdsc.entity.Company;
import by.epam.jwdsc.entity.Device;
import by.epam.jwdsc.entity.Employee;

import java.util.List;

/**
 * The type Selectable items.
 */
public class SelectableItems {

    private List<Company> companies;
    private List<Device> devices;
    private List<Employee> employees;
    private List<String> levels;

    /**
     * Instantiates a new Selectable items.
     */
    public SelectableItems() {

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

    /**
     * Gets employees.
     *
     * @return the employees
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Sets employees.
     *
     * @param employees the employees
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    /**
     * Gets levels.
     *
     * @return the levels
     */
    public List<String> getLevels() {
        return levels;
    }

    /**
     * Sets levels.
     *
     * @param levels the levels
     */
    public void setLevels(List<String> levels) {
        this.levels = levels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SelectableItems that = (SelectableItems) o;

        if (companies != null ? !companies.equals(that.companies) : that.companies != null) return false;
        if (devices != null ? !devices.equals(that.devices) : that.devices != null) return false;
        if (employees != null ? !employees.equals(that.employees) : that.employees != null) return false;
        return levels != null ? levels.equals(that.levels) : that.levels == null;
    }

    @Override
    public int hashCode() {
        int result = companies != null ? companies.hashCode() : 0;
        result = 31 * result + (devices != null ? devices.hashCode() : 0);
        result = 31 * result + (employees != null ? employees.hashCode() : 0);
        result = 31 * result + (levels != null ? levels.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SelectableItems{");
        sb.append("companies=").append(companies);
        sb.append(", devices=").append(devices);
        sb.append(", employees=").append(employees);
        sb.append(", levels=").append(levels);
        sb.append('}');
        return sb.toString();
    }
}

package by.epam.jwdsc.entity.dto;

/**
 * The type Company data.
 */
public class CompanyData {

    private String id;
    private String name;
    private String isContract;
    private String sortByName;
    private String sortDirection;

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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets is contract.
     *
     * @return the is contract
     */
    public String getIsContract() {
        return isContract;
    }

    /**
     * Sets is contract.
     *
     * @param isContract the is contract
     */
    public void setIsContract(String isContract) {
        this.isContract = isContract;
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

        CompanyData that = (CompanyData) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (isContract != null ? !isContract.equals(that.isContract) : that.isContract != null) return false;
        if (sortByName != null ? !sortByName.equals(that.sortByName) : that.sortByName != null) return false;
        return sortDirection != null ? sortDirection.equals(that.sortDirection) : that.sortDirection == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isContract != null ? isContract.hashCode() : 0);
        result = 31 * result + (sortByName != null ? sortByName.hashCode() : 0);
        result = 31 * result + (sortDirection != null ? sortDirection.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompanyData{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", isContract='").append(isContract).append('\'');
        sb.append(", sortByName='").append(sortByName).append('\'');
        sb.append(", sortDirection='").append(sortDirection).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

package by.epam.jwdsc.entity.dto;

/**
 * The type Spare part data.
 */
public class SparePartData {
    private String id;
    private String partNumber;
    private String name;
    private String description;
    private String cost;
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
     * Gets part number.
     *
     * @return the part number
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * Sets part number.
     *
     * @param partNumber the part number
     */
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public String getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(String cost) {
        this.cost = cost;
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

        SparePartData that = (SparePartData) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (partNumber != null ? !partNumber.equals(that.partNumber) : that.partNumber != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;
        if (sortByName != null ? !sortByName.equals(that.sortByName) : that.sortByName != null) return false;
        return sortDirection != null ? sortDirection.equals(that.sortDirection) : that.sortDirection == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (partNumber != null ? partNumber.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (sortByName != null ? sortByName.hashCode() : 0);
        result = 31 * result + (sortDirection != null ? sortDirection.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SparePartData{");
        sb.append("id='").append(id).append('\'');
        sb.append(", partNumber='").append(partNumber).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", cost='").append(cost).append('\'');
        sb.append(", sortByName='").append(sortByName).append('\'');
        sb.append(", sortDirection='").append(sortDirection).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

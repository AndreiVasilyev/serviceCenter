package by.epam.jwdsc.entity.dto;

public class OrderParameters {
    private String orderNumber;
    private String orderStatus;
    private String creationDate;
    private String client;
    private String device;
    private String company;
    private String model;
    private String serialNumber;
    private String acceptedEmployee;
    private String completedEmployee;
    private String completionDate;
    private String issueDate;
    private String workDescription;
    private String repairLevel;
    private String repairCost;
    private String note;
    private String sortByName;
    private String sortDirection;
    private String pageNumber;

    public OrderParameters() {

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

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAcceptedEmployee() {
        return acceptedEmployee;
    }

    public void setAcceptedEmployee(String acceptedEmployee) {
        this.acceptedEmployee = acceptedEmployee;
    }

    public String getCompletedEmployee() {
        return completedEmployee;
    }

    public void setCompletedEmployee(String completedEmployee) {
        this.completedEmployee = completedEmployee;
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

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public String getRepairLevel() {
        return repairLevel;
    }

    public void setRepairLevel(String repairLevel) {
        this.repairLevel = repairLevel;
    }

    public String getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(String repairCost) {
        this.repairCost = repairCost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSortByName() {
        return sortByName;
    }

    public void setSortByName(String sortByName) {
        this.sortByName = sortByName;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderParameters that = (OrderParameters) o;

        if (orderNumber != null ? !orderNumber.equals(that.orderNumber) : that.orderNumber != null) return false;
        if (orderStatus != null ? !orderStatus.equals(that.orderStatus) : that.orderStatus != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (device != null ? !device.equals(that.device) : that.device != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;
        if (serialNumber != null ? !serialNumber.equals(that.serialNumber) : that.serialNumber != null) return false;
        if (acceptedEmployee != null ? !acceptedEmployee.equals(that.acceptedEmployee) : that.acceptedEmployee != null)
            return false;
        if (completedEmployee != null ? !completedEmployee.equals(that.completedEmployee) : that.completedEmployee != null)
            return false;
        if (completionDate != null ? !completionDate.equals(that.completionDate) : that.completionDate != null)
            return false;
        if (issueDate != null ? !issueDate.equals(that.issueDate) : that.issueDate != null) return false;
        if (workDescription != null ? !workDescription.equals(that.workDescription) : that.workDescription != null)
            return false;
        if (repairLevel != null ? !repairLevel.equals(that.repairLevel) : that.repairLevel != null) return false;
        if (repairCost != null ? !repairCost.equals(that.repairCost) : that.repairCost != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (sortByName != null ? !sortByName.equals(that.sortByName) : that.sortByName != null) return false;
        if (sortDirection != null ? !sortDirection.equals(that.sortDirection) : that.sortDirection != null)
            return false;
        return pageNumber != null ? pageNumber.equals(that.pageNumber) : that.pageNumber == null;
    }

    @Override
    public int hashCode() {
        int result = orderNumber != null ? orderNumber.hashCode() : 0;
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (device != null ? device.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (serialNumber != null ? serialNumber.hashCode() : 0);
        result = 31 * result + (acceptedEmployee != null ? acceptedEmployee.hashCode() : 0);
        result = 31 * result + (completedEmployee != null ? completedEmployee.hashCode() : 0);
        result = 31 * result + (completionDate != null ? completionDate.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        result = 31 * result + (workDescription != null ? workDescription.hashCode() : 0);
        result = 31 * result + (repairLevel != null ? repairLevel.hashCode() : 0);
        result = 31 * result + (repairCost != null ? repairCost.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (sortByName != null ? sortByName.hashCode() : 0);
        result = 31 * result + (sortDirection != null ? sortDirection.hashCode() : 0);
        result = 31 * result + (pageNumber != null ? pageNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderParameters{");
        sb.append("orderNumber='").append(orderNumber).append('\'');
        sb.append(", orderStatus='").append(orderStatus).append('\'');
        sb.append(", creationDate='").append(creationDate).append('\'');
        sb.append(", client='").append(client).append('\'');
        sb.append(", device='").append(device).append('\'');
        sb.append(", company='").append(company).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", serialNumber='").append(serialNumber).append('\'');
        sb.append(", acceptedEmployee='").append(acceptedEmployee).append('\'');
        sb.append(", completedEmployee='").append(completedEmployee).append('\'');
        sb.append(", completionDate='").append(completionDate).append('\'');
        sb.append(", issueDate='").append(issueDate).append('\'');
        sb.append(", workDescription='").append(workDescription).append('\'');
        sb.append(", repairLevel='").append(repairLevel).append('\'');
        sb.append(", repairCost='").append(repairCost).append('\'');
        sb.append(", note='").append(note).append('\'');
        sb.append(", sortByName='").append(sortByName).append('\'');
        sb.append(", sortDirection='").append(sortDirection).append('\'');
        sb.append(", pageNumber='").append(pageNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

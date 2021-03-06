package by.epam.jwdsc.entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Order.
 */
public class Order extends CommonEntity {
    private long id;
    private String orderNumber;
    private LocalDateTime creationDate;
    private Client client;
    private Employee acceptedEmployee;
    private Device device;
    private Company company;
    private String model;
    private String serialNumber;
    private Employee completedEmployee;
    private LocalDateTime completionDate;
    private LocalDateTime issueDate;
    private String workDescription;
    private PriceInfo workPrice;
    private List<SparePart> spareParts;
    private OrderStatus orderStatus;
    private String note;


    private Order(long id, String orderNumber, LocalDateTime creationDate, Client client, Employee acceptedEmployee,
                  Device device, Company company, String model, String serialNumber, Employee completedEmployee,
                  LocalDateTime completionDate, LocalDateTime issueDate, String workDescription, PriceInfo workPrice,
                  List<SparePart> spareParts, OrderStatus orderStatus, String note) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.creationDate = creationDate;
        this.client = client;
        this.acceptedEmployee = acceptedEmployee;
        this.device = device;
        this.company = company;
        this.model = model;
        this.serialNumber = serialNumber;
        this.completedEmployee = completedEmployee;
        this.completionDate = completionDate;
        this.issueDate = issueDate;
        this.workDescription = workDescription;
        this.workPrice = workPrice;
        this.spareParts = spareParts;
        this.orderStatus = orderStatus;
        this.note = note;
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
     * Gets order number.
     *
     * @return the order number
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Gets creation date.
     *
     * @return the creation date
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Gets client.
     *
     * @return the client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Gets accepted employee.
     *
     * @return the accepted employee
     */
    public Employee getAcceptedEmployee() {
        return acceptedEmployee;
    }

    /**
     * Gets device.
     *
     * @return the device
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Gets company.
     *
     * @return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * Gets model.
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets serial number.
     *
     * @return the serial number
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Gets completed employee.
     *
     * @return the completed employee
     */
    public Employee getCompletedEmployee() {
        return completedEmployee;
    }

    /**
     * Gets completion date.
     *
     * @return the completion date
     */
    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    /**
     * Gets issue date.
     *
     * @return the issue date
     */
    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    /**
     * Gets work description.
     *
     * @return the work description
     */
    public String getWorkDescription() {
        return workDescription;
    }

    /**
     * Gets work price.
     *
     * @return the work price
     */
    public PriceInfo getWorkPrice() {
        return workPrice;
    }

    /**
     * Gets spare parts.
     *
     * @return the spare parts
     */
    public List<SparePart> getSpareParts() {
        return spareParts;
    }

    /**
     * Add spare part boolean.
     *
     * @param sparePart the spare part
     * @return the boolean
     */
    public boolean addSparePart(SparePart sparePart) {
        return spareParts.add(sparePart);
    }

    /**
     * Gets order status.
     *
     * @return the order status
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Gets note.
     *
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets company.
     *
     * @param company the company
     */
    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * Sets model.
     *
     * @param model the model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Sets serial number.
     *
     * @param serialNumber the serial number
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Sets note.
     *
     * @param note the note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Sets completed employee.
     *
     * @param completedEmployee the completed employee
     */
    public void setCompletedEmployee(Employee completedEmployee) {
        this.completedEmployee = completedEmployee;
    }

    /**
     * Sets completion date.
     *
     * @param completionDate the completion date
     */
    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    /**
     * Sets issue date.
     *
     * @param issueDate the issue date
     */
    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Sets work description.
     *
     * @param workDescription the work description
     */
    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    /**
     * Sets work price.
     *
     * @param workPrice the work price
     */
    public void setWorkPrice(PriceInfo workPrice) {
        this.workPrice = workPrice;
    }

    /**
     * Sets spare parts.
     *
     * @param spareParts the spare parts
     */
    public void setSpareParts(List<SparePart> spareParts) {
        this.spareParts = spareParts;
    }

    /**
     * Sets order status.
     *
     * @param orderStatus the order status
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (!orderNumber.equals(order.orderNumber)) return false;
        if (!creationDate.equals(order.creationDate)) return false;
        if (!client.equals(order.client)) return false;
        if (!acceptedEmployee.equals(order.acceptedEmployee)) return false;
        if (!device.equals(order.device)) return false;
        if (company != null ? !company.equals(order.company) : order.company != null) return false;
        if (model != null ? !model.equals(order.model) : order.model != null) return false;
        if (serialNumber != null ? !serialNumber.equals(order.serialNumber) : order.serialNumber != null) return false;
        if (completedEmployee != null ? !completedEmployee.equals(order.completedEmployee) : order.completedEmployee != null)
            return false;
        if (completionDate != null ? !completionDate.equals(order.completionDate) : order.completionDate != null)
            return false;
        if (issueDate != null ? !issueDate.equals(order.issueDate) : order.issueDate != null) return false;
        if (workDescription != null ? !workDescription.equals(order.workDescription) : order.workDescription != null)
            return false;
        if (workPrice != null ? !workPrice.equals(order.workPrice) : order.workPrice != null) return false;
        if (orderStatus != order.orderStatus) return false;
        return note != null ? note.equals(order.note) : order.note == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + orderNumber.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + client.hashCode();
        result = 31 * result + acceptedEmployee.hashCode();
        result = 31 * result + device.hashCode();
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (serialNumber != null ? serialNumber.hashCode() : 0);
        result = 31 * result + (completedEmployee != null ? completedEmployee.hashCode() : 0);
        result = 31 * result + (completionDate != null ? completionDate.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        result = 31 * result + (workDescription != null ? workDescription.hashCode() : 0);
        result = 31 * result + (workPrice != null ? workPrice.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", orderNumber='").append(orderNumber).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", client=").append(client);
        sb.append(", acceptedEmployee=").append(acceptedEmployee);
        sb.append(", device=").append(device);
        sb.append(", company=").append(company);
        sb.append(", model='").append(model).append('\'');
        sb.append(", serialNumber='").append(serialNumber).append('\'');
        sb.append(", completedEmployee=").append(completedEmployee);
        sb.append(", comletionDate=").append(completionDate);
        sb.append(", issueDate=").append(issueDate);
        sb.append(", workDescription='").append(workDescription).append('\'');
        sb.append(", workPrice=").append(workPrice);
        sb.append(", spareParts=").append(spareParts);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", note='").append(note).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private long id;
        private String orderNumber;
        private LocalDateTime creationDate;
        private Client client;
        private Employee acceptedEmployee;
        private Device device;
        private Company company;
        private String model;
        private String serialNumber;
        private Employee completedEmployee;
        private LocalDateTime completionDate;
        private LocalDateTime issueDate;
        private String workDescription;
        private PriceInfo workPrice;
        private List<SparePart> spareParts;
        private OrderStatus orderStatus;
        private String note;

        /**
         * Instantiates a new Builder.
         *
         * @param id               the id
         * @param orderNumber      the order number
         * @param creationDate     the creation date
         * @param client           the client
         * @param acceptedEmployee the accepted employee
         * @param device           the device
         */
        public Builder(long id, String orderNumber, LocalDateTime creationDate, Client client,
                       Employee acceptedEmployee, Device device) {
            this.id = id;
            this.orderNumber = orderNumber;
            this.creationDate = creationDate;
            this.client = client;
            this.acceptedEmployee = acceptedEmployee;
            this.device = device;
        }

        /**
         * Company builder.
         *
         * @param company the company
         * @return the builder
         */
        public Builder company(Company company) {
            this.company = company;
            return this;
        }

        /**
         * Model builder.
         *
         * @param model the model
         * @return the builder
         */
        public Builder model(String model) {
            this.model = model;
            return this;
        }

        /**
         * Serial number builder.
         *
         * @param serialNumber the serial number
         * @return the builder
         */
        public Builder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        /**
         * Completed employee builder.
         *
         * @param completedEmployee the completed employee
         * @return the builder
         */
        public Builder completedEmployee(Employee completedEmployee) {
            this.completedEmployee = completedEmployee;
            return this;
        }

        /**
         * Completion date builder.
         *
         * @param completionDate the completion date
         * @return the builder
         */
        public Builder completionDate(LocalDateTime completionDate) {
            this.completionDate = completionDate;
            return this;
        }

        /**
         * Issue date builder.
         *
         * @param issueDate the issue date
         * @return the builder
         */
        public Builder issueDate(LocalDateTime issueDate) {
            this.issueDate = issueDate;
            return this;
        }

        /**
         * Work description builder.
         *
         * @param workDescription the work description
         * @return the builder
         */
        public Builder workDescription(String workDescription) {
            this.workDescription = workDescription;
            return this;
        }

        /**
         * Work price builder.
         *
         * @param workPrice the work price
         * @return the builder
         */
        public Builder workPrice(PriceInfo workPrice) {
            this.workPrice = workPrice;
            return this;
        }

        /**
         * Spare parts builder.
         *
         * @param spareParts the spare parts
         * @return the builder
         */
        public Builder spareParts(List<SparePart> spareParts) {
            this.spareParts = spareParts;
            return this;
        }

        /**
         * Order status builder.
         *
         * @param orderStatus the order status
         * @return the builder
         */
        public Builder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        /**
         * Note builder.
         *
         * @param note the note
         * @return the builder
         */
        public Builder note(String note) {
            this.note = note;
            return this;
        }

        /**
         * Build order.
         *
         * @return the order
         */
        public Order build() {
            return new Order(this.id, this.orderNumber, this.creationDate, this.client, this.acceptedEmployee,
                    this.device, this.company, this.model, this.serialNumber, this.completedEmployee,
                    this.completionDate, this.issueDate, this.workDescription, this.workPrice, this.spareParts,
                    this.orderStatus, this.note);
        }
    }

}

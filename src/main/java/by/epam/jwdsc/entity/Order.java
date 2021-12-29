package by.epam.jwdsc.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Order extends CommonEntity {
    private final long id;
    private final String orderNumber;
    private final LocalDateTime creationDate;
    private final Client client;
    private final Employee acceptedEmployee;
    private final Device device;
    private final Company company;
    private final String model;
    private final String serialNumber;
    private final Employee completedEmployee;
    private final LocalDateTime comletionDate;
    private final LocalDateTime issueDate;
    private final String workDescription;
    private final PriceInfo workPrice;
    private final List<SparePart> spareParts;
    private final OrderStatus orderStatus;
    private final String note;


    private Order(long id, String orderNumber, LocalDateTime creationDate, Client client, Employee acceptedEmployee,
                  Device device, Company company, String model, String serialNumber, Employee completedEmployee,
                  LocalDateTime comletionDate, LocalDateTime issueDate, String workDescription, PriceInfo workPrice,
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
        this.comletionDate = comletionDate;
        this.issueDate = issueDate;
        this.workDescription = workDescription;
        this.workPrice = workPrice;
        this.spareParts = spareParts;
        this.orderStatus = orderStatus;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Client getClient() {
        return client;
    }

    public Employee getAcceptedEmployee() {
        return acceptedEmployee;
    }

    public Device getDevice() {
        return device;
    }

    public Company getCompany() {
        return company;
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Employee getCompletedEmployee() {
        return completedEmployee;
    }

    public LocalDateTime getComletionDate() {
        return comletionDate;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public PriceInfo getWorkPrice() {
        return workPrice;
    }

    public List<SparePart> getSpareParts() {
        return spareParts;
    }

    public boolean addSparePart(SparePart sparePart) {
        return spareParts.add(sparePart);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getNote() {
        return note;
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
        if (comletionDate != null ? !comletionDate.equals(order.comletionDate) : order.comletionDate != null)
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
        result = 31 * result + (comletionDate != null ? comletionDate.hashCode() : 0);
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
        sb.append(", comletionDate=").append(comletionDate);
        sb.append(", issueDate=").append(issueDate);
        sb.append(", workDescription='").append(workDescription).append('\'');
        sb.append(", workPrice=").append(workPrice);
        sb.append(", spareParts=").append(spareParts);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", note='").append(note).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private final long id;
        private final String orderNumber;
        private final LocalDateTime creationDate;
        private final Client client;
        private final Employee acceptedEmployee;
        private final Device device;
        private Company company;
        private String model;
        private String serialNumber;
        private Employee completedEmployee;
        private LocalDateTime comletionDate;
        private LocalDateTime issueDate;
        private String workDescription;
        private PriceInfo workPrice;
        private List<SparePart> spareParts;
        private OrderStatus orderStatus;
        private String note;

        public Builder(long id, String orderNumber, LocalDateTime creationDate, Client client,
                       Employee acceptedEmployee, Device device) {
            this.id = id;
            this.orderNumber = orderNumber;
            this.creationDate = creationDate;
            this.client = client;
            this.acceptedEmployee = acceptedEmployee;
            this.device = device;
        }

        public Builder company(Company company) {
            this.company = company;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder serialNumber(String serialNumber) {
            this.serialNumber = serialNumber;
            return this;
        }

        public Builder completedEmployee(Employee completedEmployee) {
            this.completedEmployee = completedEmployee;
            return this;
        }

        public Builder comletionDate(LocalDateTime comletionDate) {
            this.comletionDate = comletionDate;
            return this;
        }

        public Builder issueDate(LocalDateTime issueDate) {
            this.issueDate = issueDate;
            return this;
        }

        public Builder workDescription(String workDescription) {
            this.workDescription = workDescription;
            return this;
        }

        public Builder workPrice(PriceInfo workPrice) {
            this.workPrice = workPrice;
            return this;
        }

        public Builder spareParts(List<SparePart> spareParts) {
            this.spareParts = spareParts;
            return this;
        }

        public Builder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public Builder note(String note) {
            this.note = note;
            return this;
        }

        public Order build() {
            return new Order(this.id, this.orderNumber, this.creationDate, this.client, this.acceptedEmployee,
                    this.device, this.company, this.model, this.serialNumber, this.completedEmployee,
                    this.comletionDate, this.issueDate, this.workDescription, this.workPrice, this.spareParts,
                    this.orderStatus, this.note);
        }
    }

}

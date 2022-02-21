package by.epam.jwdsc.entity.dto;

import by.epam.jwdsc.entity.Order;

import java.util.List;

public class OrdersWithPagination {

    private List<Order> orders;
    private long totalOrdersQuantity;
    private long currentPage;

    public OrdersWithPagination() {
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public long getTotalOrdersQuantity() {
        return totalOrdersQuantity;
    }

    public void setTotalOrdersQuantity(long totalOrdersQuantity) {
        this.totalOrdersQuantity = totalOrdersQuantity;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersWithPagination that = (OrdersWithPagination) o;

        if (totalOrdersQuantity != that.totalOrdersQuantity) return false;
        if (currentPage != that.currentPage) return false;
        return orders != null ? orders.equals(that.orders) : that.orders == null;
    }

    @Override
    public int hashCode() {
        int result = orders != null ? orders.hashCode() : 0;
        result = 31 * result + (int) (totalOrdersQuantity ^ (totalOrdersQuantity >>> 32));
        result = 31 * result + (int) (currentPage ^ (currentPage >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrdersWithPagination{");
        sb.append("orders=").append(orders);
        sb.append(", totalOrdersQuantity=").append(totalOrdersQuantity);
        sb.append(", currentPage=").append(currentPage);
        sb.append('}');
        return sb.toString();
    }


}

package ecommerc;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class Order {
    private int orderId;
    private Customer customer;
    private List<Product> products;
    private LocalDateTime orderDate;
    private double totalAmount;

    public Order(int orderId, Customer customer, List<Product> products, LocalDateTime orderDate, double totalAmount) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer.getName() +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                '}';
    }
}

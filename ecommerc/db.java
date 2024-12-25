package ecommerc;

import java.util.*;
import java.time.LocalDateTime;
import javax.persistence.*;
import java.util.List;

public class ECommerceApp {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommerce-unit");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        // Add a Customer
        em.getTransaction().begin();
        Customer customer = new Customer(1, "Alice", "alice@example.com", "123 Elm Street");
        em.persist(customer);
        em.getTransaction().commit();

        // Query Customers
        TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
        List<Customer> customers = query.getResultList();
        for (Customer c : customers) {
            System.out.println(c);
        }

        // Close EntityManager
        em.close();
        emf.close();
    }
}


// Customer class
class Customer {
    private int customerId;
    private String name;
    private String email;
    private String address;

    public Customer(int customerId, String name, String email, String address) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

// Product class
class Product {
    private int productId;
    private String name;
    private String description;
    private double price;
    private int stock;

    public Product(int productId, String name, String description, double price, int stock) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void updateStock(int quantity) {
        this.stock += quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}

// Order class
class Order {
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

// ObjectDB class
class ObjectDB {
    private Map<Integer, Customer> customers = new HashMap<>();
    private Map<Integer, Product> products = new HashMap<>();
    private Map<Integer, Order> orders = new HashMap<>();

    // Customer operations
    public void addCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    public Customer getCustomer(int customerId) {
        return customers.get(customerId);
    }

    // Product operations
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public Product getProduct(int productId) {
        return products.get(productId);
    }

    public void updateProductStock(int productId, int quantity) {
        if (products.containsKey(productId)) {
            products.get(productId).updateStock(quantity);
        }
    }

    // Order operations
    public void addOrder(Order order) {
        orders.put(order.getOrderId(), order);
    }

    public Order getOrder(int orderId) {
        return orders.get(orderId);
    }

    @Override
    public String toString() {
        return "ObjectDB{" +
                "customers=" + customers.values() +
                ", products=" + products.values() +
                ", orders=" + orders.values() +
                '}';
    }
}



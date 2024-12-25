package ecommerc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
@Entity

public class ECommerceApp {
    public static void main(String[] args) {
        // Initialize the ObjectDB
        ObjectDB db = new ObjectDB();

        // Add Customers
        Customer customer1 = new Customer(1, "Alice", "alice@gmail.com", "123 Elm Street");
        Customer customer2 = new Customer(2, "Bob", "bob@gmail.com", "456 Oak Avenue");
        db.addCustomer(customer1);
        db.addCustomer(customer2);

        // Add Products
        Product product1 = new Product(101, "Smartphone", "High-end smartphone", 799.99, 20);
        Product product2 = new Product(102, "Headphones", "Noise-cancelling headphones", 199.99, 50);
        Product product3 = new Product(103, "Keyboard", "Mechanical keyboard", 99.99, 30);
        db.addProduct(product1);
        db.addProduct(product2);
        db.addProduct(product3);

        // Create an Order
        List<Product> orderProducts1 = new ArrayList<>();
        orderProducts1.add(product1);
        orderProducts1.add(product2);

        Order order1 = new Order(
            1001,
            customer1,
            orderProducts1,
            LocalDateTime.now(),
            product1.getPrice() + product2.getPrice()
        );
        db.addOrder(order1);

        // Create another Order
        List<Product> orderProducts2 = new ArrayList<>();
        orderProducts2.add(product3);

        Order order2 = new Order(
            1002,
            customer2,
            orderProducts2,
            LocalDateTime.now(),
            product3.getPrice()
        );
        db.addOrder(order2);

        // Print database contents
        System.out.println("Customers:");
        System.out.println(db.getCustomer(1));
        System.out.println(db.getCustomer(2));

        System.out.println("\nProducts:");
        System.out.println(db.getProduct(101));
        System.out.println(db.getProduct(102));
        System.out.println(db.getProduct(103));

        System.out.println("\nOrders:");
        System.out.println(db.getOrder(1001));
        System.out.println(db.getOrder(1002));
    }
}

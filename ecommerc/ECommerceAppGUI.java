package ecommerc;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ECommerceAppGUI {
    private ObjectDB db = new ObjectDB();

    public ECommerceAppGUI() {
        setupGUI();
    }

    private void setupGUI() {
        JFrame frame = new JFrame("E-Commerce Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Customers", createCustomerPanel());
        tabs.add("Products", createProductPanel());
        tabs.add("Orders", createOrderPanel());
        frame.add(tabs, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel createCustomerPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Customer List
        JTextArea customerList = new JTextArea();
        customerList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(customerList);

        // Add Customer Form
        JPanel form = new JPanel(new GridLayout(5, 2));
        form.add(new JLabel("ID:"));
        JTextField idField = new JTextField();
        form.add(idField);

        form.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        form.add(nameField);

        form.add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        form.add(emailField);

        form.add(new JLabel("Address:"));
        JTextField addressField = new JTextField();
        form.add(addressField);

        JButton addButton = new JButton("Add Customer");
        form.add(addButton);

        // Add Form and List
        panel.add(form, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add Customer Action
        addButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String email = emailField.getText();
            String address = addressField.getText();

            Customer customer = new Customer(id, name, email, address);
            db.addCustomer(customer);

            customerList.append(customer.toString() + "\n");

            // Clear fields
            idField.setText("");
            nameField.setText("");
            emailField.setText("");
            addressField.setText("");
        });

        return panel;
    }

    private JPanel createProductPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Product List
        JTextArea productList = new JTextArea();
        productList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(productList);

        // Add Product Form
        JPanel form = new JPanel(new GridLayout(6, 2));
        form.add(new JLabel("ID:"));
        JTextField idField = new JTextField();
        form.add(idField);

        form.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        form.add(nameField);

        form.add(new JLabel("Description:"));
        JTextField descriptionField = new JTextField();
        form.add(descriptionField);

        form.add(new JLabel("Price:"));
        JTextField priceField = new JTextField();
        form.add(priceField);

        form.add(new JLabel("Stock:"));
        JTextField stockField = new JTextField();
        form.add(stockField);

        JButton addButton = new JButton("Add Product");
        form.add(addButton);

        // Add Form and List
        panel.add(form, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add Product Action
        addButton.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String description = descriptionField.getText();
            double price = Double.parseDouble(priceField.getText());
            int stock = Integer.parseInt(stockField.getText());

            Product product = new Product(id, name, description, price, stock);
            db.addProduct(product);

            productList.append(product.toString() + "\n");

            // Clear fields
            idField.setText("");
            nameField.setText("");
            descriptionField.setText("");
            priceField.setText("");
            stockField.setText("");
        });

        return panel;
    }

    private JPanel createOrderPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Order List
        JTextArea orderList = new JTextArea();
        orderList.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderList);

        // Create Order Form
        JPanel form = new JPanel(new GridLayout(4, 2));
        form.add(new JLabel("Order ID:"));
        JTextField orderIdField = new JTextField();
        form.add(orderIdField);

        form.add(new JLabel("Customer ID:"));
        JTextField customerIdField = new JTextField();
        form.add(customerIdField);

        form.add(new JLabel("Product IDs (comma-separated):"));
        JTextField productIdsField = new JTextField();
        form.add(productIdsField);

        JButton addButton = new JButton("Create Order");
        form.add(addButton);

        // Add Form and List
        panel.add(form, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create Order Action
        addButton.addActionListener(e -> {
            int orderId = Integer.parseInt(orderIdField.getText());
            int customerId = Integer.parseInt(customerIdField.getText());
            String[] productIds = productIdsField.getText().split(",");

            Customer customer = db.getCustomer(customerId);
            List<Product> products = new ArrayList<>();
            double totalAmount = 0;

            for (String productIdStr : productIds) {
                int productId = Integer.parseInt(productIdStr.trim());
                Product product = db.getProduct(productId);
                products.add(product);
                totalAmount += product.getPrice();
            }

            Order order = new Order(orderId, customer, products, LocalDateTime.now(), totalAmount);
            db.addOrder(order);

            orderList.append(order.toString() + "\n");

            // Clear fields
            orderIdField.setText("");
            customerIdField.setText("");
            productIdsField.setText("");
        });

        return panel;
    }

    public static void main(String[] args) {
        new ECommerceAppGUI();
    }
}

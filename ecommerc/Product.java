package ecommerc;
import javax.persistence.Entity;

@Entity
public class Product {
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
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}

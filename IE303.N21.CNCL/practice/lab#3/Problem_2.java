import java.util.List;
import java.util.ArrayList;
class StoreFacade {
    private ProductManager productManager;
    private CartManager cartManager;
    private SalesManager salesManager;

    public StoreFacade() {
        productManager = new ProductManager();
        cartManager = new CartManager();
        salesManager = new SalesManager();
    }

    public void addProduct(Product product) {
        productManager.addProduct(product);
    }

    public void removeProduct(Product product) {
        productManager.removeProduct(product);
    }

    public void displayProducts() {
        productManager.displayProducts();
    }

    public void addToCart(Product product) {
        cartManager.addToCart(product);
    }

    public void removeFromCart(Product product) {
        cartManager.removeFromCart(product);
    }

    public double getTotalPrice() {
        return cartManager.getTotalPrice();
    }

    public void checkout() {
        salesManager.checkout(cartManager.getCart());
    }
}

class ProductManager {
    private List<Product> products;

    public ProductManager() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void displayProducts() {
        for (Product product : products) {
            System.out.println(product.getName() + ": " + product.getPrice());
        }
    }
}

class CartManager {
    private List<Product> cart;

    public CartManager() {
        cart = new ArrayList<>();
    }

    public void addToCart(Product product) {
        cart.add(product);
    }

    public void removeFromCart(Product product) {
        cart.remove(product);
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : cart) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public List<Product> getCart() {
        return cart;
    }
}

class SalesManager {
    public void checkout(List<Product> cart) {
        double totalPrice = 0.0;
        for (Product product : cart) {
            totalPrice += product.getPrice();
        }
        System.out.println("Total price: " + totalPrice);
        // Process the sale
    }
}


class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

public class Problem_2{
    public static void main(String[] args){
        StoreFacade store = new StoreFacade();
        

        Product p1 = new Product("Shirt", 29.99);
        Product p2 = new Product("Pants", 39.99);
        Product p3 = new Product("Shoes", 59.99);

        store.addProduct(p1);
        store.addProduct(p2);
        store.addProduct(p3);

        store.displayProducts();

        store.addToCart(p1);
        store.addToCart(p2);

        System.out.println("Total price: " + store.getTotalPrice());

        store.removeFromCart(p2);

        System.out.println("Updated total price: " + store.getTotalPrice());

        store.checkout();
    }
}

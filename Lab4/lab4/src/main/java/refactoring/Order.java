package refactoring;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Customer customer;
    private Map<Product, Integer> items = new HashMap<>();
    private double totalPrice;
    private boolean isPaid;

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addItem(Product product, int quantity) {
        if (product.getStock() >= quantity) {
            items.put(product, quantity);
            totalPrice += product.getPrice() * quantity;
        } else {
            throw new IllegalArgumentException("Insufficient stock for " + product.getName());
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void markAsPaid() {
        isPaid = true;
    }

    public void addToTotal(double amount) {
        totalPrice += amount;
    }
}
package refactoring.model;

public class Order {
    private final String id;
    private final int quantity;
    private final double unitPrice;
    private final double weightKg;

    public Order(String id, int quantity, double unitPrice, double weightKg) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.weightKg = weightKg;
    }

    public String getId() { return id; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getWeightKg() { return weightKg; }

    @Override
    public String toString() {
        return "Order{" + id + ", q=" + quantity + ", price=" + unitPrice + "}";
    }
}
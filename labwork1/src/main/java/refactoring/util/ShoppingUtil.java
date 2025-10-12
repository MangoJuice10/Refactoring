package refactoring.util;

import refactoring.model.Order;

public class ShippingUtil {

    // REFACTORING CANDIDATE: Extract Method (shipping calculation chunk)
    public double shippingForOrder(Order order) {
        // simple shipping: base + per-kg; free shipping if total price > 200
        double price = order.getQuantity() * order.getUnitPrice();
        double base = 5.0;
        double perKg = 2.0;
        if (price > 200) {
            return 0.0;
        }
        return base + perKg * order.getWeightKg();
    }
}

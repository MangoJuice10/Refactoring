package refactoring.util;

import refactoring.model.Order;

public class ShippingUtil {
    private double calcOrderPrice(Order o) {
        return o.getQuantity() * o.getUnitPrice();
    }

    private boolean isFreeShippingEligible(double price) {
        return price > 200;
    }

    public double calcShippingPrice(Order order) {
        double price = this.calcOrderPrice(order);
        double base = 5.0;
        double perKg = 2.0;

        if (isFreeShippingEligible(price)) {
            return 0.0;
        }
        
        return base + perKg * order.getWeightKg();
    }
}
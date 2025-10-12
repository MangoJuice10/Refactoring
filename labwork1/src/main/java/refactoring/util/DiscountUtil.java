package refactoring.util;

import refactoring.model.Order;

public class DiscountUtil {
    private double orderPrice(Order o) {
        return o.getQuantity() * o.getUnitPrice();
    }

    public double calcDiscount(Order order) {
        final boolean isPriceBig = orderPrice(order) > 100;
        final boolean isPriceMedium = orderPrice(order) > 50 && orderPrice(order) <= 100;

        if (isPriceBig) {
            return orderPrice(order) * 0.10;
        } else if (isPriceMedium) {
            return orderPrice(order) * 0.05;
        } else {
            return 0.0;
        }
    }
}
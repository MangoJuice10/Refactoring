package refactoring.util;

import refactoring.model.Order;

public class DiscountUtil {

    // REFACTORING CANDIDATE: Replace Temp with Query (price temporary var)
    // REFACTORING CANDIDATE: Introduce Explaining Variable (explain condition)
    public double computeDiscount(Order order) {
        double price = order.getQuantity() * order.getUnitPrice(); // temp
        // поясняющая логика: скидка 10% если цена > 100, 5% если > 50
        boolean isBig = price > 100;
        boolean isMedium = price > 50 && price <= 100;
        if (isBig) {
            return price * 0.10;
        } else if (isMedium) {
            return price * 0.05;
        } else {
            return 0.0;
        }
    }
}

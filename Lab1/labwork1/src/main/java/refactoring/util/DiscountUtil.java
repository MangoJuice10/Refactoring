package refactoring.util;

import refactoring.model.Order;

public class DiscountUtil {

    public double computeDiscount(Order order) {
        // REFACTORING CANDIDATE: Replace Temp with Query (Временную переменную
        // 'price' можно заменить вызовом метода calcOrderPrice)
        double price = order.getQuantity() * order.getUnitPrice();
        // REFACTORING CANDIDATE: Extract Variable (Условие price > 100
        // удобно заменить на переменную isPriceBig)
        if (price > 100) {
            return price * 0.10;
            // REFACTORING CANDIDATE: Extract variable (Условие price > 50 && price <= 100
            // удобно заменить на переменную isPriceMedium)
        } else if (price > 50 && price <= 100) {
            return price * 0.05;
        } else {
            return 0.0;
        }
    }
}

package refactoring.util;

import refactoring.model.Order;

public class ShippingUtil {

    public double shippingForOrder(Order order) {
        // REFACTORING CANDIDATE: Extract Method (Код для вычисления стоимости заказа
        // можно вынести в отдельный метод calcOrderPrice)
        double price = order.getQuantity() * order.getUnitPrice();
        double base = 5.0;
        double perKg = 2.0;

        // REFACTORING CANDIDATE: Extract Method (Код для проверки полученной стоимости
        // для определения возможности бесплатной доставки можно вынести в отдельный
        // метод isFreeShippingEligable)
        if (price > 200) {
            return 0.0;
        }

        return base + perKg * order.getWeightKg();
    }
}
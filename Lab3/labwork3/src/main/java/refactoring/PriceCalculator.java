package refactoring;

public class PriceCalculator {

    /**
     * Плохой вариант: длинная цепочка условий и вычислений в одном методе.
     * Пригоден для рефакторинга: Decompose Conditional.
     */
    public double calculateFinalPrice(Reservation reservation) {
        Car car = reservation.getCar();
        Customer customer = reservation.getCustomer();
        int days = reservation.getDays();

        double price = car.getBasePricePerDay() * days;
        // сложная цепочка условий, смешаны вычисления и логика
        if (days > 30) {
            // долгосрочная аренда — 20% скидка, но если SUV — только 10%
            if (car.getType().name().equals("SUV")) {
                price = price * 0.9;
            } else {
                price = price * 0.8;
            }
        } else if (customer.getName() != null && customer.getName().startsWith("VIP")) {
            // персональная скидка VIP клиентов
            price = price * 0.85;
            if (car.getType().name().equals("TRUCK")) {
                // VIP для тяжелых машин — дополнительный сбор $50
                price += 50;
            }
        } else {
            // обычные клиенты — weekend surcharge (эмуляция: если days==2 считаем weekend)
            if (days == 2) {
                price += 30;
            }
            // если машина SUV и день больше 3 — добавляем страховку
            if (car.getType().name().equals("SUV") && days > 3) {
                price += 15 * days;
            }
        }

        // налоги: если цена > 1000 — 15%, иначе 10%
        if (price > 1000) {
            price = price * 1.15;
        } else {
            price = price * 1.10;
        }

        // плата за обслуживание: если клиент имеет меньше 100 очков — $20, иначе $10
        Integer points = customer.getLoyaltyPoints();
        if (points == null || points < 100) {
            price += 20;
        } else {
            price += 10;
        }

        return Math.round(price * 100.0) / 100.0;
    }
}

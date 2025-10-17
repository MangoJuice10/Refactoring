package refactoring;

public class PriceCalculator {

    // REFACTORING NEEDED: Decompose Conditional (условные операторы и выполняемые
    // блоки настолько сложные, что становится непонятно, какое условие приводит к
    // какому результату; следует выделить отдельные методы с помощью Extract
    // Method)
    public double calculateFinalPrice(Reservation reservation) {
        Car car = reservation.getCar();
        Customer customer = reservation.getCustomer();
        int days = reservation.getDays();

        double price = car.getBasePricePerDay() * days;
        if (days > 30) {
            if (car.getType().name().equals("SUV")) {
                price = price * 0.9;
            } else {
                price = price * 0.8;
            }
        } else if (customer.getName() != null && customer.getName().startsWith("VIP")) {
            price = price * 0.85;
            if (car.getType().name().equals("TRUCK")) {
                price += 50;
            }
        } else {
            if (days == 2) {
                price += 30;
            }
            if (car.getType().name().equals("SUV") && days > 3) {
                price += 15 * days;
            }
        }

        if (price > 1000) {
            price = price * 1.15;
        } else {
            price = price * 1.10;
        }

        Integer points = customer.getLoyaltyPoints();
        if (points == null || points < 100) {
            price += 20;
        } else {
            price += 10;
        }

        return Math.round(price * 100.0) / 100.0;
    }
}

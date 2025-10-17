package refactoring;

public class PriceCalculator {

    // REFACTORING NEEDED: Decompose Conditional (условные операторы и выполняемые
    // блоки настолько сложные, что становится непонятно, какое условие приводит к
    // какому результату; следует выделить отдельные методы с помощью Extract Method
    private boolean isReservation2Days(int days) {
        return days == 2;
    }

    private boolean isReservationOver3Days(int days) {
        return days > 3;
    }

    private boolean isReservationOver30Days(int days) {
        return days > 30;
    }

    private boolean isCustomerVIP(Customer customer) {
        return customer.getName() != null && customer.getName().startsWith("VIP");
    }

    private boolean isCarSUV(Car car) {
        return car.getType().name().equals("SUV");
    }

    private boolean isCarTruck(Car car) {
        return car.getType().name().equals("TRUCK");
    }

    private double calcBasePrice(Customer customer, Car car, int days) {
        double result = car.getBasePricePerDay() * days;
        if (isReservationOver30Days(days)) {
            if (isCarSUV(car)) {
                result *= 0.9;
            } else {
                result *= 0.8;
            }
        } else if (isCustomerVIP(customer)) {
            result *= 0.85;
            if (isCarTruck(car)) {
                result += 50;
            }
        } else {
            if (isReservation2Days(days)) {
                result += 30;
            }
            if (isCarSUV(car) && isReservationOver3Days(days)) {
                result += 15 * days;
            }
        }
        return result;
    }

    private boolean isPriceOver1000(double price) {
        return price > 1000;
    }

    private double calcTaxedPrice(double price) {
        double result = price;
        if (isPriceOver1000(price)) {
            result *= 1.15;
        } else {
            result *= 1.10;
        }
        return result;
    }

    private boolean pointsLessThan100(Customer customer) {
        Integer points = customer.getLoyaltyPoints();
        return points == null || points < 100;
    }

    private double calcFinalPrice(Customer customer, double price) {
        double result = price;
        if (pointsLessThan100(customer)) {
            result += 20;
        } else {
            result += 10;
        }
        return Math.round(result * 100.0) / 100.0;
    }

    public double calculateFinalPrice(Reservation reservation) {
        Car car = reservation.getCar();
        Customer customer = reservation.getCustomer();
        int days = reservation.getDays();

        double price = calcBasePrice(customer, car, days);
        double taxedPrice = calcTaxedPrice(price);
        double finalPrice = calcFinalPrice(customer, taxedPrice);

        return finalPrice;
    }
}

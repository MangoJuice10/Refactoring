package refactoring;

public class VehicleService {

    // REFACTORING NEEDED: Replace Conditional with Polymorphism (вычисление
    // стоимости обслуживания зависит от типа транспортного средства, проверяемого в
    // условном операторе)
    public double computeMaintenanceCost(Car car) {
        VehicleType type = car.getType();
        double base = car.getBasePricePerDay();
        if (type == VehicleType.COMPACT) {
            return base * 0.05 + 10;
        } else if (type == VehicleType.SEDAN) {
            return base * 0.07 + 15;
        } else if (type == VehicleType.SUV) {
            return base * 0.12 + 25;
        } else if (type == VehicleType.TRUCK) {
            return base * 0.2 + 40;
        } else {
            return base * 0.1 + 20;
        }
    }
}

package refactoring;

import java.util.Objects;

public class Car {
    private final String id;
    private final VehicleType type;
    private final double basePricePerDay;
    private boolean available;

    public Car(String id, VehicleType type, double basePricePerDay, boolean available) {
        this.id = id;
        this.type = type;
        this.basePricePerDay = basePricePerDay;
        this.available = available;
    }

    public String getId() {
        return id;
    }

    public VehicleType getType() {
        return type;
    }

    public double getBasePricePerDay() {
        return basePricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package refactoring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AvailabilityNotifierTest {

    @Test
    public void notify_when_available_contains_availableText() {
        Car car = new Car("C10", VehicleType.SEDAN, 50.0, true);
        Customer cust = new Customer("U10", "Bob", 0, "b@b.com", null);
        Reservation r = new Reservation("R10", car, cust, 3, false, 0L, 0L);

        AvailabilityNotifier n = new AvailabilityNotifier();
        String res = n.notifyAvailability(r);
        assertTrue(res.contains("is available"));
    }

    @Test
    public void notify_when_not_available_contains_notAvailableText() {
        Car car = new Car("C11", VehicleType.SEDAN, 50.0, false);
        Customer cust = new Customer("U11", "Ann", 0, "a@a.com", null);
        Reservation r = new Reservation("R11", car, cust, 2, false, 0L, 0L);

        AvailabilityNotifier n = new AvailabilityNotifier();
        String res = n.notifyAvailability(r);
        assertTrue(res.contains("NOT available"));
    }
}

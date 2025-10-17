package refactoring;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingProcessorTest {

    @Test
    public void tryBook_findsFirstAvailableAppropriateCar() {
        Car c1 = new Car("1", VehicleType.SUV, 120.0, true);
        Car c2 = new Car("2", VehicleType.SEDAN, 80.0, true);
        Car c3 = new Car("3", VehicleType.COMPACT, 60.0, true);
        List<Car> fleet = Arrays.asList(c1, c2, c3);

        Customer cust = new Customer("u1", "Anna", 0, "a@a.com", null);
        BookingProcessor p = new BookingProcessor();
        Reservation res = p.tryBook(fleet, cust, 3);

        assertNotNull(res);
        assertEquals("RES-2", res.getId());
        assertFalse(c2.isAvailable()); // был помечен как зарезервированный
    }

    @Test
    public void tryBook_noneFound_returnsNull() {
        Car c1 = new Car("1", VehicleType.TRUCK, 200.0, true);
        Car c2 = new Car("2", VehicleType.SUV, 150.0, false);
        List<Car> fleet = Arrays.asList(c1, c2);

        Customer cust = new Customer("u2", "Tom", 0, "t@t.com", null);
        BookingProcessor p = new BookingProcessor();
        Reservation res = p.tryBook(fleet, cust, 2);

        assertNull(res);
    }
}

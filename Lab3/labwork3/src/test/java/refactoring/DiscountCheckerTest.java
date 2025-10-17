package refactoring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountCheckerTest {

    @Test
    public void eligible_when_loyaltyHigh() {
        Customer c = new Customer("u1", "John", 1500, "john@example.com", null);
        Car car = new Car("c1", VehicleType.COMPACT, 30.0, true);
        Reservation r = new Reservation("r1", car, c, 1, false, 0L, 0L);

        DiscountChecker d = new DiscountChecker();
        assertTrue(d.isEligibleForSpecialDiscount(r));
    }

    @Test
    public void eligible_when_emailHasPromo() {
        Customer c = new Customer("u2", "Jane", 10, "jane+promo20@ex.com", null);
        Car car = new Car("c2", VehicleType.SEDAN, 40.0, true);
        Reservation r = new Reservation("r2", car, c, 1, false, 0L, 0L);

        DiscountChecker d = new DiscountChecker();
        assertTrue(d.isEligibleForSpecialDiscount(r));
    }
}

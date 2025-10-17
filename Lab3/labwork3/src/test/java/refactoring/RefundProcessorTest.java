package refactoring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RefundProcessorTest {

    @Test
    public void vipWithin24h_fullRefund() {
        Car car = new Car("X1", VehicleType.SEDAN, 100.0, true);
        Customer c = new Customer("u1", "VIP_Sam", 0, "s@ex.com", null);
        long now = 1_000_000L;
        Reservation r = new Reservation("r1", car, c, 2, true, 0L, now - 3600); // paid 1h ago

        RefundProcessor p = new RefundProcessor();
        double refund = p.calculateRefund(r, now);
        assertEquals(200.0, refund);
    }

    @Test
    public void nonVipWithin24h_halfRefund() {
        Car car = new Car("X2", VehicleType.SEDAN, 50.0, true);
        Customer c = new Customer("u2", "Bob", 0, "b@ex.com", null);
        long now = 2_000_000L;
        Reservation r = new Reservation("r2", car, c, 4, true, 0L, now - 2_000); // paid recently

        RefundProcessor p = new RefundProcessor();
        double refund = p.calculateRefund(r, now);
        assertEquals(50.0 * 4 * 0.5, refund);
    }
}

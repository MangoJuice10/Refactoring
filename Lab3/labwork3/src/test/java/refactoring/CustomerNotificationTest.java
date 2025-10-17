package refactoring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerNotificationTest {

    @Test
    public void sendBookingConfirmation_emailPreferred_success() {
        Car car = new Car("Z1", VehicleType.COMPACT, 30.0, true);
        Customer c = new Customer("u1", "Mia", 10, "mia@example.com", Customer.NotificationChannel.EMAIL);
        Reservation r = new Reservation("RZ1", car, c, 2, false, 0L, 0L);

        CustomerNotification n = new CustomerNotification();
        String res = n.sendBookingConfirmation(r);
        assertEquals("sent-email", res);
    }

    @Test
    public void sendBookingConfirmation_noCustomer_returnsNoCustomer() {
        Car car = new Car("Z2", VehicleType.SEDAN, 40.0, true);
        Reservation r = new Reservation("RZ2", car, null, 1, false, 0L, 0L);

        CustomerNotification n = new CustomerNotification();
        String res = n.sendBookingConfirmation(r);
        assertEquals("no-customer", res);
    }
}

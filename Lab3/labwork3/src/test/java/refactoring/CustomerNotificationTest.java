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

    @Test
    public void sendBookingConfirmation_noEmail_returnsNoEmail() {
        Car car = new Car("Z3", VehicleType.SUV, 50.0, true);
        Customer c = new Customer("u2", "Leo", 5, null, Customer.NotificationChannel.EMAIL);
        Reservation r = new Reservation("RZ3", car, c, 3, false, 0L, 0L);

        CustomerNotification n = new CustomerNotification();
        String res = n.sendBookingConfirmation(r);
        assertEquals("no-email", res);
    }

    @Test
    public void sendBookingConfirmation_smsPreferred_returnsSmsNotAvailable() {
        Car car = new Car("Z4", VehicleType.SUV, 50.0, true);
        Customer c = new Customer("u3", "Sara", 5, "sara@example.com", Customer.NotificationChannel.SMS);
        Reservation r = new Reservation("RZ4", car, c, 3, false, 0L, 0L);

        CustomerNotification n = new CustomerNotification();
        String res = n.sendBookingConfirmation(r);
        assertEquals("sms-not-available", res);
    }
}
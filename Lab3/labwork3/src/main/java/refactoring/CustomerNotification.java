package refactoring;

public class CustomerNotification {

    // REFACTORING NEEDED: Introduce Null Object (Слишком много проверок на null
    // самого объекта, его полей и методов. Следует создать класс
    // NullCustomerNotification с методом isNull())
    public String sendBookingConfirmation(Reservation reservation) {
        Customer c = reservation.getCustomer();
        if (c == null) {
            return "no-customer";
        }

        if (c.getPreferredChannel() == null) {
            if (c.getEmail() != null) {
                doSendEmail(c.getEmail(), "Your booking " + reservation.getId());
                return "sent-email";
            } else {
                return "no-channel";
            }
        } else if (c.getPreferredChannel() == Customer.NotificationChannel.EMAIL) {
            if (c.getEmail() != null) {
                doSendEmail(c.getEmail(), "Your booking " + reservation.getId());
                return "sent-email";
            } else {
                return "no-email";
            }
        } else if (c.getPreferredChannel() == Customer.NotificationChannel.SMS) {
            return "sms-not-available";
        } else {
            return "unknown-channel";
        }
    }

    private void doSendEmail(String email, String body) {
        // Для демонстрации — ничего не делаем
    }
}

package refactoring;

public class CustomerNotification {

    // REFACTORING NEEDED: Introduce Null Object (Слишком много проверок на null
    // самого объекта, его полей и методов. Следует создать класс
    // NullCustomer с методом isNull())
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
        }

        if (c.isPreferredChannelEmail()) {
            if (c.getEmail() != null && !c.getEmail().isEmpty()) {
                doSendEmail(c.getEmail(), "Your booking " + reservation.getId());
                return "sent-email";
            } else {
                return "no-email";
            }
        } else if (c.isPreferredChannelSms()) {
            return "sms-not-available";
        } else {
            return "unknown-channel";
        }
    }

    // Имитационный метод
    private void doSendEmail(String email, String body) {
        // Демонстрация отправки Email
    }
}

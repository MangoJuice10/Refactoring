package refactoring;

public class CustomerNotification {
    public String sendBookingConfirmation(Reservation reservation) {
        Customer c = reservation.getCustomer();

        if (c.isNull()) {
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

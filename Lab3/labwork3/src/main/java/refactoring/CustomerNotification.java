package refactoring;

public class CustomerNotification {

    /**
     * Плохой вариант: много проверок на null и ветвлений для разных каналов уведомления.
     * При рефакторинге: Introduce Null Object.
     */
    public String sendBookingConfirmation(Reservation reservation) {
        Customer c = reservation.getCustomer();
        if (c == null) {
            // ничего не делаем
            return "no-customer";
        }

        if (c.getPreferredChannel() == null) {
            // если нет предпочитаемого канала, пробуем email
            if (c.getEmail() != null) {
                // отправка по email
                doSendEmail(c.getEmail(), "Your booking " + reservation.getId());
                return "sent-email";
            } else {
                // нет способа уведомить
                return "no-channel";
            }
        } else if (c.getPreferredChannel() == Customer.NotificationChannel.EMAIL) {
            if (c.getEmail() != null) {
                doSendEmail(c.getEmail(), "Your booking " + reservation.getId());
                return "sent-email";
            } else {
                // неконсистентная информация
                return "no-email";
            }
        } else if (c.getPreferredChannel() == Customer.NotificationChannel.SMS) {
            // В этом плохом примере у нас нет номера телефона в модели,
            // поэтому мы симулируем, что SMS недоступен
            return "sms-not-available";
        } else {
            return "unknown-channel";
        }
    }

    private void doSendEmail(String email, String body) {
        // имитация отправки
    }
}

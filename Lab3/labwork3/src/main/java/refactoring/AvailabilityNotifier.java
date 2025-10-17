package refactoring;

public class AvailabilityNotifier {

    // RFECATORING NEEDED: Consolidate Duplicate Conditional Fragments (две
    // последние строчки в условных блоках if и else дублируются, следует вынести их
    // после условных операторов, т.к. в каждом блоке они находятся в конце)
    public String notifyAvailability(Reservation reservation) {
        Car car = reservation.getCar();
        String message;
        if (car.isAvailable()) {
            message = "Car " + car.getId() + " is available for reservation " + reservation.getId();
            sendEmail(reservation, message);
            return message;
        } else {
            message = "Car " + car.getId() + " is NOT available for reservation " + reservation.getId()
                    + ". We'll notify when available.";
            sendEmail(reservation, message);
            return message;
        }
    }

    // Примитивная имитация отправки
    private void sendEmail(Reservation reservation, String body) {
        // Для демонстрации — ничего не делаем
    }
}

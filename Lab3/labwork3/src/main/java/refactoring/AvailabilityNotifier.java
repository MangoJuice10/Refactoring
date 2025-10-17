package refactoring;

public class AvailabilityNotifier {
    public String notifyAvailability(Reservation reservation) {
        Car car = reservation.getCar();
        String message;
        if (car.isAvailable()) {
            message = "Car " + car.getId() + " is available for reservation " + reservation.getId();
        } else {
            message = "Car " + car.getId() + " is NOT available for reservation " + reservation.getId()
                    + ". We'll notify when available.";
        }
        sendEmail(reservation, message);
        return message;
    }

    // Примитивная имитация отправки
    private void sendEmail(Reservation reservation, String body) {
        // Для демонстрации — ничего не делаем
    }
}

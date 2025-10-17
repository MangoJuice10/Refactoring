package refactoring;

import java.util.List;

public class BookingProcessor {

    /**
     * Плохой вариант: control flag используется, код громоздкий.
     * При рефакторинге: Remove Control Flag.
     */
    public Reservation tryBook(List<Car> fleet, Customer customer, int days) {
        boolean found = false;
        Reservation result = null;
        for (Car c : fleet) {
            // ищем первую доступную машину подходящего типа
            if (!found) {
                if (c.isAvailable()) {
                    if (c.getBasePricePerDay() <= 100.0) {
                        // дополнительная логика — предпочтение по типу
                        if (c.getType() == VehicleType.SEDAN || c.getType() == VehicleType.COMPACT) {
                            // резервируем
                            c.setAvailable(false);
                            result = new Reservation("RES-" + c.getId(), c, customer, days, false, 0L, 0L);
                            found = true;
                        } else {
                            // для других типов не бронируем
                        }
                    } else {
                        // дорогие машины пропускаем
                    }
                } else {
                    // не доступна
                }
            }
        }

        // в конце возвращаем результат (null если не найдено)
        return result;
    }
}

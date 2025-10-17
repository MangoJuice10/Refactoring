package refactoring;

import java.util.List;

public class BookingProcessor {

    // REFACTORING NEEDED: Remove Control Flag, Replace Nested Conditional with Guard
    // Clauses (Используется управляющий флаг found, который следует заменить
    // выходом из цикла с помощью управляющго оператора break; слишком много
    // вложенных условных операторов, которые следует заменить граничными
    // операторами)
    public Reservation tryBook(List<Car> fleet, Customer customer, int days) {
        boolean found = false;
        Reservation result = null;
        for (Car c : fleet) {
            if (!found) {
                if (c.isAvailable()) {
                    if (c.getBasePricePerDay() <= 100.0) {
                        if (c.getType() == VehicleType.SEDAN || c.getType() == VehicleType.COMPACT) {
                            c.setAvailable(false);
                            result = new Reservation("RES-" + c.getId(), c, customer, days, false, 0L, 0L);
                            found = true;
                        }
                    }
                }
            }
        }

        return result;
    }
}

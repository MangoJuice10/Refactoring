package refactoring;

import java.util.List;

public class BookingProcessor {
    private boolean isCardSedan(Car c) {
        return c.getType().name().equals("SEDAN");
    }

    private boolean isCardCompact(Car c) {
        return c.getType().name().equals("COMPACT");
    }

    public Reservation tryBook(List<Car> fleet, Customer customer, int days) {
        Reservation result = null;
        for (Car c : fleet) {
            if (c.isAvailable() && c.getBasePricePerDay() <= 100.0 && (isCardSedan(c) || isCardCompact(c))) {
                c.setAvailable(false);
                result = new Reservation("RES-" + c.getId(), c, customer, days, false, 0L, 0L);
                break;
            }
        }

        return result;
    }
}

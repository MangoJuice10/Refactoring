package refactoring;

public class DiscountChecker {

    /**
     * Плохой вариант: несколько разных if, но все они ведут к одному и тому же результату.
     * Лучше объединить условия в одно.
     */
    public boolean isEligibleForSpecialDiscount(Reservation reservation) {
        Customer c = reservation.getCustomer();

        // проверка 1: по очкам
        if (c.getLoyaltyPoints() != null && c.getLoyaltyPoints() > 1000) {
            return true;
        }

        // проверка 2: по имени (корпоративные клиенты начинаются с CORP)
        if (c.getName() != null && c.getName().startsWith("CORP")) {
            return true;
        }

        // проверка 3: если бронирование очень длинное
        if (reservation.getDays() > 60) {
            return true;
        }

        // проверка 4: если у клиента есть специальный промокод в email
        if (c.getEmail() != null && c.getEmail().contains("promo20")) {
            return true;
        }

        return false;
    }
}

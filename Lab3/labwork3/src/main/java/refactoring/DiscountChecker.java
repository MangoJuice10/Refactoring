package refactoring;

public class DiscountChecker {

    // REFACTORING NEEDED: Consolidate Conditional Expression (несколько условных
    // операторов ведут к одному и тому же результату, следует объединить их в один
    // условный оператор)
    public boolean isEligibleForSpecialDiscount(Reservation reservation) {
        Customer c = reservation.getCustomer();

        if (c.getLoyaltyPoints() != null && c.getLoyaltyPoints() > 1000) {
            return true;
        }

        if (c.getName() != null && c.getName().startsWith("CORP")) {
            return true;
        }

        if (reservation.getDays() > 60) {
            return true;
        }

        if (c.getEmail() != null && c.getEmail().contains("promo20")) {
            return true;
        }

        return false;
    }
}

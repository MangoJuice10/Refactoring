package refactoring;

public class DiscountChecker {
    private boolean isLoyalCustomer(Customer c) {
        return c.getLoyaltyPoints() != null && c.getLoyaltyPoints() > 1000;
    }

    private boolean isCorporateCustomer(Customer c) {
        return c.getName() != null && c.getName().startsWith("CORP");
    }

    private boolean isReservationOverTwoMonths(Reservation r) {
        return r.getDays() > 60;
    }

    private boolean hasPromotionalEmail(Customer c) {
        return c.getEmail() != null && c.getEmail().contains("promo20");
    }

    public boolean isEligibleForSpecialDiscount(Reservation reservation) {
        Customer c = reservation.getCustomer();

        if (isLoyalCustomer(c) || isCorporateCustomer(c) || isReservationOverTwoMonths(reservation)
                || hasPromotionalEmail(c)) {
            return true;
        }

        return false;
    }
}

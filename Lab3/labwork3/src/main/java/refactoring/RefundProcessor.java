package refactoring;

public class RefundProcessor {

    // REFACTORING NEEDED: Replace Nested Conditional with Guard
    // Clauses (Слишком много вложенных условных операторов, которые следует
    // заменить граничными операторами)
    public double calculateRefund(Reservation reservation, long currentEpochSeconds) {
        if (reservation.isCanceled()) {
            if (reservation.getPaidAtEpochSeconds() > 0) {
                long secondsSincePaid = currentEpochSeconds - reservation.getPaidAtEpochSeconds();
                if (secondsSincePaid <= 24 * 3600) {
                    Customer c = reservation.getCustomer();
                    if (c.getName() != null && c.getName().startsWith("VIP")) {
                        return computeAmount(reservation);
                    } else {
                        return computeAmount(reservation) * 0.5;
                    }
                } else {
                    return 0.0;
                }
            } else {
                return 0.0;
            }
        } else {
            return 0.0;
        }
    }

    private double computeAmount(Reservation reservation) {
        return reservation.getCar().getBasePricePerDay() * reservation.getDays();
    }
}

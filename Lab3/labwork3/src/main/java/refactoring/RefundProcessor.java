package refactoring;

public class RefundProcessor {

    /**
     * Плохой вариант: вложенные if-ы. Трудно читать. При рефакторинге — Replace Nested Conditional with Guard Clauses.
     */
    public double calculateRefund(Reservation reservation, long currentEpochSeconds) {
        // Сценарии:
        // - если не отменено -> 0
        // - если отменено и прошло меньше 24ч с оплаты и клиент VIP -> полный возврат
        // - если отменено и прошло меньше 24ч -> 50%
        // - иначе 0
        if (reservation.isCanceled()) {
            if (reservation.getPaidAtEpochSeconds() > 0) {
                long secondsSincePaid = currentEpochSeconds - reservation.getPaidAtEpochSeconds();
                if (secondsSincePaid <= 24 * 3600) {
                    Customer c = reservation.getCustomer();
                    if (c.getName() != null && c.getName().startsWith("VIP")) {
                        // VIP within 24h -> full refund
                        return computeAmount(reservation);
                    } else {
                        // non-VIP within 24h -> half refund
                        return computeAmount(reservation) * 0.5;
                    }
                } else {
                    // paid but more than 24h -> no refund
                    return 0.0;
                }
            } else {
                // не оплачивалось -> ничего
                return 0.0;
            }
        } else {
            // не отменено -> ничего
            return 0.0;
        }
    }

    private double computeAmount(Reservation reservation) {
        return reservation.getCar().getBasePricePerDay() * reservation.getDays();
    }
}

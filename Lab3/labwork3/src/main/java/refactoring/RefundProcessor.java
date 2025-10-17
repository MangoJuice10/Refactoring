package refactoring;

public class RefundProcessor {
    public double calculateRefund(Reservation reservation, long currentEpochSeconds) {
        if (!reservation.isCanceled()) {
            return 0.0;
        }
        if (!(reservation.getPaidAtEpochSeconds() > 0)) {
            return 0.0;
        }
        long secondsSincePaid = currentEpochSeconds - reservation.getPaidAtEpochSeconds();
        if (!(secondsSincePaid <= 24 * 3600)) {
            return 0.0;
        }

        Customer c = reservation.getCustomer();
        if (c.getName() != null && c.getName().startsWith("VIP")) {
            return computeAmount(reservation);
        }
        return computeAmount(reservation) * 0.5;
    }

    private double computeAmount(Reservation reservation) {
        return reservation.getCar().getBasePricePerDay() * reservation.getDays();
    }
}

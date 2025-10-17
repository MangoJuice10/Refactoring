package refactoring;

public class Reservation {
    private final String id;
    private final Car car;
    private final Customer customer;
    private final int days;
    private final boolean canceled;
    private final long createdAtEpochSeconds;
    private final long paidAtEpochSeconds;

    public Reservation(String id, Car car, Customer customer, int days,
                       boolean canceled, long createdAtEpochSeconds, long paidAtEpochSeconds) {
        this.id = id;
        this.car = car;
        this.customer = customer;
        this.days = days;
        this.canceled = canceled;
        this.createdAtEpochSeconds = createdAtEpochSeconds;
        this.paidAtEpochSeconds = paidAtEpochSeconds;
    }

    public String getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return (customer == null) ? new NullCustomer() : customer;
    }

    public int getDays() {
        return days;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public long getCreatedAtEpochSeconds() {
        return createdAtEpochSeconds;
    }

    public long getPaidAtEpochSeconds() {
        return paidAtEpochSeconds;
    }
}

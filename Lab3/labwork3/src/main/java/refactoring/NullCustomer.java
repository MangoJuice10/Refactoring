package refactoring;

public class NullCustomer extends Customer {
    public NullCustomer() {
        super(null, null, 0, null, null);
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public NotificationChannel getPreferredChannel() {
        return null;
    }

    @Override
    public boolean isPreferredChannelEmail() {
        return false;
    }

    @Override
    public boolean isPreferredChannelSms() {
        return false;
    }

    public boolean isNull() {
        return true;
    }
}

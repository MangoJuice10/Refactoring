package refactoring;

public class Customer {
    private final String id;
    private final String name;
    private final Integer loyaltyPoints;
    private final String email;
    private final NotificationChannel preferredChannel;

    public enum NotificationChannel { EMAIL, SMS }

    public Customer(String id, String name, Integer loyaltyPoints, String email, NotificationChannel preferredChannel) {
        this.id = id;
        this.name = name;
        this.loyaltyPoints = loyaltyPoints;
        this.email = email;
        this.preferredChannel = preferredChannel;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public String getEmail() {
        return email;
    }

    public NotificationChannel getPreferredChannel() {
        return preferredChannel;
    }
    
    public String getContactInfo() {
        if (email != null && !email.isEmpty()) {
            return email;
        } else {
            return null;
        }
    }

    public boolean isPreferredChannelEmail() {
        if (preferredChannel == null) {
            return false;
        }
        return preferredChannel == NotificationChannel.EMAIL;
    }

    public boolean isPreferredChannelSms() {
        if (preferredChannel == null) {
            return false;
        }
        return preferredChannel == NotificationChannel.SMS;
    }

    public boolean isNull() {
        return false;
    }
}

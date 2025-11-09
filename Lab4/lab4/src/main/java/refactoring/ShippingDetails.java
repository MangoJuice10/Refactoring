package refactoring;

public class ShippingDetails {
    private Order order;
    private double shippingCost;
    private String shippingAddress;
    private String shippingCity;
    private String shippingZip;
    private String shippingCountry;
    private boolean expedited;

    public ShippingDetails(Order order, String shippingAddress, String shippingCity, String shippingZip, String shippingCountry, boolean expedited) {
        this.order = order;
        this.shippingAddress = shippingAddress;
        this.shippingCity = shippingCity;
        this.shippingZip = shippingZip;
        this.shippingCountry = shippingCountry;
        this.expedited = expedited;
    }

    public Order getOrder() {
        return order;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public String getShippingZip() {
        return shippingZip;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public boolean getExpedited() {
        return expedited;
    }

    public void validateShippingDetails() {
        if (shippingAddress == null || shippingAddress.isEmpty())
            throw new IllegalArgumentException("Invalid shipping address");
        if (shippingCity == null || shippingCity.isEmpty())
            throw new IllegalArgumentException("Invalid shipping city");
        if (shippingZip == null || shippingZip.isEmpty())
            throw new IllegalArgumentException("Invalid shipping zip");
        if (shippingCountry == null || shippingCountry.isEmpty())
            throw new IllegalArgumentException("Invalid shipping country");
    }

    public double calculateShippingCost() {
        double cost = 10.0;
        if (expedited)
            cost += 20.0;
        if (!"USA".equalsIgnoreCase(shippingCountry))
            cost += 30.0;
        if (shippingZip.length() >= 5)
            cost += 5.0;
        return cost;
    }

    public String generateTrackingNumber() {
        String prefix = expedited ? "EXP" : "STD";
        String code = shippingCountry.substring(0, 2).toUpperCase() + "-" +
                shippingZip.replaceAll("\\D", "").substring(0, Math.min(4, shippingZip.length()));
        return prefix + "-" + code + "-" + System.currentTimeMillis() % 10000;
    }
}

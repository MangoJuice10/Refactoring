package refactoring;

import java.util.Map;

public class OrderService {
    public void processOrder(Order order) {
        double total = order.getTotalPrice();
        Customer customer = order.getCustomer();
        if (customer.getBalance() >= total) {
            processCreditPayment(order);
            for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
                entry.getKey().reduceStock(entry.getValue());
            }
        } else {
            throw new IllegalStateException("Insufficient funds");
        }
    }

    public double calculateTotalWithDiscount(Order order, double discountRate) {
        return order.getTotalPrice() * (1 - discountRate);
    }

    public double applyDiscount(Order order, double discountRate) {
        return order.getTotalPrice() * discountRate;
    }

    public void processCashPayment(Order order) {
        System.out.println("Success! The order has been paid for with cash.");
        order.markAsPaid();
    }

    public void processCreditPayment(Order order) {
        double total = order.getTotalPrice();
        order.getCustomer().deductBalance(total);
        order.markAsPaid();
    }

    public String prepareCustomerSummary(Customer customer, double orderTotal) {
        String name = customer.getName();
        String email = customer.getEmail();
        String address = customer.getAddress();
        double balance = customer.getBalance();

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Invalid address");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
        // Create a useful summary string using all fields
        return "Customer Summary:\nName: " + name + "\nEmail: " + email + "\nAddress: " + address + "\nBalance: $"
                + balance + "\nOrder Total: $" + orderTotal;
    }

    // Refactoring candidate: Introduce Parameter Object (Метод принимает слишком
    // много логически связанных между собой параметров, для которых затем
    // вызываются связанные с ними методы; нужно выделить параметры и методы в класс
    // ShippingDetails)
    public void shipOrder(Order order, ShippingDetails shippingDetails) {
        double shippingCost = shippingDetails.getShippingCost();
        String shippingAddress = shippingDetails.getShippingAddress();
        String shippingCity = shippingDetails.getShippingCity();
        String shippingZip = shippingDetails.getShippingZip();
        String shippingCountry = shippingDetails.getShippingCountry();
        boolean expedited = shippingDetails.getExpedited();

        // Shipping logic — uses pre-calculated cost
        System.out.println(
                "Shipping to: " + shippingAddress + ", " + shippingCity + " " + shippingZip + ", " + shippingCountry);
        if (expedited) {
            System.out.println("Expedited shipping");
        }
        System.out.println("Shipping cost: $" + shippingCost);
        String trackingNumber = shippingDetails.generateTrackingNumber();
        System.out.println("Tracking number: " + trackingNumber);
    }

    public double calculateShippingCost(ShippingDetails shippingDetails) {
        String shippingZip = shippingDetails.getShippingZip();
        String shippingCountry = shippingDetails.getShippingCountry();
        boolean expedited = shippingDetails.getExpedited();

        double cost = 10.0;
        if (expedited)
            cost += 20.0;
        if (!"USA".equalsIgnoreCase(shippingCountry))
            cost += 30.0;
        if (shippingZip.length() >= 5)
            cost += 5.0;
        return cost;
    }
    
    public void addShippingCostToOrder(ShippingDetails shippingDetails) {
        Order order = shippingDetails.getOrder();
        shippingDetails.validateShippingDetails();
        double cost = shippingDetails.calculateShippingCost();
        order.addToTotal(cost);
    }

    public void completeOrderProcess(Order order, ShippingDetails shippingDetails) {

        // 1. Calculate and add shipping cost ONCE
        double shippingCost = calculateShippingCost(shippingDetails);
        addShippingCostToOrder(shippingDetails);

        // 2. Process order (deducts total including shipping)
        processOrder(order);

        // 3. Apply discounts (for reporting)
        double discountedTotal = calculateTotalWithDiscount(order, 0.1);
        double lowDiscount = applyDiscount(order, 0.80);
        double mediumDiscount = applyDiscount(order, 0.85);
        double highDiscount = applyDiscount(order, 0.95);

        // 4. Prepare summary
        String summary = prepareCustomerSummary(order.getCustomer(), order.getTotalPrice());

        // 5. Print report
        StringBuilder report = new StringBuilder();
        report.append(summary).append("\n");
        report.append("Standard Discounted Total: $").append(String.format("%.2f", discountedTotal)).append("\n");
        report.append("Shipping Cost: $").append(String.format("%.2f", shippingCost)).append("\n");
        report.append("Possible Discounts:\n");
        report.append("  Low: $").append(String.format("%.2f", lowDiscount)).append("\n");
        report.append("  Medium: $").append(String.format("%.2f", mediumDiscount)).append("\n");
        report.append("  High: $").append(String.format("%.2f", highDiscount)).append("\n");
        System.out.println(report.toString());

        // 6. Ship — pass pre-calculated cost
        shipOrder(order, shippingDetails);
    }
}
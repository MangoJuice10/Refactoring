package refactoring;

import java.util.Map;

public class OrderService {
    public void processOrder(Order order, String paymentType) {
        double total = order.getTotalPrice();
        Customer customer = order.getCustomer();
        if (customer.getBalance() >= total) {
            processPayment(order, paymentType);
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

    // Refactoring candidate: Parametrize Method (Методы выполняют одну и ту же
    // операцию с разными значениями процента скидки; стоит заменить их одним
    // методом, принимающим процент скидки в качестве параметра)
    public double applyLowDiscount(Order order) {
        return order.getTotalPrice() * 0.95;
    }

    public double applyMediumDiscount(Order order) {
        return order.getTotalPrice() * 0.85;
    }

    public double applyHighDiscount(Order order) {
        return order.getTotalPrice() * 0.80;
    }

    // Refactoring candidate: Replace Parameter with Explicit Methods (Параметр
    // определяет поведение метода, при этом предполагается дальнейшая модификация
    // метода или добавка новых способов оплаты; стоит заменить метод двумя
    // отдельными методами applyCashPayment() и applyCreditPayment())
    public void processPayment(Order order, String paymentType) {
        if ("cash".equals(paymentType)) {
            // Cash dummy logic
            System.out.println("Success! The order has been paid for with cash.");
        } else if ("credit".equals(paymentType)) {
            // Credit logic
            double total = order.getTotalPrice();
            order.getCustomer().deductBalance(total);
        } else {
            throw new IllegalArgumentException("Unknown payment type");
        }
        order.markAsPaid();
    }

    // Refactoring candidate: Preserve Whole Object (Вызывающий метод код сначала
    // получает отдельные поля объекта класса Customer, и после этого передаёт их в
    // метод; следует вместо этого передавать методы сам объект)
    public String prepareCustomerSummary(String name, String email, String address, double balance, double orderTotal) {
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
    public void shipOrder(Order order, double shippingCost, String shippingAddress, String shippingCity,
            String shippingZip, String shippingCountry, boolean expedited) {
        // Shipping logic — uses pre-calculated cost
        System.out.println("Shipping to: " + shippingAddress + ", " + shippingCity + " " + shippingZip + ", " + shippingCountry);
        if (expedited) {
            System.out.println("Expedited shipping");
        }
        System.out.println("Shipping cost: $" + shippingCost);
        String trackingNumber = generateTrackingNumber(shippingAddress, shippingCity, shippingZip, shippingCountry, expedited);
        System.out.println("Tracking number: " + trackingNumber);
    }

    // Refactoring candidate: Remove Parameter (Метод принимает параметр expedited,
    // но никак его не использует. В будущем может была запланирована валидация в
    // том числе этого параметра, но на данный момент она не предусмотрена. Стоит
    // избавиться от этого параметра)
    private void validateShippingDetails(String shippingAddress, String shippingCity, String shippingZip,
            String shippingCountry, boolean expedited) {
        if (shippingAddress == null || shippingAddress.isEmpty()) throw new IllegalArgumentException("Invalid shipping address");
        if (shippingCity == null || shippingCity.isEmpty()) throw new IllegalArgumentException("Invalid shipping city");
        if (shippingZip == null || shippingZip.isEmpty()) throw new IllegalArgumentException("Invalid shipping zip");
        if (shippingCountry == null || shippingCountry.isEmpty()) throw new IllegalArgumentException("Invalid shipping country");
    }

    // Refactoring candidate: Separate Query from Modifier (Метод не только
    // вычисляет стоимость доставки, но и добавляет её к общей стоимости заказа)
    public double calculateShippingCostAndAddToOrder(Order order, String shippingAddress, String shippingCity,
            String shippingZip, String shippingCountry, boolean expedited) {
        validateShippingDetails(shippingAddress, shippingCity, shippingZip, shippingCountry, expedited);
        double cost = 10.0;
        if (expedited) cost += 20.0;
        if (!"USA".equalsIgnoreCase(shippingCountry)) cost += 30.0;
        if (shippingZip.length() >= 5) cost += 5.0;
        order.addToTotal(cost);
        return cost;
    }

    private String generateTrackingNumber(String shippingAddress, String shippingCity, String shippingZip,
            String shippingCountry, boolean expedited) {
        String prefix = expedited ? "EXP" : "STD";
        String code = shippingCountry.substring(0, 2).toUpperCase() + "-" +
                shippingZip.replaceAll("\\D", "").substring(0, Math.min(4, shippingZip.length()));
        return prefix + "-" + code + "-" + System.currentTimeMillis() % 10000;
    }

    public void completeOrderProcess(Order order, String paymentType, String shippingAddress, String shippingCity,
            String shippingZip, String shippingCountry, boolean expedited) {

        // 1. Calculate and add shipping cost ONCE
        double shippingCost = calculateShippingCostAndAddToOrder(order, shippingAddress, shippingCity,
                shippingZip, shippingCountry, expedited);

        // 2. Process order (deducts total including shipping)
        processOrder(order, paymentType);

        // 3. Apply discounts (for reporting)
        double discountedTotal = calculateTotalWithDiscount(order, 0.1);
        double lowDiscount = applyLowDiscount(order);
        double mediumDiscount = applyMediumDiscount(order);
        double highDiscount = applyHighDiscount(order);

        // 4. Prepare summary
        String summary = prepareCustomerSummary(order.getCustomer().getName(), order.getCustomer().getEmail(),
                order.getCustomer().getAddress(), order.getCustomer().getBalance(), order.getTotalPrice());

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
        shipOrder(order, shippingCost, shippingAddress, shippingCity, shippingZip, shippingCountry, expedited);
    }
}
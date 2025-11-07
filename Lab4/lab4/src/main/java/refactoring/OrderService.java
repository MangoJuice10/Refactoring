package refactoring;

import java.util.Map;

public class OrderService {
    public void processOrder(Order order) {
        double total = order.getTotalPrice();
        Customer customer = order.getCustomer();
        if (customer.getBalance() >= total) {
            customer.deductBalance(total);
            for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
                entry.getKey().reduceStock(entry.getValue());
            }
            order.markAsPaid();
        } else {
            throw new IllegalStateException("Insufficient funds");
        }
    }

    public double calculateTotalWithDiscount(Order order, double discountRate) {
        return order.getTotalPrice() * (1 - discountRate);
    }

    // Refactoring candidate: Separate Query from Modifier (Метод не только
    // проверяет, может ли заказ быть обработан, но и обрабатывает его, изменяя
    // остаток продукта и баланс пользователя)
    public boolean checkAndProcessOrder(Order order) {
        double total = order.getTotalPrice();
        Customer customer = order.getCustomer();
        if (customer.getBalance() >= total) {
            customer.deductBalance(total);
            for (Map.Entry<Product, Integer> entry : order.getItems().entrySet()) {
                entry.getKey().reduceStock(entry.getValue());
            }
            order.markAsPaid();
            return true;
        }
        return false;
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
            // Cash logic
            order.markAsPaid();
        } else if ("credit".equals(paymentType)) {
            // Credit logic
            double total = order.getTotalPrice();
            order.getCustomer().deductBalance(total);
            order.markAsPaid();
        } else {
            throw new IllegalArgumentException("Unknown payment type");
        }
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
    public void shipOrder(Order order, String shippingAddress, String shippingCity, String shippingZip,
            String shippingCountry, boolean expedited) {
        validateShippingDetails(shippingAddress, shippingCity, shippingZip, shippingCountry, expedited);
        double cost = calculateShippingCost(shippingAddress, shippingCity, shippingZip, shippingCountry, expedited);
        // Shipping logic...
        System.out.println(
                "Shipping to: " + shippingAddress + ", " + shippingCity + " " + shippingZip + ", " + shippingCountry);
        if (expedited) {
            System.out.println("Expedited shipping");
        }
        System.out.println("Shipping cost: $" + cost);
        // Additional logic: simulate tracking number generation
        String trackingNumber = generateTrackingNumber(shippingAddress, shippingCity, shippingZip, shippingCountry,
                expedited);
        System.out.println("Tracking number: " + trackingNumber);
    }

    // Refactoring candidate: Remove Parameter (Метод принимает параметр expedited,
    // но никак его не использует. В будущем может была запланирована валидация в
    // том числе этого параметра, но на данный момент она не предусмотрена. Стоит
    // избавиться от этого параметра)
    private void validateShippingDetails(String shippingAddress, String shippingCity, String shippingZip,
            String shippingCountry, boolean expedited) {
        if (shippingAddress == null || shippingAddress.isEmpty()) {
            throw new IllegalArgumentException("Invalid shipping address");
        }
        if (shippingCity == null || shippingCity.isEmpty()) {
            throw new IllegalArgumentException("Invalid shipping city");
        }
        if (shippingZip == null || shippingZip.isEmpty()) {
            throw new IllegalArgumentException("Invalid shipping zip");
        }
        if (shippingCountry == null || shippingCountry.isEmpty()) {
            throw new IllegalArgumentException("Invalid shipping country");
        }
        // No specific validation for expedited, but included in param list
    }

    private double calculateShippingCost(String shippingAddress, String shippingCity, String shippingZip,
            String shippingCountry, boolean expedited) {
        double cost = 10.0; // Base cost
        if (expedited) {
            cost += 20.0;
        }
        if (!"USA".equalsIgnoreCase(shippingCountry)) {
            cost += 30.0;
        }
        // Dummy logic based on zip code length
        if (shippingZip.length() > 5) {
            cost += 5.0;
        }
        return cost;
    }

    private String generateTrackingNumber(String shippingAddress, String shippingCity, String shippingZip,
            String shippingCountry, boolean expedited) {
        // Simple dummy tracking number generation
        String prefix = expedited ? "EXP" : "STD";
        String code = shippingCountry.substring(0, 2).toUpperCase() + "-" + shippingZip.replaceAll("\\D", "")
                .substring(0, Math.min(4, shippingZip.replaceAll("\\D", "").length()));
        return prefix + "-" + code + "-" + System.currentTimeMillis() % 10000;
    }

    // Utility method to tie things together
    public void completeOrderProcess(Order order, String paymentType, String shippingAddress, String shippingCity,
            String shippingZip, String shippingCountry, boolean expedited) {
        processOrder(order);
        double discountedTotal = calculateTotalWithDiscount(order, 0.1);
        boolean processed = checkAndProcessOrder(order);
        double lowDiscount = applyLowDiscount(order);
        double mediumDiscount = applyMediumDiscount(order);
        double highDiscount = applyHighDiscount(order);
        processPayment(order, paymentType);
        String summary = prepareCustomerSummary(order.getCustomer().getName(), order.getCustomer().getEmail(),
                order.getCustomer().getAddress(), order.getCustomer().getBalance(), order.getTotalPrice());
        StringBuilder report = new StringBuilder();
        report.append(summary).append("\n");
        report.append("Standard Discounted Total: $").append(discountedTotal).append("\n");
        report.append("Processing Status: ").append(processed ? "Success" : "Failure").append("\n");
        report.append("Possible Discount Levels:\n");
        report.append("  Low: $").append(lowDiscount).append("\n");
        report.append("  Medium: $").append(mediumDiscount).append("\n");
        report.append("  High: $").append(highDiscount).append("\n");
        System.out.println(report.toString());
        if (processed) {
            shipOrder(order, shippingAddress, shippingCity, shippingZip, shippingCountry, expedited);
        }
    }
}
package refactoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private Product product;
    private Customer customer;
    private Order order;
    private ShippingDetails shippingDetails;
    private OrderService service;

    @BeforeEach
    void setUp() {
        product = new Product("Laptop", 1000.0, 10);
        customer = new Customer("John Doe", "john@example.com", "123 Main St", 3000.0);
        order = new Order(customer);
        order.addItem(product, 1);
        service = new OrderService();
    }

    @Test
    void testPrOrd() {
        service.processOrder(order);
        assertTrue(order.isPaid());
        assertEquals(1000.0, order.getTotalPrice());
        assertEquals(2000.0, customer.getBalance());
        assertEquals(9, product.getStock());
    }

    @Test
    void testCalculateTotalWithDiscount() {
        double discounted = service.calculateTotalWithDiscount(order, 0.1);
        assertEquals(900.0, discounted);
    }

    @Test
    void testCalculateShippingCostAndAddToOrder() {
        shippingDetails = new ShippingDetails(order, "123 Main St", "City", "12345", "USA", true);
        double cost = service.calculateShippingCost(shippingDetails);
        service.addShippingCostToOrder(shippingDetails);
        assertEquals(35.0, cost, 0.001);
        assertEquals(1035.0, order.getTotalPrice());
    }

    @Test
    void testShipOrder_UsesPreCalculatedCost_AndPrintsCorrectly() {
        shippingDetails = new ShippingDetails(order, "123 Main St", "New York", "10001", "USA", true);
        double shippingCost = service.calculateShippingCost(
                shippingDetails);
        service.addShippingCostToOrder(shippingDetails);

        assertEquals(35.0, shippingCost, 0.001);
    }

    @Test
    void testApplyLowDiscount() {
        double discounted = service.applyDiscount(order, 0.95);
        assertEquals(950.0, discounted);
    }

    @Test
    void testApplyMediumDiscount() {
        double discounted = service.applyDiscount(order, 0.85);
        assertEquals(850.0, discounted);
    }

    @Test
    void testApplyHighDiscount() {
        double discounted = service.applyDiscount(order, 0.80);
        assertEquals(800.0, discounted);
    }

    @Test
    void testProcessPaymentCash() {
        service.processCashPayment(order);
        assertTrue(order.isPaid());
        assertEquals(3000.0, customer.getBalance());
    }

    @Test
    void testProcessPaymentCredit() {
        service.processCreditPayment(order);
        assertTrue(order.isPaid());
        assertEquals(2000.0, customer.getBalance());
    }

    @Test
    void testPrepareCustomerSummary() {
        String summary = service.prepareCustomerSummary(customer, order.getTotalPrice());
        assertTrue(summary.contains("John Doe"));
        assertTrue(summary.contains("john@example.com"));
        assertTrue(summary.contains("123 Main St"));
        assertTrue(summary.contains("3000.0"));
        assertTrue(summary.contains("1000.0"));
    }

    @Test
    void testCompleteOrderProcess() {
        shippingDetails = new ShippingDetails(order, "123 Main St", "City", "12345", "USA", true);
        service.completeOrderProcess(order, shippingDetails);

        // Total: 1000 + 35 = 1035
        // 3000 - 1035 = 1965
        assertEquals(1965, customer.getBalance());
        assertTrue(order.isPaid());
        assertEquals(1035.0, order.getTotalPrice());
        assertEquals(9, product.getStock());
    }
}
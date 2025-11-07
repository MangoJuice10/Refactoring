// src/test/java/refactoring/OrderServiceTest.java
package refactoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    private Product product;
    private Customer customer;
    private Order order;
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
        service.prOrd(order);
        assertTrue(order.isPaid());
        assertEquals(1000.0, order.getTotalPrice());
        assertEquals(2000.0, customer.getBalance());
        assertEquals(9, product.getStock());
    }

    @Test
    void testCalculateTotalWithDiscount() {
        double discounted = service.calculateTotalWithDiscount(order);
        assertEquals(900.0, discounted);
    }

    @Test
    void testCheckAndProcessOrder() {
        boolean result = service.checkAndProcessOrder(order);
        assertTrue(result);
        assertTrue(order.isPaid());
        assertEquals(2000.0, customer.getBalance());
        assertEquals(9, product.getStock());
    }

    @Test
    void testApplyLowDiscount() {
        double discounted = service.applyLowDiscount(order);
        assertEquals(950.0, discounted);
    }

    @Test
    void testApplyMediumDiscount() {
        double discounted = service.applyMediumDiscount(order);
        assertEquals(850.0, discounted);
    }

    @Test
    void testApplyHighDiscount() {
        double discounted = service.applyHighDiscount(order);
        assertEquals(800.0, discounted);
    }

    @Test
    void testProcessPaymentCash() {
        service.processPayment(order, "cash");
        assertTrue(order.isPaid());
        assertEquals(3000.0, customer.getBalance()); // Balance not deducted for cash
    }

    @Test
    void testProcessPaymentCredit() {
        service.processPayment(order, "credit");
        assertTrue(order.isPaid());
        assertEquals(2000.0, customer.getBalance());
    }

    @Test
    void testPrepareCustomerSummary() {
        String summary = service.prepareCustomerSummary(customer.getName(), customer.getEmail(), customer.getAddress(), customer.getBalance(), order.getTotalPrice());
        assertTrue(summary.contains("John Doe"));
        assertTrue(summary.contains("john@example.com"));
        assertTrue(summary.contains("123 Main St"));
        assertTrue(summary.contains("3000.0"));
        assertTrue(summary.contains("1000.0"));
    }

    @Test
    void testShipOrder() {
        assertDoesNotThrow(() -> service.shipOrder(order, "123 Main St", "City", "12345", "USA", true));
    }

    @Test
    void testCompleteOrderProcess() {
        assertDoesNotThrow(() -> service.completeOrderProcess(order, "credit", "123 Main St", "City", "12345", "USA", true));
        assertTrue(order.isPaid());
        assertEquals(0.0, customer.getBalance()); // Multiple deductions due to multiple calls, but tests the flow
        assertEquals(8, product.getStock()); // Multiple reductions
    }
}
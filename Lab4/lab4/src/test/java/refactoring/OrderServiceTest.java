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
        service.processOrder(order, "credit");
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
        double cost = service.calculateShippingCostAndAddToOrder(order, "123 Main St", "City", "12345", "USA", true);
        assertEquals(35.0, cost, 0.001);
        assertEquals(1035.0, order.getTotalPrice());
    }

    @Test
    void testShipOrder_UsesPreCalculatedCost_AndPrintsCorrectly() {
        String shippingAddress = "123 Main St";
        String shippingCity = "New York";
        String shippingZip = "10001";
        String shippingCountry = "USA";
        boolean expedited = true;

        double shippingCost = service.calculateShippingCostAndAddToOrder(
                order, shippingAddress, shippingCity, shippingZip, shippingCountry, expedited);

        assertEquals(35.0, shippingCost, 0.001);
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
        assertEquals(3000.0, customer.getBalance());
    }

    @Test
    void testProcessPaymentCredit() {
        service.processPayment(order, "credit");
        assertTrue(order.isPaid());
        assertEquals(2000.0, customer.getBalance());
    }

    @Test
    void testPrepareCustomerSummary() {
        String summary = service.prepareCustomerSummary(customer.getName(), customer.getEmail(), customer.getAddress(),
                customer.getBalance(), order.getTotalPrice());
        assertTrue(summary.contains("John Doe"));
        assertTrue(summary.contains("john@example.com"));
        assertTrue(summary.contains("123 Main St"));
        assertTrue(summary.contains("3000.0"));
        assertTrue(summary.contains("1000.0"));
    }

    @Test
    void testCompleteOrderProcess() {
        service.completeOrderProcess(order, "credit", "123 Main St", "City", "12345", "USA", true);

        // Total: 1000 + 35 = 1035
        // 3000 - 1035 = 1965
        assertEquals(1965, customer.getBalance());
        assertTrue(order.isPaid());
        assertEquals(1035.0, order.getTotalPrice());
        assertEquals(9, product.getStock());
    }
}
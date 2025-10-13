package refactoring;

import org.junit.jupiter.api.Test;
import refactoring.model.Order;
import refactoring.service.OrderService;
import refactoring.util.TaxUtil;
import refactoring.util.DiscountUtil;
import refactoring.util.ShippingUtil;
import refactoring.helper.InlineHelper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RefactoringTests {

    @Test
    void testDiscountUtil() {
        DiscountUtil du = new DiscountUtil();
        Order small = new Order("A", 1, 10.0, 1.0); // price 10 -> discount 0
        Order medium = new Order("B", 5, 12.0, 2.0); // price 60 -> 5% -> 3.0
        Order big = new Order("C", 10, 20.0, 5.0); // price 200 -> 10% -> 20.0

        assertEquals(0.0, du.computeDiscount(small), 1e-9);
        assertEquals(3.0, du.computeDiscount(medium), 1e-9);
        assertEquals(20.0, du.computeDiscount(big), 1e-9);
    }

    @Test
    void testShippingUtil() {
        ShippingUtil su = new ShippingUtil();
        Order o1 = new Order("S1", 1, 10.0, 3.0); // price 10 -> shipping = 5 + 2*3 = 11
        Order o2 = new Order("S2", 20, 11.0, 5.0); // price 220 -> free shipping 0
        assertEquals(11.0, su.shippingForOrder(o1), 1e-9);
        assertEquals(0.0, su.shippingForOrder(o2), 1e-9);
    }

    @Test
    void testTaxUtil_assignsParameterBehavior() {
        TaxUtil tu = new TaxUtil();
        // computeTax adds 1.0 to amount; this is part of current behavior and must be preserved by tests
        double result = tu.computeTax(99.0, 0.1); // (99+1)*0.1 = 10.0
        assertEquals(10.0, result, 1e-9);

        // negative rate becomes 0
        assertEquals(0.0, tu.computeTax(10.0, -0.5), 1e-9);
    }

    @Test
    void testOrderService_calculateInvoiceTotal() {
        OrderService service = new OrderService();
        Order o1 = new Order("O1", 2, 30.0, 2.0); // price 60 -> discount 3 (5%)
        Order o2 = new Order("O2", 5, 50.0, 10.0); // price 250 -> discount 25 (10%), free shipping
        List<Order> orders = List.of(o1, o2);

        // manual compute:
        double subtotal = 60 + 250; // 310
        double discounts = 3 + 25; // 28
        // shipping: o1 -> 5 + 2*2 =9 ; o2 -> free 0 => 9
        double shipping = 9.0;
        // taxable = subtotal - discounts = 282
        // tax util adds +1 to taxable: taxable+1 = 283; tax rate 0.1 => 28.3
        double tax = 283 * 0.1;
        double expected = subtotal - discounts + shipping + tax; // 310 - 28 + 9 + 28.3 = 319.3

        assertEquals(expected, service.calculateInvoiceTotal(orders), 1e-9);
    }

    @Test
    void testInlineHelper_banner() {
        InlineHelper ih = new InlineHelper();
        assertEquals("=== ORDER INVOICE ===", ih.banner());
    }
}
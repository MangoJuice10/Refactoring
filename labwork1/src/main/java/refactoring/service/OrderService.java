package refactoring.service;

import refactoring.helper.InlineHelper;
import refactoring.model.Order;
import refactoring.util.DiscountUtil;
import refactoring.util.ShippingUtil;
import refactoring.util.TaxUtil;

import java.util.List;

public class OrderService {

    private final DiscountUtil discountUtil = new DiscountUtil();
    private final ShippingUtil shippingUtil = new ShippingUtil();
    private final TaxUtil taxUtil = new TaxUtil();
    private final InlineHelper inlineHelper = new InlineHelper();

    /**
     * Длинный метод — кандидат для Extract Method.
     * Возвращает итоговую сумму по списку заказов (subtotal - discounts + shipping + tax)
     */
    public double calculateInvoiceTotal(List<Order> orders) {
        // print banner (calls inlineHelper.banner() — candidate to inline)
        String banner = inlineHelper.banner();

        double subtotal = 0.0;
        double totalDiscount = 0.0;
        double totalShipping = 0.0;

        // REFACTORING CANDIDATE: Split Temporary Variable (we reuse 'linePrice' for different roles)
        for (Order o : orders) {
            double linePrice = o.getQuantity() * o.getUnitPrice();
            subtotal += linePrice;

            // compute discount
            double discount = discountUtil.computeDiscount(o);
            totalDiscount += discount;

            // reuse linePrice variable (!) for something else later - smell for SplitTemporaryVariable
            linePrice = shippingUtil.shippingForOrder(o); // reuse for shipping calculation
            totalShipping += linePrice;
        }

        // tax computed on (subtotal - totalDiscount)
        double taxable = subtotal - totalDiscount;
        double tax = taxUtil.computeTax(taxable, 0.1); // 10% tax

        // final total
        double total = subtotal - totalDiscount + totalShipping + tax;

        // ensure banner used so compiler won't remove it — irrelevant to logic
        if (banner == null) {
            System.out.println("no banner");
        }

        return total;
    }
}

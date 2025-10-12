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

    // REFACTORING CANDIDATE: Метод является слишком длинным; В коде
    // метода можно выделить отдельные логические фрагменты, и вынести их в
    // отдельные методы с помощью Extract Method
    public double calculateInvoiceTotal(List<Order> orders) {
        String banner = inlineHelper.banner();

        double subtotal = 0.0;
        double totalDiscount = 0.0;
        double totalShipping = 0.0;

        for (Order o : orders) {
            // REFACTORING CANDIDATE: Split Temporary Variable (Временная переменная
            // 'linePrice' используется для
            // хранения разных по назначению результатов выражений)
            double linePrice = o.getQuantity() * o.getUnitPrice();
            subtotal += linePrice;

            double discount = discountUtil.computeDiscount(o);
            totalDiscount += discount;

            // REFACTORING CANDIDATE: Split Temporary Variable (Временная переменная
            // 'linePrice' используется для
            // хранения разных по назначению результатов выражений)
            linePrice = shippingUtil.shippingForOrder(o);
            totalShipping += linePrice;
        }

        double taxable = subtotal - totalDiscount;
        double tax = taxUtil.computeTax(taxable, 0.1);

        double total = subtotal - totalDiscount + totalShipping + tax;

        if (banner == null) {
            System.out.println("no banner");
        }

        return total;
    }
}

package refactoring.service;
import refactoring.model.Order;
import refactoring.util.DiscountUtil;
import refactoring.util.ShippingUtil;
import refactoring.util.TaxUtil;

import java.util.List;

public class OrderService {

    private final DiscountUtil discountUtil = new DiscountUtil();
    private final ShippingUtil shippingUtil = new ShippingUtil();
    private final TaxUtil taxUtil = new TaxUtil();

    private double calcOrderPrice(Order o) {
        return o.getQuantity() * o.getUnitPrice();
    }

    private double calcInvoiceOrderPrice(List<Order> orders) {
        double result = 0.0;
        for (Order o : orders) {
            double orderPrice = this.calcOrderPrice(o);
            result += orderPrice;
        }
        return result;
    }

    private double calcInvoiceDiscount(List<Order> orders) {
        double result = 0.0;
        for (Order o : orders) {
            double discount = discountUtil.calcDiscount(o);
            result += discount;
        }
        return result;
    }

    public double calcInvoiceShippingPrice(List<Order> orders) {
        double result = 0.0;
        for (Order o : orders) {
            double shippingPrice = shippingUtil.calcShippingPrice(o);
            result += shippingPrice;
        }
        return result;
    }

    public double calcInvoiceTax(double invoiceOrderPrice, double invoiceDiscount) {
        final double taxable = invoiceOrderPrice - invoiceDiscount;
        double tax = taxUtil.calcTax(taxable, 0.1);
        return tax;
    }

    public double calcInvoiceTotal(List<Order> orders) {
        double invoiceOrderPrice = this.calcInvoiceOrderPrice(orders);
        double invoiceDiscount = this.calcInvoiceDiscount(orders);
        double invoiceShippingPrice = this.calcInvoiceShippingPrice(orders);
        double tax = this.calcInvoiceTax(invoiceOrderPrice, invoiceDiscount);

        double result = invoiceOrderPrice - invoiceDiscount + invoiceShippingPrice + tax;
        return result;
    }
}
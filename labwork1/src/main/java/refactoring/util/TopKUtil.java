package refactoring.util;

import refactoring.model.Order;

import java.util.ArrayList;
import java.util.List;

public class TopKUtil {

    // REFACTORING CANDIDATE: Substitute Algorithm
    // naive O(n*k) algorithm to find top-K orders by price
    public List<Order> topKByPrice(List<Order> orders, int k) {
        List<Order> result = new ArrayList<>();
        for (int i = 0; i < Math.min(k, orders.size()); i++) {
            Order best = null;
            for (Order o : orders) {
                if (result.contains(o)) continue;
                if (best == null || (o.getQuantity() * o.getUnitPrice()) > (best.getQuantity() * best.getUnitPrice())) {
                    best = o;
                }
            }
            if (best != null) result.add(best);
        }
        return result;
    }
}

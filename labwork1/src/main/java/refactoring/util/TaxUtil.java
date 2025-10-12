package refactoring.util;

public class TaxUtil {
    public double calcTax(double amount, double rate) {
        double taxBase = amount + 1.0;
        if (rate < 0) {
            rate = 0;
        }
        return taxBase * rate;
    }
}

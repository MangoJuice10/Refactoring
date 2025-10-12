package refactoring.util;

public class TaxUtil {
    // REFACTORING CANDIDATE: Remove Assignments to Parameters (Происходит
    // присвоение параметру amount нового значения)
    public double calcTax(double amount, double rate) {
        // REFACTORING CANDIDATE: Inline Temp (Переменная constant не несёт смысловой
        // нагрузки, поэтому можно встроить её значение в связанное выражение)
        double constant = 1.0;
        amount = amount + constant;
        if (rate < 0) {
            rate = 0;
        }
        return amount * rate;
    }
}

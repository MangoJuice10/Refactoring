package refactoring.util;

public class TaxUtil {
    // REFACTORING CANDIDATE: Remove Assignments to Parameters
    // (this method intentionally assigns to 'amount' parameter to illustrate the smell)
    public double computeTax(double amount, double rate) {
        // bad practice: reassign parameter
        amount = amount + 1.0; // pretend an adjustment to demonstrate the refactoring
        if (rate < 0) {
            rate = 0;
        }
        return amount * rate;
    }
}

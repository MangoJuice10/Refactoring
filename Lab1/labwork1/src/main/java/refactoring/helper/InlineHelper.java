package refactoring.helper;

public class InlineHelper {

    // REFACTORING CANDIDATE: Inline Method — trivial delegating method
    public String banner() {
        return generateBanner();
    }

    private String generateBanner() {
        return "=== ORDER INVOICE ===";
    }
}

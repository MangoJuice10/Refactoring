// src/main/java/com/example/TelephoneNumber.java
package refactoring;

// Small class with little responsibility.
public class TelephoneNumber {
    private String number;

    public TelephoneNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
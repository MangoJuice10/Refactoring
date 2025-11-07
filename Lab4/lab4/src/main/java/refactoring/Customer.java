package refactoring;

public class Customer {
    private String name;
    private String email;
    private String address;
    private double balance;

    public Customer(String name, String email, String address, double balance) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }

    public void deductBalance(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
    }
}
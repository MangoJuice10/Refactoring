package refactoring;

import java.util.Date;

public class User {
    private String name;
    private Department department;
    private Date borrowDate;
    private Account account;
    private String telephoneNumber;

    public User(String name, Department department) {
        this.name = name;
        this.department = department;
        this.account = new Account(0.0);
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Account getAccount() {
        return account;
    }
    
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public double getAccountBalance() {
        return account.getBalance();
    }

    public void borrowBook(Date date) {
        this.borrowDate = date;
    }
}
package refactoring;

import java.util.Date;

public class User {
    private String name;
    private Department department;
    private TelephoneNumber phone;
    private Date borrowDate;

    public User(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public TelephoneNumber getPhone() {
        return phone;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setPhone(TelephoneNumber phone) {
        this.phone = phone;
    }

    public void borrowBook(Date date) {
        this.borrowDate = date;
    }
}
// src/main/java/com/example/User.java
package refactoring;

import java.util.Date;

public class User {
    private String name;
    private Department department;
    private TelephoneNumber phone;
    private Date borrowDate;
    private double fineRate = 0.5;

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

    public void setPhone(TelephoneNumber phone) {
        this.phone = phone;
    }

    public void borrowBook(Date date) {
        this.borrowDate = date;
    }

    // Calculates fine based on days overdue.
    public double calculateFine() {
        if (borrowDate == null) return 0.0;
        long daysOverdue = (new Date().getTime() - borrowDate.getTime()) / (1000 * 60 * 60 * 24) - 14; // 2 weeks grace
        return daysOverdue > 0 ? daysOverdue * fineRate : 0.0;
    }
}
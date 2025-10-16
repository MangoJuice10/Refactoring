package refactoring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

public class LibrarySystem {
    private FineService fineService;
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    
    public LibrarySystem() {
        this.fineService = new FineService(0.5);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public double getFineRate() {
        return fineService.getFineRate();
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }
    
    public double calculateTotalFines() {
        return fineService.calculateTotalFines(users);
    }

    public String formatBookDetails(Book book) {
        return "Title: " + book.getTitle() + ", Author: " + book.getAuthorName() + " " + book.getAuthorSurname() +
                ", Birth Year: " + book.getAuthorBirthYear();
    }

    public void updateUserPhone(User user, String number) {
        user.setTelephoneNumber(number);;
    }

    public double getUserBalance(User user) {
        return user.getAccountBalance();
    }

    public Date getNextWeek(Date date) {
        return new Date(date.getTime() + 86400000 * 7);
    }

    public Date getDueDate(Date borrowDate) {
        return getNextWeek(borrowDate);
    }

    public String fetchUserManagerName(User user) {
        return user.getDepartment().getManager().getName();
    }

    public String fetchUserManagerEmail(User user) {
        return user.getDepartment().getManager().getEmail();
    }

    public String fetchUserManagerTitle(User user) {
        return user.getDepartment().getManager().getTitle();
    }

    public String fetchUserManagerOffice(User user) {
        return user.getDepartment().getManager().getOffice();
    }

    public String fetchUserManagerPhone(User user) {
        return user.getDepartment().getManager().getPhone();
    }

    public int fetchUserManagerId(User user) {
        return user.getDepartment().getManager().getId();
    }

    public String fetchUserManagerUpperName(User user) {
        return user.getDepartment().getManager().getUpperCaseName();
    }

    public String fetchUserManagerInitials(User user) {
        return user.getDepartment().getManager().getInitials();
    }

    public String fetchUserManagerGreeting(User user) {
        return user.getDepartment().getManager().getGreeting();
    }

    public LocalDate calculateReservationEnd(LocalDate reservationStart) {
        LocalDateExtended reservationStartExtended = new LocalDateExtended(reservationStart);
        return reservationStartExtended.addBusinessDays(5);
    }

    public boolean isReservationOverdue(LocalDate reservationStart, LocalDate today) {
        LocalDate due = calculateReservationEnd(reservationStart);
        return today.isAfter(due);
    }

    public String getReservationStatus(LocalDate reservationStart, LocalDate today) {
        LocalDateExtended dueExtended = new LocalDateExtended(calculateReservationEnd(reservationStart));
        long daysLeft = LocalDateExtended.daysBetween(today, dueExtended.getLocalDate());
        if (daysLeft < 0) {
            return "Reservation overdue by " + (-daysLeft) + " day(s).";
        } else {
            return "Reservation valid for " + daysLeft + " more day(s), until " + dueExtended.formatLocalDateShort();
        }
    }

    public LocalDate getNextIssueDate(LocalDate today) {
        LocalDateExtended todayExtended = new LocalDateExtended(today);
        return todayExtended.nextBusinessDay();
    }

    public String getSystemDateInfo(LocalDate today) {
        LocalDateExtended todayExtended = new LocalDateExtended(today);
        return "[System Date Info] " + todayExtended.verboseLocalDateInfo();
    }
}
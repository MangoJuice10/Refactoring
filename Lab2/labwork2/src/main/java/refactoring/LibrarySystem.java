package refactoring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LibrarySystem {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private FineService fineService;

    public LibrarySystem(double fineRate) {
        this.fineService = new FineService(fineRate);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
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

    // REFACTORING: Extract Class (Класс Book содержит поля одновременно двух
    // сущностей: Книга и Автор, поэтому после выделения класса необходимо будет
    // изменить также обращения к исходному классу, связанные с полями и методами
    // нового класса)
    public String formatBookDetails(Book book) {
        return "Title: " + book.getTitle() + ", Author: " + book.getAuthorName() + " " + book.getAuthorSurname() +
                ", Birth Year: " + book.getAuthorBirthYear();
    }

    // REFACTORING: Inline Class (Класс TelephoneNumber имеет слишком мало
    // обязанностей, и в будущем не предполагается добавлять для него новые
    // обязанности, поэтому следует произвести встраивание класса TelephoneNumber в
    // класс User)
    public void updateUserPhone(User user, String number) {
        user.setPhone(new TelephoneNumber(number));
    }

    // REFACTORING: Hide Delegate (Класс LibrarySystem обращается к делегату Person
    // не напрямую, а через сервер Department, поэтому следует добавить в класс
    // Department делегирующий метод getManagerName())
    public String getUserManagerName(User user) {
        return user.getDepartment().getManager().getName();
    }

    // REFACTORING: Introduce Foreign Method (Класс Date библиотеки java.util не
    // предоставляет метода для получения даты по прошествии одной недели, поэтому
    // имеет смысл написать внешний метод getNextWeek(Date date), принимающий на
    // вход объект класса Date, и возвращающий нужную дату)
    public Date getDueDate(Date borrowDate) {
        // Дата возврата книги определяется как неделя спустя момента получения книги
        Date dueDate = new Date(borrowDate.getTime() + 86400000 * 7); // 24*60*60*1000
        return dueDate;
    }

    // REFACTORING: Remove Middle Man (Класс LibrarySystem обращается к делегату
    // Person через множество делегирующих методов сервера Department: под каждый
    // запрос клиента приходится создавать в классе Department отдельный
    // делегирующий метод, что превращает класс Department в "транзитный" класс.
    // Необходимо заменить делегирование прямым обращением к объекту клиента Person)
    public String fetchUserManagerName(User user) {
        return user.getDepartment().getManagerName();
    }

    public String fetchUserManagerEmail(User user) {
        return user.getDepartment().getManagerEmail();
    }

    public String fetchUserManagerTitle(User user) {
        return user.getDepartment().getManagerTitle();
    }

    public String fetchUserManagerOffice(User user) {
        return user.getDepartment().getManagerOffice();
    }

    public String fetchUserManagerPhone(User user) {
        return user.getDepartment().getManagerPhone();
    }

    public int fetchUserManagerId(User user) {
        return user.getDepartment().getManagerId();
    }

    public String fetchUserManagerUpperName(User user) {
        return user.getDepartment().getManagerUpperCaseName();
    }

    public String fetchUserManagerInitials(User user) {
        return user.getDepartment().getManagerInitials();
    }

    public String fetchUserManagerGreeting(User user) {
        return user.getDepartment().getManagerGreeting();
    }

    // REFACTORING: Introduce Local Extension (Класс LocalDate библиотеки java.time
    // не предоставляет множества методов, нужных классу LibrarySystem, поэтому
    // имеет смысл создать локальное расширение LocalDateExtended, представляющее из
    // себя подкласс класса LocalDate либо обёртку над ним.

    public String formatLocalDateShort(LocalDate date) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(fmt);
    }

    public long daysBetween(LocalDate from, LocalDate to) {
        return ChronoUnit.DAYS.between(from, to);
    }

    public LocalDate addBusinessDays(LocalDate date, int days) {
        LocalDate res = date;
        int added = 0;
        while (added < days) {
            res = res.plusDays(1);
            if (!(res.getDayOfWeek().getValue() == 6 || res.getDayOfWeek().getValue() == 7)) {
                added++;
            }
        }
        return res;
    }

    public LocalDate nextBusinessDay(LocalDate date) {
        LocalDate d = date.plusDays(1);
        while (d.getDayOfWeek().getValue() == 6 || d.getDayOfWeek().getValue() == 7) {
            d = d.plusDays(1);
        }
        return d;
    }

    public String verboseLocalDateInfo(LocalDate date) {
        return "Date: " + formatLocalDateShort(date) + ", DayOfYear: " + date.getDayOfYear() + ", Leap: "
                + date.isLeapYear();
    }

    // REFACTORING: Move Field (LibrarySystem никак не использует поле; поле
    // логически должно находиться в классе Book)
    private int publicationYear = 2020; // Default year, but should be per book.

    public int getPublicationYear() {
        return publicationYear;
    }
}
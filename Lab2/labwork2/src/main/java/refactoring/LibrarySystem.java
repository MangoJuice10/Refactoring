// src/main/java/com/example/LibrarySystem.java
package refactoring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibrarySystem {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

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

    // REFACTROING: Move Method (Этот метод вычисляет общую сумму долга всех
    // пользователей, но использует только метод класса User calculateFine()
    // Следует переместить этот метод в класс User)
    public double calculateTotalFines() {
        double total = 0.0;
        for (User user : users) {
            total += user.calculateFine();
        }
        return total;
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

    // For Introduce Local Extension: We could extend Date with MfDate, but since
    // it's 7 out of 8, we can comment it.
    // /* Suitable for Introduce Local Extension: Extend Date with custom methods
    // like isWeekend, but not implemented here to limit to 7. */

    // REFACTORING: Move Field (LibrarySystem никак не использует поле; поле
    // логически должно находиться в классе Book)
    private int publicationYear = 2020; // Default year, but should be per book.

    public int getPublicationYear() {
        return publicationYear;
    }
}
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

    // This method calculates the total fine for all users, but it uses User data heavily.
    // It should be moved to User class or a separate FineCalculator class.
    // /* Suitable for Move Method: This method is in LibrarySystem but operates mostly on User data. Move to User or extract to new class. */
    public double calculateTotalFines() {
        double total = 0.0;
        for (User user : users) {
            total += user.calculateFine();
        }
        return total;
    }

    // This method formats book details including author info, which is mixed responsibilities.
    // /* Suitable for Extract Class: Book class has both book and author responsibilities. Extract Author class. */
    public String formatBookDetails(Book book) {
        return "Title: " + book.getTitle() + ", Author: " + book.getAuthorName() + " " + book.getAuthorSurname() +
               ", Birth Year: " + book.getAuthorBirthYear();
    }

    // Using a small class TelephoneNumber that can be inlined.
    // /* Suitable for Inline Class: TelephoneNumber has little responsibility; inline into User. */
    public void updateUserPhone(User user, String number) {
        user.setPhone(new TelephoneNumber(number));
    }

    // Client code that directly accesses delegate.
    // /* Suitable for Hide Delegate: Client accesses manager through person.getDepartment().getManager(). Hide in Person. */
    public String getUserManagerName(User user) {
        return user.getDepartment().getManager().getName();
    }

    // Server with many delegations.
    // /* Suitable for Remove Middle Man: Person delegates too much to Department; client should access Department directly if needed. */
    public boolean isUserManager(User user) {
        return user.getDepartment().isManager(user);
    }

    // Foreign method for Date.
    // /* Suitable for Introduce Foreign Method: nextDay should be in Date, but since we can't modify Date, add as utility in client. */
    private static Date nextDay(Date arg) {
        // implementation of next day
        return new Date(arg.getTime() + 86400000); // 24*60*60*1000
    }

    public Date getDueDate(Date borrowDate) {
        return nextDay(borrowDate); // Using foreign method.
    }

    // For Introduce Local Extension: We could extend Date with MfDate, but since it's 7 out of 8, we can comment it.
    // /* Suitable for Introduce Local Extension: Extend Date with custom methods like isWeekend, but not implemented here to limit to 7. */

    // This field is used more in Book class.
    // /* Suitable for Move Field: publicationYear is in LibrarySystem but used in Book. Move to Book. */
    private int publicationYear = 2020; // Default year, but should be per book.

    public int getPublicationYear() {
        return publicationYear;
    }
}
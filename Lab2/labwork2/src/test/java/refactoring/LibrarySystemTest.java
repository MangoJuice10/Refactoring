// src/test/java/com/example/LibrarySystemTest.java
package refactoring;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class LibrarySystemTest {
    private LibrarySystem library;
    private Department dept;
    private Person manager;
    private User user;

    @Before
    public void setUp() {
        library = new LibrarySystem();
        manager = new Person("John Doe");
        dept = new Department(manager);
        user = new User("Jane Smith", dept);
        library.addUser(user);
    }

    @Test
    public void testAddBook() {
        Book book = new Book("Test Title", "Author", "Surname", 1980);
        library.addBook(book);
        assertEquals(1, library.getBooks().size());
    }

    @Test
    public void testCalculateTotalFines() {
        user.borrowBook(new Date(System.currentTimeMillis() - 20L * 24 * 60 * 60 * 1000)); // Overdue by 6 days
        assertEquals(3.0, library.calculateTotalFines(), 0.01);
    }

    @Test
    public void testFormatBookDetails() {
        Book book = new Book("Test Title", "Author", "Surname", 1980);
        assertEquals("Title: Test Title, Author: Author Surname, Birth Year: 1980", library.formatBookDetails(book));
    }

    @Test
    public void testUpdateUserPhone() {
        library.updateUserPhone(user, "123-456");
        assertEquals("123-456", user.getPhone().getNumber());
    }

    @Test
    public void testGetUserManagerName() {
        assertEquals("John Doe", library.getUserManagerName(user));
    }

    @Test
    public void testIsUserManager() {
        assertFalse(library.isUserManager(user));
    }

    @Test
    public void testGetDueDate() {
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + 86400000);
        assertEquals(tomorrow.getTime() / 1000, library.getDueDate(today).getTime() / 1000); // Compare seconds
    }
}
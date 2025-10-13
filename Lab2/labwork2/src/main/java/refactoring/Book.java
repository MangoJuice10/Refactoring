// src/main/java/com/example/Book.java
package refactoring;

public class Book {
    private String title;
    private String authorName; // Mixed with author details.
    private String authorSurname;
    private int authorBirthYear;

    public Book(String title, String authorName, String authorSurname, int authorBirthYear) {
        this.title = title;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.authorBirthYear = authorBirthYear;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public int getAuthorBirthYear() {
        return authorBirthYear;
    }
}
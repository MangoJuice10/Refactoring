package refactoring;

public class Book {
    private String title;
    Author author;

    public Book(String title, String authorName, String authorSurname, int authorBirthYear) {
        this.title = title;
        this.author = new Author(authorName, authorSurname, authorBirthYear);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return this.author.getName();
    }

    public String getAuthorSurname() {
        return this.author.getSurname();
    }

    public int getAuthorBirthYear() {
        return this.author.getBirthYear();
    }
}
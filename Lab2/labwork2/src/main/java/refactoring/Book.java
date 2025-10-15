package refactoring;

public class Book {
    private String title;
    // REFACTORING: Extract Class (Класс Book содержит поля одновременно двух
    // сущностей: Книга и Автор, поэтому после выделения класса необходимо будет
    // изменить также обращения к исходному классу, связанные с полями и методами
    // нового класса)
    private String authorName;
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
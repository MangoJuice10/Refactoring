// src/main/java/com/example/Person.java
package refactoring;

public class Person {
    private String name;
    private String email;
    private String title;
    private String office;
    private String phone;
    private int id;

    public Person(String name) {
        this.name = name;
        this.email = name.toLowerCase().replace(" ", ".") + "@example.com";
        this.title = "Manager";
        this.office = "101";
        this.phone = "000-000-000";
        this.id = Math.abs(name.hashCode()) % 1000 + 1;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getOffice() {
        return office;
    }

    public String getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }

    public String getUpperCaseName() {
        return name == null ? null : name.toUpperCase();
    }

    public String getInitials() {
        if (name == null || name.isEmpty()) return "";
        String[] parts = name.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String p : parts) {
            if (!p.isEmpty()) sb.append(p.charAt(0));
        }
        return sb.toString();
    }

    public String getGreeting() {
        return "Dear " + (name == null ? "" : name);
    }
}
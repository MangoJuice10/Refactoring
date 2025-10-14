package refactoring;

public class Department {
    private Person manager;

    public Department(Person manager) {
        this.manager = manager;
    }

    public Person getManager() {
        return manager;
    }

    public String getManagerName() {
        return manager.getName();
    }

    public String getManagerEmail() {
        return manager.getEmail();
    }

    public String getManagerTitle() {
        return manager.getTitle();
    }

    public String getManagerOffice() {
        return manager.getOffice();
    }

    public String getManagerPhone() {
        return manager.getPhone();
    }

    public int getManagerId() {
        return manager.getId();
    }

    public String getManagerUpperCaseName() {
        return manager.getUpperCaseName();
    }

    public String getManagerInitials() {
        return manager.getInitials();
    }

    public String getManagerGreeting() {
        return manager.getGreeting();
    }
}
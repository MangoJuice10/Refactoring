package refactoring;

public class Department {
    private Person manager;

    public Department(Person manager) {
        this.manager = manager;
    }

    public Person getManager() {
        return manager;
    }

    public boolean isManager(User user) {
        return user.getName().equals(manager.getName());
    }
}
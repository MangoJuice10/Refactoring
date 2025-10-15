package refactoring;

import java.util.List;
import java.util.Date;

public class FineService {
    private double fineRate;

    public FineService(double fineRate) {
        this.fineRate = fineRate;
    }

	public double calculateFine(User user) {
        if (user.getBorrowDate() == null) return 0.0;
        long daysOverdue = (new Date().getTime() - user.getBorrowDate().getTime()) / (1000 * 60 * 60 * 24) - 14; // 2 weeks grace
        return daysOverdue > 0 ? daysOverdue * fineRate : 0.0;
    }

    public double calculateTotalFines(List<User> users) {
        double total = 0.0;
        FineService fineService = new FineService(0.5);
        for (User user : users) {
            total += fineService.calculateFine(user);
        }
        return total;
    }
}

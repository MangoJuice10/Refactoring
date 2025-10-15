package refactoring;

import java.util.List;
import java.util.Date;

public class FineService {
	public double calculateFine(User user, double fineRate) {
        if (user.getBorrowDate() == null) return 0.0;
        long daysOverdue = (new Date().getTime() - user.getBorrowDate().getTime()) / (1000 * 60 * 60 * 24) - 14; // 2 weeks grace
        return daysOverdue > 0 ? daysOverdue * fineRate : 0.0;
    }

    public double calculateTotalFines(List<User> users, double fineRate) {
        double total = 0.0;
        for (User user : users) {
            total += this.calculateFine(user, fineRate);
        }
        return total;
    }
}

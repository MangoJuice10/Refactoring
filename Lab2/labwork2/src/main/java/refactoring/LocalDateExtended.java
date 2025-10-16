package refactoring;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class LocalDateExtended {
    private final LocalDate localDate;

    public LocalDateExtended(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String formatLocalDateShort() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return localDate.format(fmt);
    }

    public LocalDate addBusinessDays(int days) {
        LocalDate res = localDate;
        int added = 0;
        while (added < days) {
            res = res.plusDays(1);
            if (!(res.getDayOfWeek().getValue() == 6 || res.getDayOfWeek().getValue() == 7)) {
                added++;
            }
        }
        return res;
    }

    public LocalDate nextBusinessDay() {
        LocalDate d = localDate.plusDays(1);
        while (d.getDayOfWeek().getValue() == 6 || d.getDayOfWeek().getValue() == 7) {
            d = d.plusDays(1);
        }
        return d;
    }

    public String verboseLocalDateInfo() {
        return "Date: " + formatLocalDateShort() + ", DayOfYear: " + localDate.getDayOfYear() + ", Leap: "
                + localDate.isLeapYear();
    }

    public static long daysBetween(LocalDate from, LocalDate to) {
        return ChronoUnit.DAYS.between(from, to);
    }
}
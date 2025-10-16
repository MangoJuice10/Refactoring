package refactoring;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class LocalDateExtendedTest {

    @Test
    public void testFormatLocalDateShort() {
        LocalDateExtended ext = new LocalDateExtended(LocalDate.of(2025, 3, 5));
        assertEquals("05.03.2025", ext.formatLocalDateShort());
    }

    @Test
    public void testAddBusinessDays_SimpleCase() {
        // Понедельник 13 октября 2025 → +5 рабочих дней → Понедельник 20 октября
        LocalDateExtended ext = new LocalDateExtended(LocalDate.of(2025, 10, 13));
        LocalDate result = ext.addBusinessDays(5);
        assertEquals(LocalDate.of(2025, 10, 20), result);
        assertEquals(DayOfWeek.MONDAY, result.getDayOfWeek());
    }

    @Test
    public void testAddBusinessDays_SkipsWeekend() {
        // Пятница 10 октября 2025 + 1 рабочий день = понедельник 13 октября
        LocalDateExtended ext = new LocalDateExtended(LocalDate.of(2025, 10, 10));
        LocalDate result = ext.addBusinessDays(1);
        assertEquals(LocalDate.of(2025, 10, 13), result);
        assertEquals(DayOfWeek.MONDAY, result.getDayOfWeek());
    }

    @Test
    public void testNextBusinessDay_FromFriday() {
        LocalDateExtended ext = new LocalDateExtended(LocalDate.of(2025, 10, 10)); // пятница
        LocalDate next = ext.nextBusinessDay();
        assertEquals(LocalDate.of(2025, 10, 13), next); // понедельник
        assertEquals(DayOfWeek.MONDAY, next.getDayOfWeek());
    }

    @Test
    public void testNextBusinessDay_FromWednesday() {
        LocalDateExtended ext = new LocalDateExtended(LocalDate.of(2025, 10, 15)); // среда
        LocalDate next = ext.nextBusinessDay();
        assertEquals(LocalDate.of(2025, 10, 16), next); // четверг
    }

    @Test
    public void testVerboseLocalDateInfo() {
        LocalDate date = LocalDate.of(2024, 2, 29); // високосный год
        LocalDateExtended ext = new LocalDateExtended(date);
        String info = ext.verboseLocalDateInfo();

        assertTrue(info.contains("Date: 29.02.2024"));
        assertTrue(info.contains("Leap: true"));
        assertTrue(info.contains("DayOfYear:"));
    }

    @Test
    public void testDaysBetween_Positive() {
        LocalDate from = LocalDate.of(2025, 10, 10);
        LocalDate to = LocalDate.of(2025, 10, 15);
        long days = LocalDateExtended.daysBetween(from, to);
        assertEquals(5, days);
    }

    @Test
    public void testDaysBetween_Negative() {
        LocalDate from = LocalDate.of(2025, 10, 20);
        LocalDate to = LocalDate.of(2025, 10, 15);
        long days = LocalDateExtended.daysBetween(from, to);
        assertEquals(-5, days);
    }

    @Test
    public void testGetLocalDate_ReturnsSame() {
        LocalDate base = LocalDate.of(2025, 10, 16);
        LocalDateExtended ext = new LocalDateExtended(base);
        assertEquals(base, ext.getLocalDate());
    }
}
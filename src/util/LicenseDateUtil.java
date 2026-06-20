package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LicenseDateUtil {

    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String addDays(int days) {
        return LocalDate.now().plusDays(days).format(fmt);
    }

    public static String addMonths(int months) {
        return LocalDate.now().plusMonths(months).format(fmt);
    }

    public static String addYears(int years) {
        return LocalDate.now().plusYears(years).format(fmt);
    }

    public static boolean isExpired(String date) {
        return LocalDate.now().isAfter(LocalDate.parse(date, fmt));
    }
}

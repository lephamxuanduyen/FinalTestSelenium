package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    public static String nextDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
    }
}

package test.clearsolution.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final String datePattern = "dd-MM-yyyy";

    public static String getDatePattern() {
        return datePattern;
    }

    public static LocalDate convertStringToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(datePattern));
    }
}

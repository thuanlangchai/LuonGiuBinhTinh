package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class NgayUtil {

	
    public static final DateTimeFormatter DMY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter DMY_HM = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    
    private NgayUtil() {
    }

    public static String fmt(LocalDate d) {
        return d == null ? "" : d.format(DMY);
    }

    public static String fmt(LocalDateTime d) {
        return d == null ? "" : d.format(DMY);
    }

    public static String fmtFull(LocalDateTime d) {
        return d == null ? "" : d.format(DMY_HM);
    }

    public static LocalDate parseDate(String s) {
        if (s == null || s.isBlank()) {
            return null;
        }
        String t = s.trim();
        try {
            return LocalDate.parse(t, DMY);
        } catch (DateTimeParseException e) {
            try {
                return LocalDate.parse(t);
            } catch (DateTimeParseException ex) {
                return null;
            }
        }
    }
}

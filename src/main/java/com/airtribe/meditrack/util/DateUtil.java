package com.airtribe.meditrack.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.airtribe.meditrack.constants.Constants;

/**
 * Date utility for formatting and parsing.
 */
public class DateUtil {
    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    private DateUtil() {
    }

    public static String formatDate(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }

    public static LocalDateTime parseDate(String dateString) {
        return LocalDateTime.parse(dateString, FORMATTER);
    }

    public static boolean isValidDate(LocalDateTime dateTime) {
        return dateTime.isAfter(LocalDateTime.now());
    }
}
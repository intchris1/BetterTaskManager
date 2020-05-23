package org.example.tm.util;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateFormatter {
    @NotNull
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String convertDateToString(@NotNull final LocalDate date) {
        return FORMATTER.format(date);
    }

    public static LocalDate convertStringToDate(@NotNull final String date) {
        return LocalDate.parse(date, FORMATTER);
    }

}

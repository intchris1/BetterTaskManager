package org.example.tm.util;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateFormatter {
    @NotNull
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy");

    public static String convertDateToString(@NotNull final Date date) {
        return FORMATTER.format(date);
    }

}

package com.example.userproject.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    public static long totalDaysBetween(LocalDate today, LocalDate deadline) {
        return ChronoUnit.DAYS.between(today, deadline);
    }
}

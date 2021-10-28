package com.example.userproject.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtilTest {

    @Test
    public void testTotalDaysBetween() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate today = LocalDate.parse("01/01/2021", formatter);
        LocalDate deadline = LocalDate.parse("01/01/2022", formatter);

        long result = DateUtil.totalDaysBetween(today, deadline);

        Assertions.assertEquals(365L, result);
    }
}

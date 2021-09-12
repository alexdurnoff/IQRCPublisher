package ru.durnov;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateTimeFromLogTest {

    @Test
    void value() {
        String str = "DateTime=2015/07/30 00:06:52   wday=4";
        LocalDateTime localDateTime = new LocalDateTimeFromLog(str).value();
        assertEquals(localDateTime, LocalDateTime.of(2015,7,30,0,6,52));
    }
}
package com.springmicroservice.loginservice.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DateUtils {

    public static long getTimeInMillisecondsFromLocalDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return Timestamp.valueOf(localDateTime).getTime();
    }
}

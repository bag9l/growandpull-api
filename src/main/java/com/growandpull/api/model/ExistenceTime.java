package com.growandpull.api.model;

import com.growandpull.api.model.enums.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExistenceTime {

    private static final Integer MINUTE_IN_SECONDS = 60;
    private static final Integer HOUR_IN_MINUTES = 60;
    private static final Integer DAY_IN_HOURS = 24;
    private static final Integer WEEK_IN_DAYS = 7;
    private static final Integer MONTH_IN_DAYS = 30;
    private static final Integer YEAR_IN_MONTHS = 12;

    private int value;
    private TimeUnit unit;


    public static ExistenceTime createExistenceTimeFrom(LocalDateTime createdAt) {
        Duration duration = Duration.between(createdAt, LocalDateTime.now());

        int value = (int) duration.toSeconds();
        TimeUnit unit = TimeUnit.SECOND;

        if (value >= MINUTE_IN_SECONDS) {
            value /= MINUTE_IN_SECONDS;
            unit = TimeUnit.MINUTE;
        }
        if (value >= HOUR_IN_MINUTES) {
            value /= HOUR_IN_MINUTES;
            unit = TimeUnit.HOUR;
        }
        if (value >= DAY_IN_HOURS) {
            value /= DAY_IN_HOURS;
            unit = TimeUnit.DAY;
        }
        if (value >= WEEK_IN_DAYS) {
            value /= WEEK_IN_DAYS;
            unit = TimeUnit.WEEK;
        }
        if (value >= MONTH_IN_DAYS) {
            value /= MONTH_IN_DAYS;
            unit = TimeUnit.MONTH;
        }
        if (value >= YEAR_IN_MONTHS) {
            value /= YEAR_IN_MONTHS;
            unit = TimeUnit.YEAR;
        }

        return new ExistenceTime(value, unit);
    }
}

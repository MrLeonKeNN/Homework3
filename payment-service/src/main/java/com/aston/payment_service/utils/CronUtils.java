package com.aston.payment_service.utils;

import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class CronUtils {

    public Instant parseCron(String cron, ZoneId zoneId) {
        CronExpression cronExpression = CronExpression.parse(cron);

        ZonedDateTime now = ZonedDateTime.now(zoneId);

        ZonedDateTime nextRun = cronExpression.next(now);

        Instant nextRunInstant = null;
        if (nextRun != null) {
            nextRunInstant = nextRun.toInstant();
        }
        return nextRunInstant;
    }
}

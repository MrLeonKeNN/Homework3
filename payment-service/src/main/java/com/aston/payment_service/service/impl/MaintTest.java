package com.aston.payment_service.service.impl;

import org.springframework.scheduling.support.CronExpression;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MaintTest {
    public static void main(String[] args) {
//        String cron = "0 0 12 * * ? "; // Минутное срабатывание
//
//        // Разбираем CRON через Spring
//        CronExpression cronExpression = CronExpression.parse(cron);
//
//        // Текущая временная зона
//        ZoneId zoneId = ZoneId.systemDefault();
//        ZonedDateTime now = ZonedDateTime.now(zoneId);
//
//        // Вычисляем следующее срабатывание
//        ZonedDateTime nextRun = cronExpression.next(now);
//
//        // Конвертируем в Instant
//        Instant nextRunInstant = null;
//        if (nextRun != null) {
//            nextRunInstant = nextRun.toInstant();
//        }
//
//        System.out.println("Следующее выполнение CRON: " + nextRunInstant);
//        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
//        Instant now = zonedDateTime.toInstant();
//        System.out.println(now);
    }
}

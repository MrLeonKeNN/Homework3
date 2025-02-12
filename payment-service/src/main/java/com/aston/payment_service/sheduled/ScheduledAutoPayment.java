package com.aston.payment_service.sheduled;

import com.aston.payment_service.entity.AutoPayments;
import com.aston.payment_service.repository.AutoPaymentsRepository;
import com.aston.payment_service.repository.PaymentRepository;
import com.aston.payment_service.service.api.OutboxService;
import com.aston.payment_service.utils.CronUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class ScheduledAutoPayment {

    private final PaymentRepository paymentRepository;
    private final AutoPaymentsRepository autoPaymentsRepository;
    private final CronUtils cronUtils;
    private final OutboxService outboxService;

    @Scheduled(cron = "0 0 * * * *")
    @SchedulerLock(name = "TaskScheduler_scheduledTask", lockAtLeastFor = "PT35M",
            lockAtMostFor = "PT59M")
    @Transactional
    public void createPayment() {
        List<AutoPayments> autoPaymentsForCrone = autoPaymentsRepository.getAutoPaymentsForCrone();
        autoPaymentsForCrone.forEach(autoPayments ->
                {
                    autoPayments.setNextPaymentDate(
                            cronUtils.parseCron(
                                    autoPayments.getPeriodicity(),
                                    ZoneId.of(autoPayments.getMeasuredTimeZone())
                            )
                    );
                    autoPayments.setLastPaymentDate(Instant.now());
                }
        );
        autoPaymentsRepository.saveAll(autoPaymentsForCrone);
    }

    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(name = "TaskScheduler_scheduledTask2", lockAtLeastFor = "PT5S",
            lockAtMostFor = "PT10S")
    @Transactional()
    public void sendPayload() {
        outboxService.processOutbox();
    }
}

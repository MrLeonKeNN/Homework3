package com.aston.payment_service.sheduled;

import com.aston.payment_service.entity.AutoPayments;
import com.aston.payment_service.repository.AutoPaymentsRepository;
import com.aston.payment_service.repository.PaymentRepository;
import com.aston.payment_service.utils.CronUtils;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Component
@Slf4j
public class ScheduledAutoPayment {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final PaymentRepository paymentRepository;
    private final AutoPaymentsRepository autoPaymentsRepository;
    private final CronUtils cronUtils;

    public ScheduledAutoPayment(PaymentRepository paymentRepository, AutoPaymentsRepository autoPaymentsRepository, CronUtils cronUtils) {
        this.paymentRepository = paymentRepository;
        this.autoPaymentsRepository = autoPaymentsRepository;
        this.cronUtils = cronUtils;
    }

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


}

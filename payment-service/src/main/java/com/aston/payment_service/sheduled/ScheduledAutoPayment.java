package com.aston.payment_service.sheduled;

import com.aston.payment_service.entity.AutoPayments;
import com.aston.payment_service.entity.Outbox;
import com.aston.payment_service.entity.enums.OutboxStatus;
import com.aston.payment_service.repository.AutoPaymentsRepository;
import com.aston.payment_service.repository.OutboxRepository;
import com.aston.payment_service.repository.PaymentRepository;
import com.aston.payment_service.service.api.OutboxService;
import com.aston.payment_service.utils.CronUtils;
import com.example.JsonPojo.kafka.pojo.CreatePaymentOBS;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.kafka.core.KafkaTemplate;
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

    //    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final PaymentRepository paymentRepository;
    private final AutoPaymentsRepository autoPaymentsRepository;
    private final CronUtils cronUtils;
    private final OutboxService outboxService;
//    private final OutboxRepository outboxRepository;
//    private KafkaTemplate<String, CreatePaymentOBS> kafkaTemplate;

//    public ScheduledAutoPayment(PaymentRepository paymentRepository, AutoPaymentsRepository autoPaymentsRepository, CronUtils cronUtils) {
//        this.paymentRepository = paymentRepository;
//        this.autoPaymentsRepository = autoPaymentsRepository;
//        this.cronUtils = cronUtils;
//    }

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

    //    @Scheduled(cron = "*/30 * * * * *")
//    @SchedulerLock(name = "TaskScheduler_scheduledTask2", lockAtLeastFor = "PT5S",
//            lockAtMostFor = "PT10S")
//    public void test(){
//        System.out.println("POD123");
//
//    }
//
    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(name = "TaskScheduler_scheduledTask2", lockAtLeastFor = "PT5S",
            lockAtMostFor = "PT10S")
    @Transactional
    public void sendPayload() {
        outboxService.processOutbox();
//        List<Outbox> outboxList = outboxRepository.findWaitingOutbox(OutboxStatus.WAITING);
//        System.out.println(outboxList.size());
//        CompletableFuture<SendResult<CreatePaymentOBS, String>> send = kafkaTemplate.send();

    }
}

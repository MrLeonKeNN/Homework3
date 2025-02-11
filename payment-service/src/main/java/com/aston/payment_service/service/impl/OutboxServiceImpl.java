package com.aston.payment_service.service.impl;

import com.aston.payment_service.entity.Entities;
import com.aston.payment_service.entity.Outbox;
import com.aston.payment_service.entity.Payment;
import com.aston.payment_service.entity.ServiceEntity;
import com.aston.payment_service.entity.enums.OutboxStatus;
import com.aston.payment_service.mapper.CreatePaymentOBSMapper;
import com.aston.payment_service.mapper.OutboxMapper;
import com.aston.payment_service.repository.OutboxRepository;
import com.aston.payment_service.service.api.OutboxService;
import com.example.JsonPojo.kafka.pojo.CreatePaymentOBS;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@AllArgsConstructor
public class OutboxServiceImpl implements OutboxService {

    private OutboxRepository outboxRepository;
    private OutboxMapper outboxMapper;
    private CreatePaymentOBSMapper createPaymentOBSMapper;
    private KafkaTemplate<String, CreatePaymentOBS> kafkaTemplate;


    @Override
    public void save(Payment payment, Entities entities, ServiceEntity serviceEntity) {
        CreatePaymentOBS createPaymentOBS = createPaymentOBSMapper.toPojo(payment, entities, serviceEntity);
        outboxRepository.save(outboxMapper.createOutbox(payment.getId(), createPaymentOBS));
    }

    @Override
    public void processOutbox() {
        List<Outbox> outboxList = outboxRepository.findWaitingOutbox(OutboxStatus.WAITING);
        List<CompletableFuture<SendResult<String, CreatePaymentOBS>>> futures = new ArrayList<>();

        for (Outbox outbox : outboxList) {
            CompletableFuture<SendResult<String, CreatePaymentOBS>> future = kafkaTemplate
                    .send("payments", outbox.getPayload())
                    .handle((metadata, exception) -> {
                        if (exception != null) {
                            log.error("Failed to send message to Kafka: {}", outbox, exception);
                            outbox.setStatus(OutboxStatus.FAILED);
                        } else {
                            log.info("Message sent to Kafka successfully: {}", outbox);
                            outbox.setStatus(OutboxStatus.SEND);
                        }
                        return null;
                    });

            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .join();

        outboxRepository.saveAllAndFlush(outboxList);
    }
}

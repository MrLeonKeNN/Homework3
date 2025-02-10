package aston.intensive.ticketservice.service;

import aston.intensive.ticketservice.dto.kafka.producer.CustomPayCommunicationServicesNotificationEvent;
import aston.intensive.ticketservice.kafka.pojo.PayCommunicationServicesNotificationEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class TestService {

    private final KafkaTemplate<String, PayCommunicationServicesNotificationEvent> kafkaTemplate;

    public void sendMessage() {
        PayCommunicationServicesNotificationEvent customPayCommunicationServicesNotificationEvent =
                PayCommunicationServicesNotificationEvent.builder()
                        .withClientID(UUID.fromString("2bf16b0d-4d61-46a9-9959-f349e04652b1"))
                        .withPaymentID(UUID.fromString("cb6fadce-7dbf-40b4-9990-c521374eb40a"))
                        .withStatus("DONE")
                        .build();
//        CustomPayCommunicationServicesNotificationEvent customPayCommunicationServicesNotificationEvent = new CustomPayCommunicationServicesNotificationEvent();
        customPayCommunicationServicesNotificationEvent.setClientID(UUID.fromString("2bf16b0d-4d61-46a9-9959-f349e04652b1"));
        customPayCommunicationServicesNotificationEvent.setPaymentID(UUID.fromString("cb6fadce-7dbf-40b4-9990-c521374eb40a"));
        customPayCommunicationServicesNotificationEvent.setStatus("DONE");
//        CustomPayCommunicationServicesNotificationEvent customPayCommunicationServicesNotificationEvent = CustomPayCommunicationServicesNotificationEvent.builder().clientID("2bf16b0d-4d61-46a9-9959-f349e04652b1").paymentID("cb6fadce-7dbf-40b4-9990-c521374eb40a").status("DONE").build();
        CompletableFuture<SendResult<String, PayCommunicationServicesNotificationEvent>> paymentServiceStatus = kafkaTemplate.send("paymentServiceStatus", customPayCommunicationServicesNotificationEvent);
//        System.out.println("Сообщение отправлено в Kafka: " + message);
    }

}

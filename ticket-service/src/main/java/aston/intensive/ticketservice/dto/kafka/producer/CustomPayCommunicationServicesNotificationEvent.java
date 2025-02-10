package aston.intensive.ticketservice.dto.kafka.producer;

import aston.intensive.ticketservice.kafka.pojo.PayCommunicationServicesNotificationEvent;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public class CustomPayCommunicationServicesNotificationEvent extends PayCommunicationServicesNotificationEvent implements Serializable {

    private String clientID;
    private String paymentID;
    private String status;
}

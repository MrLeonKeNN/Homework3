package com.aston.payment_service.configuration;

import com.example.JsonPojo.kafka.pojo.CreatePaymentOBS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaDeserializerConfig {

    @Bean
    public JsonDeserializer<CreatePaymentOBS> paymentFromAccountJsonDeserializer() {

        JsonDeserializer<CreatePaymentOBS> deserializer =
                new JsonDeserializer<>(CreatePaymentOBS.class);
        deserializer.addTrustedPackages("*");
        return deserializer;
    }

    @Bean
    public JsonSerializer<CreatePaymentOBS> paymentFromAccountJsonSerializer() {
        return new JsonSerializer<>();
    }
}

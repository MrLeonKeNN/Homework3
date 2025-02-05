package com.aston.payment_service.controller.impl;

import com.aston.payment_service.controller.api.AutoPaymentController;
import com.aston.payment_service.dto.request.AutoPaymentDtoRequest;
import com.aston.payment_service.dto.response.SuccesDtoResponse;
import com.aston.payment_service.service.api.AutoPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@AllArgsConstructor
public class AutoPaymentControllerImpl implements AutoPaymentController {

    private final AutoPaymentService autoPaymentService;
    private WebClient.Builder webClientBuilder;

    @Override
    public ResponseEntity<SuccesDtoResponse> createAutoPayment(AutoPaymentDtoRequest autoPaymentDtoRequest) {
        return ResponseEntity.ok(autoPaymentService.createAutoPayment(autoPaymentDtoRequest));
    }

    @Override
    public ResponseEntity<Void> test() {
        String s = webClientBuilder.build()
                .get()
                .uri("http://account-service/v1/account-service/test")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println("test");
        System.out.println(s);
        return ResponseEntity.ok()
                .build();
    }
}

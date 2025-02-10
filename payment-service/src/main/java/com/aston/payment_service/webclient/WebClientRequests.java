package com.aston.payment_service.webclient;

import com.aston.payment_service.dto.response.AccountDtoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@AllArgsConstructor
public class WebClientRequests {

    private static final String ACCOUNT_SERVICE_DETAILS_URL = "http://account-service/v1/account-service/details/";
    private static final String ACCOUNT_DETAILS_ENDPOINT = ACCOUNT_SERVICE_DETAILS_URL + "{uuid}";

    private final WebClient.Builder webClientBuilder;

    public AccountDtoResponse getAccountDetails(UUID uuid) {
        return webClientBuilder.build()
                .get()
                .uri(ACCOUNT_DETAILS_ENDPOINT, uuid.toString())
                .retrieve()
                .bodyToMono(AccountDtoResponse.class)
                .onErrorResume(trow -> Mono.error(new RuntimeException(trow.getMessage())))
                .block();
    }
}

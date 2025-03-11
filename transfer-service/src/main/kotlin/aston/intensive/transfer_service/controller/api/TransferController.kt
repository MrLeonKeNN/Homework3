package aston.intensive.transfer_service.controller.api

import org.springframework.web.bind.annotation.RequestMapping
import reactor.core.publisher.Mono

@RequestMapping("/v1/transfers")
interface TransferController {

    suspend fun executeInternalAccountTransfer(): Mono<String>
}
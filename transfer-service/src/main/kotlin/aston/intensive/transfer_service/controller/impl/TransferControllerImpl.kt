package aston.intensive.transfer_service.controller.impl

import aston.intensive.transfer_service.controller.api.TransferController
import reactor.core.publisher.Mono

class TransferControllerImpl : TransferController  {

    override suspend fun executeInternalAccountTransfer(): Mono<String> {
        TODO("Not yet implemented")
    }

}
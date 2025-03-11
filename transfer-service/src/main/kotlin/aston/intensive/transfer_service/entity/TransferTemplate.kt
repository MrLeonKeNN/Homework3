package aston.intensive.transfer_service.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Table("transfer_template")
data class TransferTemplate(

    @Id
    val id: UUID,
    val name: String,
    val senderAccountNumber: String,
    val senderCardNumber: String,
    val senderClientId: UUID,
    val receiverAccountNumber: String,
    val receiverCardNumber: String,
    val amount: BigDecimal,
    val currency: String,
    val createdDate: LocalDateTime,
    val lastTransactionDate: LocalDateTime?,
    val comment: String?,
    val isFavorite: Boolean
)

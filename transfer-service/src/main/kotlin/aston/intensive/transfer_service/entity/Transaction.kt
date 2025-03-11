package aston.intensive.transfer_service.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Table("transaction")
data class Transaction(
    @Id
    val id: UUID,
    val senderAccountNumber: String,
    val senderCardNumber: String,
    val senderClientId: UUID,
    val receiverAccountNumber: String,
    val receiverCardNumber: String,
    val amount: BigDecimal,
    val currency: String,
    val createdDate: LocalDateTime,
    val lastUpdateDate: LocalDateTime?,
    val transferTypeName: String,
    val status: String,
    val comment: String?,
    val isFavorite: Boolean
)
package aston.intensive.card_service.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.*

@Table("card_products")
data class CardProduct(

    @Id
    val id: UUID? = null,

    val name: String,

    val description: String?,

    val termId: UUID?,

    val limitationId: UUID?,

    val status: String,

    val currencies: String?,

    val validityPeriod: String?,

    val createdAt: Instant,

    val updatedAt: Instant?,

    val level: String,

    val isVirtual: Boolean,

    val paymentSystems: String?,

    val cardType: String

)